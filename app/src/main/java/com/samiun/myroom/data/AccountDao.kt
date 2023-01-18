package com.samiun.myroom.data
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAccount(account: Account)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addHistory(history: History)

    @Query("Select * from account_table order by firstName")
    fun readAllAccount(): LiveData<List<Account>>

    @Query("select * from history_table order by id")
    fun readHistoryData(): LiveData<List<History>>

    @Update
    suspend fun updateAccount(account: Account)

    @Delete
    suspend fun deleteAccount(account: Account)

    @Query("select * from account_table where phoneNumber= :number and password= :password")
    suspend fun LogIn(number: Int, password: Int): Account?

    @Query("select * from account_table where phoneNumber= :number")
    suspend fun transactionNumber(number: Int): Account?

    @Query("select * from history_table where myNumber= :number")
    fun myHistory(number: Int): LiveData<List<History>>
}