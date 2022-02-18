package com.example.app_convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.app_convidados.service.model.GuestModel
import com.example.app_convidados.service.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    private val mContext = application.applicationContext
    private val mGuestRepository: GuestRepository = GuestRepository.getIntance(mContext)

    private var mSaveGuest = MutableLiveData<Boolean>()
    val saveGuest: LiveData<Boolean> = mSaveGuest

    private var mUpdateGuest = MutableLiveData<Boolean>()
    val updateGuest: LiveData<Boolean> = mUpdateGuest

    private var mGuest = MutableLiveData<GuestModel>()
    val guest: LiveData<GuestModel> = mGuest

    fun save(name: String, presence: Boolean) {
        val guest = GuestModel(name = name, presence = presence)
       mSaveGuest.value = mGuestRepository.save(guest)
    }

    fun update(id: Int,name: String,presence: Boolean){
        val guest = GuestModel(id = id,name = name, presence = presence)
        mUpdateGuest.value = mGuestRepository.update(guest)

    }

    fun load(id:Int) {
        mGuest.value = mGuestRepository.getUser(id)
    }

}