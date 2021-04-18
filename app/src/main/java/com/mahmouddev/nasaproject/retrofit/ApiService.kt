package com.mahmouddev.nasaproject.retrofit

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("neo/rest/v1/feed")
    suspend fun getData(
        @Query("start_date") startDate: String,
        @Query("api_key") key: String = "EWMHtoskp7E0HC3scYRQGWJJeynBfDIvfbkMctJx",
    ): ResponseBody

    @GET("planetary/apod")
    suspend fun getImage(
        @Query("api_key") key: String = "EWMHtoskp7E0HC3scYRQGWJJeynBfDIvfbkMctJx"
    ): DailyImage


}