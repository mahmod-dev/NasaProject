package com.mahmouddev.nasaproject.retrofit

import okhttp3.ResponseBody

interface ApiHelper {
    suspend fun getData(
        startDate: String,
    ): ResponseBody


    suspend fun getImage(): DailyImage

}