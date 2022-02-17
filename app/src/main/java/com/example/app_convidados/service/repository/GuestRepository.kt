package com.example.app_convidados.service.repository

import android.content.ContentValues
import android.content.Context
import com.example.app_convidados.service.DataBaseConstants
import com.example.app_convidados.service.model.GuestModel
import java.lang.Exception

class GuestRepository private constructor(context: Context) {

    private var mGuestDataBaseHelper: GuestDataBaseHelper = GuestDataBaseHelper(context)

    /**
     * Padrão Singleton - Para ter um instancia da classe de repository, evitando assim problemas futuros de conexões com o banco de dados
     * 1- Privar o contrutor da nossa classe
     * 2-Criamos uma conexão com o nosso banco
     * 3-Criar um método estatico responsável por retornar a instancia da nossa classe
     */

    companion object {

        private lateinit var repository: GuestRepository

        fun getIntance(context: Context): GuestRepository {
            if (!::repository.isInitialized) {
                repository = GuestRepository(context)
            }
            return repository
        }
    }


    /**
     * Métodos do banco de dados de Salvar,Atualizar e exlcuir
     */

    fun save(guest: GuestModel): Boolean {
        return try {
            val db = mGuestDataBaseHelper.writableDatabase

            val contentValues = ContentValues()

            contentValues.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence)


            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, contentValues)
            true
        } catch (e: Exception) {
            false
        }

    }

    fun update(guest: GuestModel): Boolean {

        return try {
            val db = mGuestDataBaseHelper.writableDatabase

            val contentValues = ContentValues()

            contentValues.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            contentValues.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.presence)

            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(guest.id.toString())


            db.update(DataBaseConstants.GUEST.TABLE_NAME, contentValues, selection, args)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun delete(id: Int): Boolean {
        return try {
            val db = mGuestDataBaseHelper.writableDatabase
            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())

            db.delete(DataBaseConstants.GUEST.TABLE_NAME, selection, args)
            true
        } catch (e: Exception) {
            false
        }
    }


    /**
     * Métodos para listar os nossos usuários por presença ou ausencia e a lista de todos os convidados...
     */

    fun getUser(id: Int): GuestModel? {

        var guest: GuestModel? = null
        return try {
            val db = mGuestDataBaseHelper.readableDatabase

            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )
            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
            val args = arrayOf(id.toString())

            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME, projection, selection, args, null, null, null
            )

            if (cursor != null && cursor.count > 0) {
                cursor.moveToFirst()

                val name =
                    cursor.getString(cursor.getColumnIndexOrThrow(DataBaseConstants.GUEST.COLUMNS.NAME))
                val presence =
                    (cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                guest = GuestModel(id, name, presence)

            }
            cursor?.close()
            guest
        } catch (e: Exception) {
            guest
        }
    }

    fun getAll(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return try {
            val db = mGuestDataBaseHelper.readableDatabase

            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )

            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME, projection, null, null, null, null, null
            )

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id =
                        cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name =
                        cursor.getString(cursor.getColumnIndexOrThrow(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence =
                        (cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                    val guest = GuestModel(id, name, presence)
                    list.add(guest)
                }

            }
            cursor?.close()
            list
        } catch (e: Exception) {
            list
        }
    }

    fun getPresent(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return try {
            val db = mGuestDataBaseHelper.readableDatabase

            val cursor = db.rawQuery("SELECT id,name,presence FROM Guest WHERE presence = 1",null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id =
                        cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name =
                        cursor.getString(cursor.getColumnIndexOrThrow(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence =
                        (cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                    val guest = GuestModel(id, name, presence)
                    list.add(guest)
                }

            }
            cursor?.close()
            list
        } catch (e: Exception) {
            list
        }
    }

    fun getAbsent(): List<GuestModel> {
        val list: MutableList<GuestModel> = ArrayList()
        return try {
            val db = mGuestDataBaseHelper.readableDatabase

            val cursor = db.rawQuery("SELECT id,name,presence FROM Guest WHERE presence = 0",null)

            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id =
                        cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name =
                        cursor.getString(cursor.getColumnIndexOrThrow(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence =
                        (cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                    val guest = GuestModel(id, name, presence)
                    list.add(guest)
                }

            }
            cursor?.close()
            list
        } catch (e: Exception) {
            list
        }
    }


}