package com.mahmouddev.nasaproject.viewModel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.mahmouddev.nasaproject.api.parseAsteroidsJsonResult
import com.mahmouddev.nasaproject.retrofit.ApiHelper
import com.mahmouddev.nasaproject.roomDB.DatabaseHelper
import com.mahmouddev.nasaproject.roomDB.dbUtil.Resource
import com.mahmouddev.nasaproject.roomDB.entities.Asteroid
import com.mahmouddev.nasaproject.roomDB.entities.DailyImage
import kotlinx.coroutines.launch
import org.json.JSONObject

class MainViewModel(private val apiHelper: ApiHelper, private val dbHelper: DatabaseHelper) :
    ViewModel() {
    val TAG = "MainViewModel"
    private val asteroid = MutableLiveData<Resource<List<Asteroid>>>()
    private val image = MutableLiveData<Resource<DailyImage>>()

    fun fetchAsteroid(startDate: String) {
        viewModelScope.launch {
            asteroid.postValue(Resource.loading(null))
            val dataDB = dbHelper.getAllAsteroid()
            if (dataDB.isNullOrEmpty()) {
                //convert json to string
                val dataApi = apiHelper.getData(startDate).string()
                var strJson = Gson().toJson(dataApi)

                // handle strings
                strJson = strJson.replace("\\\"", "'")
                strJson =  strJson.substring(1, strJson.length - 1)

                // convert string to json object
                val obj = JSONObject(strJson)

                // insert to database
                val listAsteroid = parseAsteroidsJsonResult(obj)
                dbHelper.insertAllAsteroid(listAsteroid)

                //get data from database
                asteroid.postValue(Resource.success(dbHelper.getAllAsteroid()))


            } else {
                asteroid.postValue(Resource.success(dataDB))
            }

            try {

            } catch (ex: Exception) {
                Log.e(TAG, "fetchAsteroid: ${ex.message} ")
            }
        }

    }

    fun fetchDailyImage() {
        viewModelScope.launch {
            image.postValue(Resource.loading(null))

            try {
                val imageDB = dbHelper.getDailyImage()
                if (imageDB == null) {
                    // get data from internet
                    val imageApi = apiHelper.getImage()
                    val obj = DailyImage(imageApi.title, imageApi.url, imageApi.media_type)
                    dbHelper.insertDailyImage(obj)

                    image.postValue(Resource.success(dbHelper.getDailyImage()))

                } else {
                    image.postValue(Resource.success(imageDB))
                }
            } catch (ex: Exception) {
                image.postValue(Resource.error(ex.message.toString(), null))
                Log.e(TAG, "fetchDailyImage: ${ex.message}")
            }
        }
    }


    fun getAsteroidAsLiveData(): LiveData<Resource<List<Asteroid>>> {
        return asteroid
    }

    fun getImageAsLiveData(): LiveData<Resource<DailyImage>> {
        return image
    }
}