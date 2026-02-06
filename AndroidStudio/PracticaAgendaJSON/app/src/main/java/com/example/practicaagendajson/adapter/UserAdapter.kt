package com.example.practicaagendajson.adapter

import android.content.Context
import android.os.Binder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practicaagendajson.databinding.ItemUserCardBinding
import com.example.practicaagendajson.model.User

class UserAdapter(var context: Context): RecyclerView.Adapter<UserAdapter.MyHolder>() {

    private var lista: ArrayList<User> = ArrayList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ItemUserCardBinding.inflate(inflater,parent,false)
        return MyHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MyHolder,
        position: Int
    ) {
        val usuarioActual = lista[position]
        holder.binding.textoCard.text = usuarioActual.email
        holder.binding.toolbarCard.title = "${usuarioActual.firstName} ${usuarioActual.lastName}"

        Glide.with(context).load(usuarioActual.image).into(holder.binding.imagenCard)

    }

    override fun getItemCount(): Int {
        return lista.size
    }

    inner class MyHolder(val binding: ItemUserCardBinding):
        RecyclerView.ViewHolder(binding.root)


}