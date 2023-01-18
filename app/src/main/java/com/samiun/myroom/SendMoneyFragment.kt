package com.samiun.myroom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.samiun.myroom.data.Account
import com.samiun.myroom.data.History
import com.samiun.myroom.viewmodel.AccountViewModel
import kotlinx.android.synthetic.main.fragment_send_money.*
import java.text.SimpleDateFormat
import java.util.*
import javax.sql.DataSource

class SendMoneyFragment : Fragment() {
    val getArgs: SendMoneyFragmentArgs by navArgs()
    private lateinit var viewModel: AccountViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_send_money, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[AccountViewModel::class.java]

        val getAccount = getArgs.account
        send_btn.setOnClickListener {
            val number = send_num.text.toString()
            val amount = send_amount.text.toString()
            viewModel.transactionNumber(number.toInt())
            val receiver = viewModel.receiver

            if(number.length<6 && receiver==null){
                Toast.makeText(requireContext(), "Enter a proper receivers number!", Toast.LENGTH_SHORT).show()

            }

            else if(number.length>5 && amount.toInt()<getAccount!!.balance && receiver!=null ){
                val balance = getAccount.balance-amount.toDouble()
                val receiverBalance = receiver.balance+amount.toDouble()
                val history = History(0,getAccount.phoneNumber,number.toInt(),amount.toDouble(),getCurrentDate())
                val account = Account(getAccount.phoneNumber,getAccount.image,getAccount.password,getAccount.firstName,getAccount.lastName,balance)
                val receiver =Account(receiver.phoneNumber,getAccount.image,receiver.password,receiver.firstName,receiver.lastName,receiverBalance)
                viewModel.updateUser(account)
                viewModel.updateUser(receiver)
                viewModel.addHistory(history)
                val action = SendMoneyFragmentDirections.actionSendMoneyFragmentToHomeFragment(account)
                findNavController().navigate(action)

            }

            else{
                Toast.makeText(requireContext(), "You dont have enough balance!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getCurrentDate(): String{
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val currentDate = sdf.format(Date())
        return currentDate.toString()
    }


}