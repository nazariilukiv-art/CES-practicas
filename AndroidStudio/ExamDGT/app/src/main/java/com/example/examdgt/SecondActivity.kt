package com.example.examdgt

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.examdgt.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nombreApellido = intent.getStringExtra("EnombreApellido")
        val matriculaCoche = intent.getStringExtra("EmatriculaCoche")
        val anioMatriculacion = intent.getDoubleExtra("EanioMatriculacion",0.0)
        val autonomia = intent.getStringExtra("Eautonomia")
        val imagen: Int = intent.getSerializableExtra("Imagen") as Int

        binding.textNombreApellido.text = "Tu nombre es: $nombreApellido"
        binding.textMatricula.text = "Tu matricula es: $matriculaCoche"
        binding.textAnoMatriculacion.text = "Tu año de matriculacion: $anioMatriculacion"
        binding.textAutonomia.text = "La autonomia: $autonomia"
        binding.imagenGS.setImageResource(imagen)


    }
}