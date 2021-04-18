package com.mahmouddev.nasaproject.roomDB.dao

import androidx.room.*
import com.mahmouddev.nasaproject.roomDB.entities.DailyImage

@Dao
interface DaoDailyImages {
    @Query("SELECT * FROM dailyImage_tb")
    suspend fun getDailyImage(): DailyImage?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDailyImage(dailyImage: DailyImage)



}