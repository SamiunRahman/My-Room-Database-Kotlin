package com.samiun.myroom

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.samiun.myroom.adapter.AccountAdapter
import com.samiun.myroom.databinding.FragmentHistoryBinding
import com.samiun.myroom.viewmodel.AccountViewModel

class HistoryFragment : Fragment() {

    val getArgs : HomeFragmentArgs by navArgs()


    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private val layoutManager = LinearLayoutManager(context)
    private lateinit var viewModel: AccountViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val getAccount = getArgs.account


        viewModel = ViewModelProvider(requireActivity())[AccountViewModel::class.java]
        if (getAccount != null) {
            viewModel.myHistory(getAccount.phoneNumber)
        }
        recyclerView= binding.recyclerViewHistory
        recyclerView.setHasFixedSize(true)


        recyclerView.layoutManager= layoutManager

        viewModel.myHistory.observe(viewLifecycleOwner){
            val adapter = AccountAdapter(requireContext(),viewModel,it)
            recyclerView.adapter=adapter
    //            adapter.setData(it)

        }
    }

}