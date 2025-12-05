package com.example.loginappmi

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.loginappmi.databinding.ActivityMainBinding
import com.example.loginappmi.model.Usuario
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        acciones()
    }

    private fun acciones() {

        binding.spinerPlataformas.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                binding.imagenGoogle.visibility = View.INVISIBLE
                binding.imagenGit.visibility = View.INVISIBLE
                binding.imagenFacebook.visibility = View.INVISIBLE

                when(position){
                    1 ->{ binding.imagenGoogle.visibility = View.VISIBLE }
                    2 ->{ binding.imagenGit.visibility = View.VISIBLE }
                    3 ->{ binding.imagenFacebook.visibility = View.VISIBLE }
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        binding.botonLogin.setOnClickListener {
            if (binding.editTextPass.text.isBlank() || binding.editTextCorreo.text.isBlank()){

                Snackbar.make(binding.root, "Faltan DATOS", Snackbar.LENGTH_SHORT).show()
            } else {
                if (binding.editTextCorreo.text.toString().equals("admin", true) && binding.editTextPass.text.toString().equals("admin") ){
                    val intent = Intent(this, SecondActivity::class.java)

                    val usuario: Usuario = Usuario(binding.editTextCorreo.text.toString(),
                            binding.editTextPass.text.toString(), binding.spinerPlataformas.selectedItem.toString())

                    intent.putExtra("Extra_usuario", usuario)

                    startActivity(intent)



                }
            }
        }

        }













    override fun onItemSelected(
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long
    ) {
        TODO("Not yet implemented")
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }


}