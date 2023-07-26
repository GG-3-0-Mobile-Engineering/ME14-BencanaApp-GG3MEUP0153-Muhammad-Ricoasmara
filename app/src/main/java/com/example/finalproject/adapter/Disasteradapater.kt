package com.example.finalproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.finalproject.R
import com.example.finalproject.api.GeometriesItem
import com.example.finalproject.api.Properties
import com.example.finalproject.databinding.ListDisasterBinding

class Disasteradapater( private val list:List<GeometriesItem>):RecyclerView.Adapter<Disasteradapater.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding=ListDisasterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
         return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(list[position])
    }

    override fun getItemCount(): Int=list.size

   inner class ViewHolder(private var binding: ListDisasterBinding):RecyclerView.ViewHolder(binding.root){
    fun bind(disasterList: GeometriesItem){
            binding.apply {
                tvBencana.text= disasterList.properties?.disasterType
                tvDeskripsi.text=disasterList.properties?.text
                disasterList.properties?.imageUrl?.let { imageUrl->
                    imgBencana.load(imageUrl){
                    }
                }
            }

        }

    }

    }