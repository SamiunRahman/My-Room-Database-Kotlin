package com.samiun.myroom

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.samiun.myroom.viewmodel.AccountViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    val getArgs : HomeFragmentArgs by navArgs()
    private lateinit var viewModel: AccountViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[AccountViewModel::class.java]

        val getAccount = getArgs.account

        val options = BitmapFactory.Options()
        options.inMutable = true
        val bitmap = getAccount?.image?.let { BitmapFactory.decodeByteArray(getAccount.image, 0, it.size, options) }
        imageView.setImageBitmap(bitmap)


        home_name.text = "Welcome, ${getAccount?.firstName}"
        home_phone.text = getAccount?.phoneNumber.toString()
        home_balance.text = "Balance: $ "+getAccount?.balance


        home_send_btn.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToSendMoneyFragment(getAccount)
            findNavController().navigate(action)
        }
        home_history_btn.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToHistoryFragment(getAccount)
            findNavController().navigate(action)
        }

        home_logout_btn.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToLogInFragment()
            findNavController().navigate(action)
        }
        home_Update_btn.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAddFragment(getAccount)
            findNavController().navigate(action)

        }
        home_delete_btn.setOnClickListener {
            if (getAccount != null) {
                viewModel.deleteUser(getAccount)
            }
            val action = HomeFragmentDirections.actionHomeFragmentToLogInFragment()
            findNavController().navigate(action)
        }


    }

}