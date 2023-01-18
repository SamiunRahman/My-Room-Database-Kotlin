
package com.samiun.myroom.data
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
@Parcelize
@Entity(tableName = "history_table")
data class History(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val myNumber: Int,
    val sendNumber: Int,
    val amount: Double,
    val date: String
): Parcelable {
}