package com.example.agendajson

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.agendajson.adapter.UserAdapter
import com.example.agendajson.model.DataSet
import com.example.agendajson.model.User
import com.example.agendajson.ui.dialog.DialogUser
import com.google.android.material.snackbar.Snackbar

class FavoritesActivity : AppCompatActivity(), UserAdapter.OnItemUserListener {

    private lateinit var recycler: RecyclerView
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        recycler = findViewById(R.id.recycler_favorites)
        adapter = UserAdapter(this, true)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        adapter.setList(DataSet.listaFavoritos)
    }

    override fun onUserDetailSelected(user: User) {
        val dialogo: DialogUser = DialogUser.newInstance(user)
        dialogo.show(supportFragmentManager, null)
    }

    override fun onUserFavSelected(user: User) {
    }

    override fun onUserDeleteSelected(user: User) {
        DataSet.listaFavoritos.remove(user)
        adapter.setList(DataSet.listaFavoritos)
        Snackbar.make(recycler, "Eliminado de favoritos", Snackbar.LENGTH_SHORT).show()
    }
}