package com.samiun.myroom.data

import androidx.lifecycle.LiveData

class AccountRepository(private val accountDao: AccountDao) {
    val readAllData : LiveData<List<Account>> = accountDao.readAllAccount()
    val readHistoryData: LiveData<List<History>> = accountDao.readHistoryData()

    suspend fun addAccount(account: Account){
        accountDao.addAccount(account)
    }
    suspend fun addHistory(history: History){
        accountDao.addHistory(history)
    }
    suspend fun deleteAccount(account: Account){
        accountDao.deleteAccount(account)
    }

    suspend fun updateAccount(account: Account){
        accountDao.updateAccount(account)
    }

    suspend fun LogIn(number: Int, password: Int): Account? {
        return accountDao.LogIn(number,password)
    }

    suspend fun transactionNumber(number: Int): Account? {
        return accountDao.transactionNumber(number)

    }

    fun myHistory(number: Int): LiveData<List<History>> {
        return accountDao.myHistory(number)
    }
}