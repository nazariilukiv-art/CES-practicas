package com.example.examdgt

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.SnapHelper
import com.example.examdgt.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var imagen: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        acciones()
    }

    private fun acciones() {


        binding.radioGroupTipo.setOnCheckedChangeListener { group, checkedId ->

            if (checkedId == R.id.radio_buton_hibrido){
                imagen = R.drawable.hibrido
            } else if (checkedId == R.id.radio_buton_electrico){
                imagen = R.drawable.electrico
            } else if (checkedId == R.id.radio_buton_disel){
                imagen = R.drawable.diesel
            }else{imagen = R.drawable.gasolina}
        }

        binding.botonRevisar.setOnClickListener {

            if (
                binding.editNombreApellido.text.toString().isBlank() ||
                binding.editMatriculaCoche.text.toString().isBlank() ||
                binding.editAnoDeMatriculacion.text.toString().isBlank()
                ){
                Snackbar.make(binding.root, "Faltan DATOS", Snackbar.LENGTH_SHORT).show()
            }else {
                val nombreApellido = binding.editNombreApellido.text.toString()
                val matriculaCoche = binding.editMatriculaCoche.text.toString()
                val anioMatriculacion = binding.editAnoDeMatriculacion.text.toString()
                val autonomia = binding.editAutonomia.text.toString()



                val intent = Intent(this, SecondActivity::class.java)

                intent.putExtra("EnombreApellido", nombreApellido)
                intent.putExtra("EmatriculaCoche", matriculaCoche)
                intent.putExtra("EanioMatriculacion", anioMatriculacion.toDouble())
                intent.putExtra("Eautonomia", autonomia)
                intent.putExtra("Imagen",imagen)

                startActivity(intent)

            }

        }


    }
}









