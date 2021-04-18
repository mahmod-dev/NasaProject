package com.mahmouddev.nasaproject.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mahmouddev.nasaproject.R
import com.mahmouddev.nasaproject.adapters.MainAdapter
import com.mahmouddev.nasaproject.databinding.FragmentMainBinding
import com.mahmouddev.nasaproject.retrofit.ApiImp
import com.mahmouddev.nasaproject.retrofit.RetrofitBuilder
import com.mahmouddev.nasaproject.roomDB.AppDatabase
import com.mahmouddev.nasaproject.roomDB.DatabaseHelperImpl
import com.mahmouddev.nasaproject.roomDB.dbUtil.Status
import com.mahmouddev.nasaproject.roomDB.dbUtil.ViewModelFactory
import com.mahmouddev.nasaproject.roomDB.entities.Asteroid
import com.mahmouddev.nasaproject.roomDB.entities.DailyImage
import com.mahmouddev.nasaproject.viewModel.MainViewModel
import com.squareup.picasso.Picasso

class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding
    lateinit var viewModel: MainViewModel
    lateinit var dailyImg:DailyImage

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        initViewModel()

        viewModel.fetchAsteroid("2021-04-17")
        viewModel.fetchDailyImage()
        getSearchResult()
        getImageDaily()
    }

    private fun initViewModel() {

        val dbHelper = DatabaseHelperImpl(AppDatabase.getInstance(requireContext()))
        val apiHelper = ApiImp(RetrofitBuilder.apiService)
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(apiHelper, dbHelper)
        ).get(MainViewModel::class.java)

    }

    private fun getSearchResult() {
        viewModel.getAsteroidAsLiveData().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {

                    it.data?.let { list ->
                        Log.e("TAG", "getSearchResult: ${list.toString()}")

                        initRecycleView(list)
                    }
                    binding.progressBar.visibility = View.GONE
                }
                Status.EMPTY -> {

                    binding.progressBar.visibility = View.GONE


                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    // Handle Error
                }
            }
        })
    }

    private fun initRecycleView(data: List<Asteroid>) {

        val mainAdapter = MainAdapter(data)
        binding.asteroidRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mainAdapter
            setHasFixedSize(true)
        }

        mainAdapter.onItemClick = {
            val action = MainFragmentDirections.actionMainFragmentToDetailsFragment(it,dailyImg)
            findNavController().navigate(action)

        }



    }


    private fun getImageDaily() {
        viewModel.getImageAsLiveData().observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {

                    it.data?.let { dailyImage ->
                        dailyImg = dailyImage
                        if (dailyImage.mediaType == "image") {
                            Picasso.with(context).load(dailyImage.url)
                                .into(binding.activityMainImageOfTheDay)
                        }

                        binding.textView.text = dailyImage.title
                    }
                }
                Status.EMPTY -> {
                }
                Status.LOADING -> {
                }
                Status.ERROR -> {
                }
            }
        })
    }


}