package com.example.app_convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app_convidados.service.constants.GuestConstants
import com.example.app_convidados.service.model.GuestModel
import com.example.app_convidados.service.repository.GuestRepository

class AllGuestViewModel(application: Application) : AndroidViewModel(application) {

    private val mRepository = GuestRepository(application.applicationContext)

    private val mListGuest = MutableLiveData<List<GuestModel>>()
    val list: LiveData<List<GuestModel>> = mListGuest

    fun load(filter: Int) {

        if(filter == GuestConstants.FILTER.EMPTY){
            mListGuest.value = mRepository.getAll()
        }else if(filter == GuestConstants.FILTER.PRESENT){
            mListGuest.value = mRepository.getPresent()
        }else{
            mListGuest.value = mRepository.getAbsent()
        }

    }

    fun delete(id:Int) {
        val guest = mRepository.getUser(id)
         mRepository.delete(guest)
    }
}