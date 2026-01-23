package com.example.tienda.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.tienda.R
import com.example.tienda.databinding.ActivityDetalleBinding
import com.example.tienda.model.Producto

class DetalleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetalleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Botón atrás
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Detalle del Producto"

        val producto = intent.getSerializableExtra("producto") as? Producto

        if (producto != null) {
            binding.nombreDetalle.text = producto.nombre
            binding.descripcionDetalle.text = producto.descripcion
            binding.precioDetalle.text = "${producto.precio} €"

            Glide.with(this)
                .load(producto.imagen)
                .placeholder(R.drawable.producto)
                .into(binding.imagenDetalle)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}