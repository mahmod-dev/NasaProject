package com.mahmouddev.nasaproject.roomDB.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "Asteroid_tb")
@Parcelize
data class Asteroid(
    @PrimaryKey()
    var id: Int,
    var name: String? = null,
    var closeApproachDate: String? = null,
    var magnitude: Double? = null,
    var estimatedDiameter: Double? = null,
    var distanceFromEarth: Double? = null,
    var kilometersPerSecond: Double? = null,
    var hazardous: Boolean? = null
):Parcelable