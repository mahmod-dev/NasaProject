package com.mahmouddev.nasaproject.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.mahmouddev.nasaproject.R
import com.mahmouddev.nasaproject.databinding.ActivityMainBinding
import com.mahmouddev.nasaproject.databinding.FragmentDetailsBinding
import com.squareup.picasso.Picasso

class DetailsFragment : Fragment(R.layout.fragment_details) {
    lateinit var binding: FragmentDetailsBinding

    val args: DetailsFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)

        binding.apply {
            Picasso.with(context).load(args.dailyImage.url)
                .into(activityMainImageOfTheDay)

            absoluteMagnitude.text = "${args.asteroid.magnitude} au"

            estimatedDiameter.text = String.format("%f km", args.asteroid.estimatedDiameter)

            closeApproachDate.text = args.asteroid.closeApproachDate
            closeApproachDate.contentDescription = args.asteroid.closeApproachDate


            relativeVelocity.text =
                String.format(
                    requireActivity().getString(R.string.km_s_unit_format),
                    args.asteroid.kilometersPerSecond
                )

            distanceFromEarth.text =
                String.format(
                    requireActivity().getString(R.string.astronomical_unit_format),
                    args.asteroid.distanceFromEarth
                )

            helpButton.setOnClickListener {
                showDialog()
            }


        }

    }

    private fun showDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("my title")
            .setMessage("my details")
            .setPositiveButton("ok") { dialog, which ->

                Log.e("TAG", "showDialog: ")

            }
            .setNegativeButton("cancel") { dialog, which ->

                Log.e("TAG", "cancel: ")

            }
            .show()
    }

}