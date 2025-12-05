package com.example.miimcapp

import android.content.Intent
import android.os.Bundle
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.miimcapp.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding // creamos binding

    private var sexoActual: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // inicializamos
        setContentView(binding.root) // conectamos

        acciones()
    }

    private fun acciones() {
        binding.botonCalcular.setOnClickListener {

            val peso = binding.editTextPeso.text.toString()
            val altura = binding.editTextAltura.text.toString()

            if (sexoActual.isBlank() || peso.isBlank() || altura.isBlank()){
                Snackbar.make(binding.root, "Te faltan Datos a rellenar", Snackbar.LENGTH_LONG).show()
            } else {
                val imc = peso.toDouble() / (altura.toDouble()*altura.toDouble())

                val intent = Intent(this, SecondActivity::class.java)

                intent.putExtra("EXTRA_IMC", imc)
                intent.putExtra("EXTRA_SEXO", sexoActual)

                startActivity(intent)
            }

        }
        binding.radioGroupSexo.setOnCheckedChangeListener{ RadioGroup, checkedId ->

            if (checkedId == R.id.radio_boton_masculino){
                sexoActual = "M"
            } else if (checkedId == R.id.radio_boton_femenino){
                sexoActual = "F"
            }else{
                Snackbar.make(binding.root, "Seleccione sexo", Snackbar.LENGTH_LONG).show()
            }
        }
    }
}