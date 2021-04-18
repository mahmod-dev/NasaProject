package com.mahmouddev.nasaproject.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mahmouddev.nasaproject.roomDB.dao.DaoAsteroid
import com.mahmouddev.nasaproject.roomDB.dao.DaoDailyImages
import com.mahmouddev.nasaproject.roomDB.entities.Asteroid
import com.mahmouddev.nasaproject.roomDB.entities.DailyImage

@Database(entities = [Asteroid::class,DailyImage::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun daoAsteroid(): DaoAsteroid
    abstract fun daoDailyImages(): DaoDailyImages

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = initDatabase(context)

            }
            return INSTANCE!!
        }

        private fun initDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "myDb").build()
        }
    }


}