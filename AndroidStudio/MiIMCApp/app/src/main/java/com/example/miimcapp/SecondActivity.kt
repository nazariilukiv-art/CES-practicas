package com.example.miimcapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.miimcapp.databinding.ActivityMainBinding
import com.example.miimcapp.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imc = intent.getDoubleExtra("EXTRA_IMC", 0.0)
        val sexo = intent.getStringExtra("EXTRA_SEXO")


        mostrar(imc,sexo)
    }

    private fun mostrar(imc: Double, sexo: String?) {

        binding.textNumeroIMC.text = "Tu IMC es $imc"

        val estado: String
        val imagen: Int

        when (sexo) {
            "M" -> {
                when {
                    imc < 18.5 -> {
                        estado = "Bajo peso"
                        imagen = R.drawable.estado1
                    }
                    imc < 24.9 -> {
                        estado = "Peso normal"
                        imagen = R.drawable.estado2
                    }
                    imc < 29.9 -> {
                        estado = "Pre-obesidad o Sobrepeso"
                        imagen = R.drawable.estado3
                    }
                    imc < 34.9 -> {
                        estado = "Obesidad clase I"
                        imagen = R.drawable.estado4
                    }
                    imc < 39.9 -> {
                        estado = "Obesidad clase II"
                        imagen = R.drawable.estado5
                    }
                    else -> {
                        estado = "Obesidad clase III"
                        imagen = R.drawable.estado6
                    }
                }

            }
            "F" -> {
                when {
                    imc < 16.5 -> {
                        estado = "Bajo peso"
                        imagen = R.drawable.estado1
                    }
                    imc < 22.9 -> {
                        estado = "Peso normal"
                        imagen = R.drawable.estado2
                    }
                    imc < 25.9 -> {
                        estado = "Pre-obesidad o Sobrepeso"
                        imagen = R.drawable.estado3
                    }
                    imc < 30.9 -> {
                        estado = "Obesidad clase I"
                        imagen = R.drawable.estado4
                    }
                    imc < 33.9 -> {
                        estado = "Obesidad clase II"
                        imagen = R.drawable.estado5
                    }
                    else -> {
                        estado = "Obesidad clase III"
                        imagen = R.drawable.estado6
                    }
                }
            }
            else ->{
                estado = "Error"
                imagen = R.drawable.ic_launcher_background
            }
        }

        binding.textEstadoIMC.text = estado
        binding.imagenEstado.setImageResource(imagen)

    }
}

















