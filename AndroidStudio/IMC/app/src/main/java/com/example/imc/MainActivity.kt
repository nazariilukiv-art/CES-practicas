package com.example.imc

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.imc.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    //Binding
    private lateinit var binding: ActivityMainBinding
    //Para guardar seleccion
    private var sexo: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        acciones()
    }

    private fun acciones() {
        binding.botonEvaluar.setOnClickListener {

            val peso = binding.editTextPeso.text.toString()
            val altura = binding.editTextAltura.text.toString()

            if(peso.isBlank() || altura.isBlank() || sexo.isBlank()){
                Snackbar.make(binding.root, "faltan datos", Snackbar.LENGTH_SHORT).show()
            }else{
                try {
                    val peso = peso.toDouble()
                    val altura = altura.toDouble()

                    // Calculamos
                    val imc = peso / (altura * altura)

                    // Enviamos desde aqui a Segunda pagina
                    val intent = Intent(this, SecondActivity::class.java)

                    // Decimos como se llaman
                    intent.putExtra("EXTRA_IMC", imc)
                    intent.putExtra("EXTRA_SEXO", sexo)
                    startActivity(intent)

                }catch (e: NumberFormatException){
                    Snackbar.make(binding.root, "Los numeros introducidos no son validos", Snackbar.LENGTH_SHORT).show()
                }
            }


        }
        binding.radioGroupSexo.setOnCheckedChangeListener { group, checkedId ->
            sexo = when (checkedId){
                R.id.radio_button_masculino -> "M"
                R.id.radio_button_femenino -> "F"
                else -> ""
            }
        }
    }

}