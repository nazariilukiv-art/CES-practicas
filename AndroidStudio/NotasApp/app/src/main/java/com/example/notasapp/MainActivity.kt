package com.example.notasapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.notasapp.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var asistenciaSeleccionada: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        acciones()
    }

    private fun acciones() {

        binding.botonValidacion.setOnClickListener {
            val notaExam = binding.editTextNotaExamen.text.toString()
            val notaTrabajo = binding.editTextNotaTrabajo.text.toString()

            if (notaExam.isBlank() || notaTrabajo.isBlank() || asistenciaSeleccionada.isBlank()){
                Snackbar.make(binding.root, "Te faltan Datos para rellenar", Snackbar.LENGTH_SHORT).show()
            } else {

                var notaBase = (notaExam.toDouble() * 0.6) + (notaTrabajo.toDouble() * 0.4)
                var notaFinal: Double
                when(asistenciaSeleccionada){
                    "B" ->{
                        notaFinal = notaBase + 0.5
                    }
                    "R" ->{
                        notaFinal = notaBase
                    }
                    "M" ->{
                        notaFinal = notaBase - 0.5
                    } else -> {notaFinal = 4.9}
                }

                if (notaFinal > 10) {
                    notaFinal = 10.0
                } else
                    if (notaFinal < 0){
                    notaFinal = 0.0
                }

                val intent = Intent(this, SecondActivity::class.java)

                intent.putExtra("Extra_notaFinal", notaFinal)

                startActivity(intent)
            }


        }
        binding.radioGroupAsistencia.setOnCheckedChangeListener { group, checkedId ->

            if (checkedId == R.id.radioboton_asistencia_buena){
                asistenciaSeleccionada = "B"
            } else  if (checkedId == R.id.radioboton_asistencia_regular){
                asistenciaSeleccionada = "R"
            } else  if (checkedId == R.id.radioboton_asistencia_mala){
                asistenciaSeleccionada = "M"
            } else { asistenciaSeleccionada = ""}

        }
    }
}




















