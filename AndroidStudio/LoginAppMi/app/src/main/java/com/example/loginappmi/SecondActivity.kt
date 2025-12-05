package com.example.loginappmi

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginappmi.databinding.ActivityMainBinding
import com.example.loginappmi.databinding.ActivitySecondBinding
import com.example.loginappmi.model.Usuario

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    var usuario: Usuario? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)


        usuario = intent.getSerializableExtra("Extra_usuario") as Usuario

        binding.textoSaludo.text = "Bienvendo  ${usuario?.correo}"
        binding.textoPlataforma.text = "Sesion iniciada con ${usuario?.plataforma}"
    }
}