package com.mahmouddev.nasaproject.roomDB.dao

import androidx.room.*
import com.mahmouddev.nasaproject.roomDB.entities.Asteroid

@Dao
interface DaoAsteroid {
    @Query("SELECT * FROM asteroid_tb ORDER BY closeApproachDate DESC")
    suspend fun getAllAsteroid(): List<Asteroid>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllAsteroid(list: List<Asteroid>)

    @Insert
    suspend fun insertAsteroid(asteroid: Asteroid)



}