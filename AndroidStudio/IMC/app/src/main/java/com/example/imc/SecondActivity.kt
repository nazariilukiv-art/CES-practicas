package com.example.imc

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.imc.databinding.ActivityMainBinding
import com.example.imc.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imc = intent.getDoubleExtra("EXTRA_IMC",0.0)
        val sexo = intent.getStringExtra("EXTRA_SEXO")


        mostrarResultado(imc, sexo)



    }

    private fun mostrarResultado(imc: Double, sexo: String?) {

        val imcFormateado = "%.2f".format(imc)
        binding.textViewResultado.text = "Tu IMC es: $imcFormateado"

        val estado: String
        val imagenID: Int

        when(sexo){
                "M" ->{
                    when {
                        imc < 18.5 -> {
                            estado = "Bajo peso"
                            imagenID = R.drawable.estado1
                        }
                        imc < 24.9 -> {
                            estado = "Peso normal"
                            imagenID = R.drawable.estado2
                        }
                        imc < 29.9 -> {
                            estado = "Pre-obesidad o Sobrepeso"
                            imagenID = R.drawable.estado3
                        }
                        imc < 34.9 -> {
                            estado = "Obesidad clase I"
                            imagenID = R.drawable.estado4
                        }
                        imc < 39.9 -> {
                            estado = "Obesidad clase II"
                            imagenID = R.drawable.estado5
                        }
                        else -> {
                            estado = "Obesidad clase III"
                            imagenID = R.drawable.estado6
                        }
                    }
                }
                "F" ->{
                    when {
                        imc < 16.5 -> {
                            estado = "Bajo peso"
                            imagenID = R.drawable.estado1
                        }
                        imc < 22.9 -> {
                            estado = "Peso normal"
                            imagenID = R.drawable.estado2
                        }
                        imc < 25.9 -> {
                            estado = "Pre-obesidad o Sobrepeso"
                            imagenID = R.drawable.estado3
                        }
                        imc < 30.9 -> {
                            estado = "Obesidad clase I"
                            imagenID = R.drawable.estado4
                        }
                        imc < 33.9 -> {
                            estado = "Obesidad clase II"
                            imagenID = R.drawable.estado5
                        }
                        else -> {
                            estado = "Obesidad clase III"
                            imagenID = R.drawable.estado6
                        }
                    }
                }
                else -> {
                    estado = "Error de datos"
                    imagenID = R.drawable.ic_launcher_background
                }
            }

        binding.textViewEstado.text = estado
        binding.imageViewResultado.setImageResource(imagenID)

    }


}