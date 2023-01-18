package com.samiun.myroom.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.samiun.myroom.R
import com.samiun.myroom.data.History
import com.samiun.myroom.viewmodel.AccountViewModel

class AccountAdapter(context: Context, private val viewModel: AccountViewModel, listHistory:List<History>): RecyclerView.Adapter<AccountAdapter.AccountViewHolder>() {

    private var listOfHistory = listHistory
    class AccountViewHolder(private val binding: View): RecyclerView.ViewHolder(binding){
        val tnxid : TextView = binding.findViewById(R.id.transaction_tv)
        val toNumber: TextView = binding.findViewById(R.id.to_number_tv)
        val amount : TextView = binding.findViewById(R.id.amount_tv)
        val date: TextView = binding.findViewById(R.id.date_text)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.history_list,parent, false)
        return AccountViewHolder(root)
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        val history = listOfHistory[position]
        Log.v("Adapter Fragment",history.toString())

        holder.tnxid.text= "Id: "+history.id.toString()
        holder.toNumber.text= "To Number : "+history.sendNumber.toString()
        holder.amount.text = "Amount : "+history.amount.toString()
        holder.date.text = history.date
    }

    override fun getItemCount(): Int {
        Log.v("History Item Count Adapter", "Count"+listOfHistory.size.toString())
        return listOfHistory.size
    }
    fun setData(history: List<History>){
        listOfHistory= history
        Log.v("Account Adapter", "Size of data "+listOfHistory.size.toString())
        notifyDataSetChanged()
    }



}