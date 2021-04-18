package com.mahmouddev.nasaproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.mahmouddev.nasaproject.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navContainer) as NavHostFragment
        navController = navHostFragment.findNavController()

        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController)


    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.item1 -> {
                Log.e(TAG, "onOptionsItemSelected: item1 ", )
            }
            R.id.item2 -> {
                Log.e(TAG, "onOptionsItemSelected: item2 ", )
            }

            R.id.item3 -> {
                Log.e(TAG, "onOptionsItemSelected: item3 ", )

            }
        }
        return true
    }

    private fun readJsonExample() {
        val response = "{\"name\":\"ali\",\n" +
                "\"age\":20,\n" +
                "\"student\":{\"rate\":80,\"subject\":[\"english\",\"math\",\"arabic\"]}\n" +
                "}"

        val jsonObject = JSONObject(response)
        val name = jsonObject.getString("name")

        val age = jsonObject.getInt("age")
        val student = jsonObject.getJSONObject("student")

        val stdRate = student.getInt("rate")
        val array = student.getJSONArray("subject")

        for (i in 0 until array.length()) {
            val subject = array.get(i)
            Log.e("subject", "onCreate: $subject")
        }

        Log.e(TAG, "onCreate: name: $name age: $age, rate: $stdRate")

    }
}