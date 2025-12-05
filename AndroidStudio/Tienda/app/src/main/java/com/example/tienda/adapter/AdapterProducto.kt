package com.example.tienda.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tienda.R
import com.example.tienda.databinding.ItemProductoBinding
import com.example.tienda.dataset.DataSet
import com.example.tienda.model.Producto
import com.example.tienda.ui.DetalleActivity

class AdapterProducto(
    var lista: ArrayList<Producto>,
    var contexto: Context,
    var esCarrito: Boolean = false // Parámetro para saber si estamos en el carrito
) : RecyclerView.Adapter<AdapterProducto.MyHolder>() {

    var listener: OnProductoCarritoListener

    init {
        // En algunas activities puede que no implementemos la interfaz, usamos try/catch o casteo seguro
        if (contexto is OnProductoCarritoListener) {
            listener = contexto as OnProductoCarritoListener
        } else {
            // Implementación vacía por defecto para evitar crashes
            listener = object : OnProductoCarritoListener {
                override fun actualizarContadorCarrito() {}
            }
        }
    }

    inner class MyHolder(var binding: ItemProductoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding = ItemProductoBinding.inflate(LayoutInflater.from(contexto), parent, false)
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val producto: Producto = lista[position]

        // Cargar imagen
        Glide.with(contexto)
            .load(producto.imagen)
            .placeholder(R.drawable.producto)
            .into(holder.binding.imagenFila)

        holder.binding.nombreFila.text = producto.nombre

        // SI ES CARRITO: Ocultar botón "Agregar Carrito".
        // SI ES TIENDA: Mostrar botón "Agregar Carrito".
        if (esCarrito) {
            holder.binding.btnCompra.visibility = View.GONE
        } else {
            holder.binding.btnCompra.visibility = View.VISIBLE
        }

        // Botón Detalle siempre visible
        holder.binding.btnDetalle.setOnClickListener {
            val intent = Intent(contexto, DetalleActivity::class.java)
            intent.putExtra("producto", producto)
            contexto.startActivity(intent)
        }

        // Botón Compra
        holder.binding.btnCompra.setOnClickListener {
            DataSet.addProducto(producto)
            listener.actualizarContadorCarrito()
        }
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    // Función clave para el FILTRO
    fun chageList(nuevaLista: ArrayList<Producto>){
        this.lista = nuevaLista
        notifyDataSetChanged()
    }

    interface OnProductoCarritoListener {
        fun actualizarContadorCarrito()
    }
}