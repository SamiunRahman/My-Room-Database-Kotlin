package com.samiun.myroom.data
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
@Parcelize
@Entity(tableName = "account_table")
data class Account(
    @PrimaryKey
    val phoneNumber: Int,
    val image: ByteArray,
    val password: Int,
    val firstName: String,
    val lastName: String,
    val balance: Double): Parcelable {
}