package com.mahmouddev.nasaproject.retrofit

import okhttp3.ResponseBody

class ApiImp(private val apiService: ApiService) : ApiHelper {
    override suspend fun getData(startDate: String): ResponseBody {
        return apiService.getData(startDate)
    }

    override suspend fun getImage(): DailyImage {
        return apiService.getImage()
    }
}