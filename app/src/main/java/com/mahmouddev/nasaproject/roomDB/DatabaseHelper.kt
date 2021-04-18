package com.mahmouddev.nasaproject.roomDB

import com.mahmouddev.nasaproject.roomDB.entities.Asteroid
import com.mahmouddev.nasaproject.roomDB.entities.DailyImage


interface DatabaseHelper {
    suspend fun getAllAsteroid(): List<Asteroid>

    suspend fun insertAllAsteroid(list: List<Asteroid>)

    suspend fun insertAsteroid(asteroid: Asteroid)

    suspend fun getDailyImage(): DailyImage?

    suspend fun insertDailyImage(dailyImage: DailyImage)


}