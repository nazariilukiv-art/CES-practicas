package com.example.practicaagendajson

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.toolbox.JsonObjectRequest
import com.example.practicaagendajson.adapter.UserAdapter
import com.example.practicaagendajson.databinding.ActivityMainBinding
import com.example.practicaagendajson.model.User
import com.google.gson.Gson
import com.google.gson.JsonObject
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserAdapter
    private val urlBase = "https://dummyjson.com/users"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        instancias() //para adapter
        initGUI() // para grafica
        realizarPeticionJSON(urlBase) //para JSON
    }

    private fun realizarPeticionJSON(urlBase: String) {
        val peticionJSON = JsonObjectRequest(
            urlBase,
            {
                val gson = Gson()
                val usersArray: JSONArray = it.getJSONArray("users")
                for (i in 0..usersArray.length()-1) {
                    val userJSON = usersArray.getJSONObject(i)
                    val user: User = gson.fromJson(userJSON.toString(), User::class.java)

                    // HE TERMINADO AQUI NO TENGO FUNCION addUSer
                    // y no funciona adapter.addUSer(user)
                    // HAY QUE MIRAR QUE PASA
                }
            },
            {

            }
        )
    }


    private fun instancias() {
        adapter = UserAdapter(this)
    }

    private fun initGUI() {
        // Activar superpoderes es decir mostrar titulo, menu con puntitos etc...
        setSupportActionBar(binding.toolbar)

        // decimos quien manda en este caso adapter
        binding.recycleUsers.adapter = adapter

        //decimos que posicion van a tener verticas o horizontal
        binding.recycleUsers.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }





}