package com.samiun.myroom.data
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Account::class, History::class], version = 4, exportSchema = false)
abstract class AccountDatabase: RoomDatabase() {
    abstract fun accountDao(): AccountDao

    companion object{
        @Volatile
        private var INSTANCE: AccountDatabase?= null

        fun getDatabase(context: Context): AccountDatabase{
            val tempInstance = INSTANCE
            if(tempInstance!= null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AccountDatabase::class.java,
                    "account_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE= instance
                return instance
            }
        }
    }
}