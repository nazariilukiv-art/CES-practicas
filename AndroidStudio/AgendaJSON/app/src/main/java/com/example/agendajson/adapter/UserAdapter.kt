package com.example.agendajson.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.agendajson.R
import com.example.agendajson.databinding.ItemUserCardBinding
import com.example.agendajson.model.User

class UserAdapter(var context: Context, var isFavorites: Boolean = false) : RecyclerView.Adapter<UserAdapter.MyHolder>() {

    private var lista: ArrayList<User>
    private lateinit var listener: OnItemUserListener

    inner class MyHolder(var binding: ItemUserCardBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            if (isFavorites) {
                binding.toolbarCard.inflateMenu(R.menu.user_menu_fav)
            } else {
                binding.toolbarCard.inflateMenu(R.menu.user_menu)
            }

            binding.toolbarCard.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.menu_user_detalle -> {
                        listener.onUserDetailSelected(lista[bindingAdapterPosition])
                    }
                    R.id.menu_user_fav -> {
                        listener.onUserFavSelected(lista[bindingAdapterPosition])
                    }
                    R.id.menu_user_delete -> {
                        listener.onUserDeleteSelected(lista[bindingAdapterPosition])
                    }
                }
                return@setOnMenuItemClickListener true
            }
        }
    }

    init {
        lista = ArrayList()
        if (context is OnItemUserListener) {
            listener = context as OnItemUserListener
        }
    }

    fun clearUsers(): Unit {
        lista.clear()
        notifyDataSetChanged()
    }

    fun addUSer(user: User): Unit {
        this.lista.add(user)
        notifyItemInserted(lista.size - 1)
    }

    fun setList(newList: ArrayList<User>) {
        this.lista = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyHolder {
        val binding = ItemUserCardBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return MyHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MyHolder,
        position: Int
    ) {
        val user = lista[position]
        holder.binding.textoCard.text = user.email
        holder.binding.toolbarCard.title = user.firstName
        Glide.with(context).load(user.image).into(holder.binding.imagenCard)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    interface OnItemUserListener{
        fun onUserDetailSelected(user: User)
        fun onUserFavSelected(user: User)
        fun onUserDeleteSelected(user: User)
    }
}