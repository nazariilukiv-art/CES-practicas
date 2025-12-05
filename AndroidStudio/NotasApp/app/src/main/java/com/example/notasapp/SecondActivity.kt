package com.example.notasapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.notasapp.databinding.ActivityMainBinding
import com.example.notasapp.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val notaFinal = intent.getDoubleExtra("Extra_notaFinal", 0.0)


        mostrarResultado(notaFinal)
    }

    private fun mostrarResultado(notaFinal: Double) {

        val estado: String
        val imagen: Int

        when{
            notaFinal < 5.0 ->{
                estado = "Suspenso"
                imagen = R.drawable.estado3
            }
            notaFinal < 6.0 ->{
                estado = "Aprobado"
                imagen = R.drawable.estado5
            }
            notaFinal < 7.0 ->{
                estado = "bien"
                imagen = R.drawable.estado1
            }
            notaFinal < 8.0 ->{
                estado = "Notable"
                imagen = R.drawable.estado6
            }
            notaFinal < 9.0 ->{
                estado = "Sobresaliente"
                imagen = R.drawable.estado4
            }
            notaFinal <= 10.0 ->{
                estado = "Maravilloso"
                imagen = R.drawable.estado2
            }else -> {
                estado = "No Calificado"
                imagen = R.drawable.ic_launcher_background
            }

        }

        binding.textNotaFinal.text = "$notaFinal"
        binding.textEstadoFinal.text = estado
        binding.imagenEstado.setImageResource(imagen)
    }
}



















