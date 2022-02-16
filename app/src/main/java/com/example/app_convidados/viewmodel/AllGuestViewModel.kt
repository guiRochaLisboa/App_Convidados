package com.example.app_convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app_convidados.service.model.GuestModel
import com.example.app_convidados.service.repository.GuestRepository

class AllGuestViewModel(application: Application) : AndroidViewModel(application) {

    private val mGuestRepository = GuestRepository.getIntance(application.applicationContext)

    private val mGuestList = MutableLiveData<List<GuestModel>>()
    val guestList: LiveData<List<GuestModel>> = mGuestList

    fun load() {
        mGuestList.value = mGuestRepository.getAll()
    }


}