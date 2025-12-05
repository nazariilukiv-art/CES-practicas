package com.example.tienda

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tienda.adapter.AdapterProducto
import com.example.tienda.databinding.ActivityMainBinding
import com.example.tienda.dataset.DataSet
import com.example.tienda.model.Producto
import com.example.tienda.ui.CarritoActivity

class MainActivity : AppCompatActivity(), AdapterProducto.OnProductoCarritoListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterProducto: AdapterProducto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        // 1. SPINNER: Configuración correcta
        val categorias = arrayListOf("Todas", "muebles", "tecnologia", "ropa")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categorias)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCategorias.adapter = spinnerAdapter

        // 2. RECYCLER: Inicialización
        // Importante: Empezamos con una copia nueva de la lista para no afectar al DataSet original
        val listaInicial = ArrayList(DataSet.lista)
        adapterProducto = AdapterProducto(listaInicial, this)

        if (resources.configuration.orientation == 1) {
            binding.recyclerProductos.layoutManager = LinearLayoutManager(this)
        } else {
            binding.recyclerProductos.layoutManager = GridLayoutManager(this, 2)
        }
        binding.recyclerProductos.adapter = adapterProducto

        acciones()
    }

    fun acciones() {
        binding.spinnerCategorias.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    // Esta función se ejecuta automáticamente al iniciar y al cambiar selección
                    val categoriaSeleccionada = parent!!.adapter.getItem(position).toString()
                    ejecutarFiltro(categoriaSeleccionada)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    // Método centralizado para filtrar
    private fun ejecutarFiltro(categoria: String) {
        val listaFiltrada = DataSet.getListaFiltrada(categoria)
        adapterProducto.chageList(listaFiltrada)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_carrio -> {
                val intent = Intent(this, CarritoActivity::class.java)
                startActivity(intent)
            }
            R.id.menu_filtrar -> {
                // CORREGIDO: Toma la categoría actual del spinner y fuerza el filtrado
                val categoria = binding.spinnerCategorias.selectedItem.toString()
                ejecutarFiltro(categoria)
            }
            R.id.menu_limpiar -> {
                // Resetea spinner a "Todas" (posición 0) y muestra toda la lista
                binding.spinnerCategorias.setSelection(0)
                ejecutarFiltro("Todas")
            }
        }
        return true
    }

    override fun actualizarContadorCarrito() {
        binding.textoContador.text = DataSet.listaCarrito.size.toString()
    }

    // Al volver del carrito, actualizamos el contador por si se borraron cosas
    override fun onResume() {
        super.onResume()
        actualizarContadorCarrito()
    }
}