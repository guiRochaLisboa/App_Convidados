package com.example.app_convidados.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.app_convidados.R
import com.example.app_convidados.viewmodel.GuestFormViewModel
import kotlinx.android.synthetic.main.activity_guest_form.*

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    /**
     * Activity responsável pelo cadastro dos convidados.
     */

    private lateinit var mViewModel: GuestFormViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_form)

        mViewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        setListener()
        observe()
    }

    override fun onClick(v: View) {
        val id = v.id
        if(id == R.id.button_save){
            val name = edit_name.text.toString()
            val presence = button_present.isChecked

            mViewModel.save(name,presence)
        }
    }

    private fun observe() {
        mViewModel.saveGuest.observe(this, Observer {
            if(it){
                Toast.makeText(applicationContext,"Sucesso",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(applicationContext,"Falha",Toast.LENGTH_SHORT).show()
            }
            finish()

        })
    }

    private fun setListener() {
       button_save.setOnClickListener(this)
    }

}