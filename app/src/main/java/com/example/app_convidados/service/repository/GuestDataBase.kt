package com.example.app_convidados.service.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.app_convidados.service.model.GuestModel


@Database(entities = [GuestModel::class], version = 1)
abstract class GuestDataBase : RoomDatabase(){

    abstract fun guestDAO() : GuestDAO

    companion object{
        /**
         * Singleton
         */

        private lateinit var INSTANCE : GuestDataBase
        fun getDataBase(context: Context) : GuestDataBase{

            if(!::INSTANCE.isInitialized){
                synchronized(GuestDataBase::class.java){
                    //Criação do nosso banco
                    INSTANCE = Room.databaseBuilder(context,GuestDataBase::class.java,"guest.db")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }
    }

}