package com.example.app_convidados.service.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.app_convidados.service.DataBaseConstants

class GuestDataBaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    /**
     * Classe responsável pelo criação do banco da nossa aplicação
     */

    override fun onCreate(db: SQLiteDatabase) {
    db.execSQL(CREATE_TABLE_GUEST)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "Convidados.db"

        private val CREATE_TABLE_GUEST =
            ("create table " + DataBaseConstants.GUEST.TABLE_NAME + "( "
                    + DataBaseConstants.GUEST.COLUMNS.ID + "interger primary key autoincrement, "
                    + DataBaseConstants.GUEST.COLUMNS.NAME + "text, "
                    + DataBaseConstants.GUEST.COLUMNS.PRESENCE + "interger);")
    }

}