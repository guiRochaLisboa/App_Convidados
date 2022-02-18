package com.example.app_convidados.view.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app_convidados.R
import com.example.app_convidados.service.model.GuestModel
import com.example.app_convidados.view.listener.GuestListener

class GuestViewHolder(itemView: View,private val listener: GuestListener) : RecyclerView.ViewHolder(itemView) {


    fun bind(guest: GuestModel) {
        val textName = itemView.findViewById<TextView>(R.id.text_name)
        textName.text = guest.name

        textName.setOnClickListener {
            listener.onClick(guest.id)
        }
    }

}