package com.mahmouddev.nasaproject.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mahmouddev.nasaproject.R
import com.mahmouddev.nasaproject.databinding.ItemMainBinding
import com.mahmouddev.nasaproject.roomDB.entities.Asteroid

class MainAdapter(var data: List<Asteroid>) :
    RecyclerView.Adapter<MainAdapter.MyViewHolder>() {
    val TAG = "MainAdapter"

    var onItemClick: ((Asteroid) -> Unit)? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
        val viewBinding = ItemMainBinding.inflate(view, viewGroup, false)

        return MyViewHolder(viewBinding)
    }

    override fun onBindViewHolder(myViewHolder: MyViewHolder, i: Int) {
        myViewHolder.bind(data[i])

    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class MyViewHolder(var viewBinding: ItemMainBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(response: Asteroid) {

            viewBinding.apply {

                tvDate.text = response.closeApproachDate
                tvTitle.text = response.name

                rlContainer.setOnClickListener {

                    onItemClick?.invoke(response)
                }

                if (response.hazardous == true) img.setImageResource(R.drawable.ic_status_potentially_hazardous)
                else img.setImageResource(R.drawable.ic_status_normal)

                img.contentDescription = response.name
            }

        }

    }
}

