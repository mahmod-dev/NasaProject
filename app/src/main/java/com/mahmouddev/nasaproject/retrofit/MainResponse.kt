package com.mahmouddev.nasaproject.retrofit



data class DailyImage(
    val date: String,
    val explanation: String,
    val media_type: String,
    val service_version: String,
    val title: String,
    val url: String
)

