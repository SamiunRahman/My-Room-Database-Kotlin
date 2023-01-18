package com.samiun.myroom

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.samiun.myroom.data.Account
import com.samiun.myroom.viewmodel.AccountViewModel
import kotlinx.android.synthetic.main.fragment_log_in.*

class LogInFragment : Fragment() {

    private lateinit var viewModel: AccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[AccountViewModel::class.java]

        create_btn.setOnClickListener {
            val action = LogInFragmentDirections.actionLogInFragmentToAddFragment()
            findNavController().navigate(action)
        }

        login_btn.setOnClickListener {
            val number= login_number.text.toString()
            val password = login_password.text.toString()
            if(number.length>5 && password.length>3){

               viewModel.logIn(number.toInt(), password.toInt())
                val account = viewModel.user

                if(account !=null){
                        val action = LogInFragmentDirections.actionLogInFragmentToHomeFragment(account)
                        Log.v("Log In fragment", account.firstName)
                        findNavController().navigate(action)
                }

            }
            else{
                Toast.makeText(requireContext(), "Enter a proper Number and Password", Toast.LENGTH_SHORT).show()
            }
        }
    }


}