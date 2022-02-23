package com.example.app_convidados.service.repository

import android.content.ContentValues
import android.content.Context
import com.example.app_convidados.service.constants.DataBaseConstants
import com.example.app_convidados.service.model.GuestModel
import java.lang.Exception

class GuestRepository  constructor(context: Context) {

    private val mDataBase = GuestDataBase.getDataBase(context).guestDAO()
    /**
     * Métodos do banco de dados de Salvar,Atualizar e exlcuir
     */

    fun save(guest: GuestModel): Boolean {
        return mDataBase.save(guest) > 0
    }

    fun update(guest: GuestModel): Boolean {
        return mDataBase.update(guest) > 0
    }

    fun delete(guest: GuestModel) {
       return mDataBase.delete(guest)
    }


    /**
     * Métodos para listar os nossos usuários por presença ou ausencia e a lista de todos os convidados...
     */

    fun getUser(id: Int): GuestModel {
       return mDataBase.get(id)
    }

    fun getAll(): List<GuestModel> {
        return mDataBase.getInvited()
    }

    fun getPresent(): List<GuestModel> {
        return mDataBase.getPresent()
    }

    fun getAbsent(): List<GuestModel> {
        return mDataBase.getAbsent()
            }


}