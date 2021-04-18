package com.mahmouddev.nasaproject.roomDB.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity(tableName = "dailyImage_tb")
@Parcelize
data class DailyImage(
    val title: String,
    val url: String,
    val mediaType: String
) : Parcelable {

    @PrimaryKey
    var id: Int = 1 // need to single record
}