package com.samiun.myroom.viewmodel
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.samiun.myroom.data.Account
import com.samiun.myroom.data.AccountDatabase
import com.samiun.myroom.data.AccountRepository
import com.samiun.myroom.data.History
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
class AccountViewModel(application: Application): AndroidViewModel(application) {
    val readAlldata : LiveData<List<Account>>
    val readHistorydata: LiveData<List<History>>
    var user: Account? =null
    var receiver: Account? =null
    lateinit var myHistory: LiveData<List<History>>



    private val repository : AccountRepository

    init {
        val accountDao = AccountDatabase.getDatabase(application).accountDao()
        repository = AccountRepository(accountDao)
        readAlldata = repository.readAllData
        readHistorydata = repository.readHistoryData
    }

    fun addAccount(account: Account){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addAccount(account)
        }
    }

    fun addHistory(history: History){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addHistory(history)
        }
    }

    fun updateUser(account: Account){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateAccount(account)
        }
    }

    fun deleteUser(account: Account){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAccount(account)
        }
    }

    fun logIn(number: Int, passWord: Int) {
        viewModelScope.launch(Dispatchers.IO){
            user= repository.LogIn(number,passWord)
        }
    }

    fun transactionNumber(number: Int) {
        viewModelScope.launch(Dispatchers.IO){
            receiver= repository.transactionNumber(number)
        }
    }

    fun myHistory(number: Int) {

            myHistory= repository.myHistory(number)

    }




}