package com.example.app_convidados.view.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app_convidados.R
import com.example.app_convidados.service.model.GuestModel

class GuestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    /**
     *  Nossa classe GuestViewHolder é a responsável por armazenar os nossos elementos de interfaces
     */

    fun bind(guestModel: GuestModel) {
        val textName = itemView.findViewById<TextView>(R.id.text_name)
        textName.text = guestModel.name
    }

}