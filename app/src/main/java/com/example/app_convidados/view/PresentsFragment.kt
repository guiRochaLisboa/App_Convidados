package com.example.app_convidados.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app_convidados.R
import com.example.app_convidados.databinding.FragmentPresentBinding
import com.example.app_convidados.service.constants.GuestConstants
import com.example.app_convidados.view.adapter.GuestAdapter
import com.example.app_convidados.view.listener.GuestListener
import com.example.app_convidados.viewmodel.AllGuestViewModel

class PresentsFragment : Fragment() {

    private var _binding: FragmentPresentBinding? = null
    private lateinit var mViewModel: AllGuestViewModel
    private val mAdapter: GuestAdapter = GuestAdapter()
    private lateinit var mListener: GuestListener

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        mViewModel =
            ViewModelProvider(this).get(AllGuestViewModel::class.java)

        _binding = FragmentPresentBinding.inflate(inflater, container, false)
        val root: View = binding.root


        // 1- Obter a recycler
        val recycler = root.findViewById<RecyclerView>(R.id.recycler_present)

        //2- Definir um layout de como a nossa recycler se comporta na tela
        recycler.layoutManager = LinearLayoutManager(context)

        /**3- Definir um Adapter, adapter é algo que vai servir como cola
         * onde ele pega os dados do repositório e junta com o nosso layout
         * podendo assim renderizar as nossas informações em tela
         */
        recycler.adapter = mAdapter


        mListener = object : GuestListener {
            override fun onClick(id: Int) {

                val intent = Intent(context, GuestFormActivity::class.java)

                val bundle = Bundle()
                bundle.putInt(GuestConstants.GUEST_ID, id)

                intent.putExtras(bundle)
                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                mViewModel.delete(id)
                mViewModel.load(GuestConstants.FILTER.PRESENT)
            }

        }

        mAdapter.attachListener(mListener)

        observe()
        mViewModel.load(GuestConstants.FILTER.PRESENT)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        mViewModel.load(GuestConstants.FILTER.PRESENT)
    }

    private fun observe() {
        mViewModel.list.observe(viewLifecycleOwner, Observer {
            mAdapter.updateGuest(it)
        })

    }
}