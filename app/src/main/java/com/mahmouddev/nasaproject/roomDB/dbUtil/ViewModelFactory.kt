package com.mahmouddev.nasaproject.roomDB.dbUtil

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mahmouddev.nasaproject.retrofit.ApiHelper
import com.mahmouddev.nasaproject.roomDB.DatabaseHelper
import com.mahmouddev.nasaproject.viewModel.MainViewModel

class ViewModelFactory(
    private val apiHelper: ApiHelper,
    private val databaseHelper: DatabaseHelper
) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {


        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(apiHelper, databaseHelper) as T
        }

        throw IllegalArgumentException("Unknown class name")
    }


}