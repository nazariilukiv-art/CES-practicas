package com.example.tienda.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tienda.adapter.AdapterProducto
import com.example.tienda.databinding.ActivityCarritoBinding
import com.example.tienda.dataset.DataSet

class CarritoActivity : AppCompatActivity(), AdapterProducto.OnProductoCarritoListener {

    private lateinit var binding: ActivityCarritoBinding
    private lateinit var adapter: AdapterProducto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarritoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Mi Carrito"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Inicializamos adaptador en modo carrito (true)
        adapter = AdapterProducto(DataSet.listaCarrito, this, true)

        binding.recyclerCarrito.layoutManager = LinearLayoutManager(this)
        binding.recyclerCarrito.adapter = adapter

        actualizarInfo()

        // Lógica del botón VACIAR CARRITO
        binding.btnVaciarCarrito.setOnClickListener {
            DataSet.vaciarCarrito()
            adapter.notifyDataSetChanged() // Refresca la lista vacía
            actualizarInfo()
            Toast.makeText(this, "Carrito vaciado", Toast.LENGTH_SHORT).show()
        }
    }

    override fun actualizarContadorCarrito() {
        // No se usa aquí porque ocultamos el botón de agregar/quitar individual,
        // pero es necesario por la interfaz del adaptador.
    }

    private fun actualizarInfo() {
        supportActionBar?.subtitle = "Total artículos: ${DataSet.listaCarrito.size}"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}