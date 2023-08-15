package com.example.finalproject.adapter

import android.annotation.SuppressLint
import android.icu.text.DateFormat.MEDIUM
import android.icu.text.DateFormat.RELATIVE_MEDIUM
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.finalproject.R
import com.example.finalproject.api.ApiResponse
import com.example.finalproject.api.GeometriesItem
import com.example.finalproject.api.Properties
import com.example.finalproject.databinding.ListDisasterBinding
import com.example.finalproject.utils.DisasterType
import com.example.finalproject.utils.nameProvince


import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class Disasteradapater( private val list:List<GeometriesItem>):RecyclerView.Adapter<Disasteradapater.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListDisasterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])

    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(private var binding: ListDisasterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(disasterList: GeometriesItem) {
            val geometriesItem = filterGeomertries
            val properties=geometriesItem
            binding.apply {
                properties.let {
                    val timeStamp = Instant.parse(disasterList.properties?.createdAt)
                    val publisZone = ZonedDateTime.ofInstant(timeStamp, ZoneId.systemDefault())
                    val dateFormat: DateTimeFormatter =
                        DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
                    val provinceName =
                        nameProvince.getnameProvince(disasterList.properties?.tags?.instanceRegionCode)
                    tvRegion.text = provinceName
                    tvBencana.text = publisZone.format(dateFormat)
                    tvDeskripsi.text = disasterList.properties?.text
                    disasterList.properties?.imageUrl?.let { imageUrl ->
                        imgBencana.load(imageUrl) {
                        }
                    }
                    val disasterType = disasterList.properties?.disasterType
                    val disasterText = when (DisasterType.valueOf(disasterType!!.uppercase())) {
                        DisasterType.FLOOD -> DisasterType.FLOOD.idDisaster
                        DisasterType.EARTHQUAKE -> DisasterType.EARTHQUAKE.idDisaster
                        DisasterType.FIRE -> DisasterType.FIRE.idDisaster
                        DisasterType.HAZE -> DisasterType.HAZE.idDisaster
                        DisasterType.WIND -> DisasterType.WIND.idDisaster
                        DisasterType.VOLCANO -> DisasterType.VOLCANO.idDisaster
                    }
                    tvDisasterType.text = disasterText
                    val disasterTextColor =
                        when (DisasterType.valueOf(disasterType!!.uppercase())) {
                            DisasterType.FLOOD -> DisasterType.FLOOD.colorDisaster
                            DisasterType.EARTHQUAKE -> DisasterType.EARTHQUAKE.colorDisaster
                            DisasterType.FIRE -> DisasterType.FIRE.colorDisaster
                            DisasterType.HAZE -> DisasterType.HAZE.colorDisaster
                            DisasterType.WIND -> DisasterType.WIND.colorDisaster
                            DisasterType.VOLCANO -> DisasterType.VOLCANO.colorDisaster

                        }
                    tvDisasterType.setTextColor(
                        ContextCompat.getColor(
                            itemView.context,
                            disasterTextColor
                        )
                    )


                }
            }
        }

    }
    private val defaultType= listOf("flood","eartquake","haze","fire","wind","volcano")
    val apiResponse=ApiResponse()
    var filterGeomertries:List<GeometriesItem> =
        (apiResponse.result?.objects?.output?.geometries ?: emptyList()) as List<GeometriesItem>

    fun filterType(disasterType:String){
       filterGeomertries= if (disasterType in defaultType){
           apiResponse.result?.objects?.output?.geometries?.filter {
               it.properties?.disasterType == disasterType
           }?.toList()as List<GeometriesItem>??: emptyList()
       }else{
            apiResponse.result?.objects?.output?.geometries as List<GeometriesItem>? ?: emptyList()
       }

    }
    fun filterProvinceName(filteredGeometries: List<GeometriesItem>) {
        this.filterGeomertries = filteredGeometries
        notifyDataSetChanged()
    }

}