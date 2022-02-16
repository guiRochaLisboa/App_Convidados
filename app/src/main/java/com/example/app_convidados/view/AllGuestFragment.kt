package com.example.app_convidados.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app_convidados.R
import com.example.app_convidados.databinding.FragmentAllBinding
import com.example.app_convidados.view.adapter.GuestAdapter
import com.example.app_convidados.viewmodel.AllGuestViewModel

class AllGuestFragment : Fragment() {

    private var _binding: FragmentAllBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(AllGuestViewModel::class.java)

        _binding = FragmentAllBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /**
         * Listagem de elementos com RecyclerView
         * 1- Obter a recycler
         * 2- Definir um layout
         * 3- Definir um Adapter
         */

        // 1- Obter a recycler
        val recycler = root.findViewById<RecyclerView>(R.id.recycler_all_guest)

        //2- Definir um layout de como a nossa recycler se comporta na tela
        recycler.layoutManager = LinearLayoutManager(context)

        /**3- Definir um Adapter, adapter é algo que vai servir como cola
         * onde ele pega os dados do repositório e junta com o nosso layout
         * podendo assim renderizar as nossas informações em tela
         */
        recycler.adapter = GuestAdapter()




        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}