package com.example.app_convidados.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_convidados.R
import com.example.app_convidados.service.model.GuestModel
import com.example.app_convidados.view.viewholder.GuestViewHolder

class GuestAdapter : RecyclerView.Adapter<GuestViewHolder>() {

    private var mGuestList: List<GuestModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.row_guest,parent,false)
        return GuestViewHolder(item)
    }

    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
       holder.bind(mGuestList[position])
    }

    override fun getItemCount(): Int {
      return  mGuestList.count()
    }

    fun updateGuest(list: List<GuestModel>){
        mGuestList = list
        notifyDataSetChanged()
    }
}