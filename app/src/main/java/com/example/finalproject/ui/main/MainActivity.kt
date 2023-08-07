package com.example.finalproject.ui.main

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.content.res.AppCompatResources
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.R
import com.example.finalproject.adapter.Disasteradapater
import com.example.finalproject.api.ApiConfig
import com.example.finalproject.api.ApiResponse
import com.example.finalproject.api.GeometriesItem

import com.example.finalproject.databinding.ActivityMainBinding
import com.example.finalproject.preference.DataPreferences
import com.example.finalproject.remote.ViewModelFactory
import com.example.finalproject.ui.darkmode.DarkThemeActivity
import com.example.finalproject.ui.darkmode.DarkThemeViewModel
import com.example.finalproject.ui.notifikasi.NotifikasiActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager
import com.mapbox.maps.plugin.scalebar.scalebar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dialog: BottomSheetDialog
    private lateinit var adapter: Disasteradapater
    private val mainViewModel by viewModels<MainViewModel> {
        ViewModelFactory(DataPreferences.getInstance(dataStore))
    }
    private val list=ArrayList<GeometriesItem>()

    private var mapView: MapView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val rvItem= findViewById<RecyclerView>(R.id.rvItem)
        rvItem.setHasFixedSize(true)
        rvItem.layoutManager=LinearLayoutManager(this)
        adapter = Disasteradapater(list)
        showBottomSheet()
//        getList(rvItem)
        mainView(rvItem)
        settingDarkmode()
    }

    private fun showBottomSheet() {
        val dialogView = layoutInflater.inflate(R.layout.bottom_sheet_layout, null)
        dialog = BottomSheetDialog(this)
        dialog.setContentView(dialogView)

        BottomSheetBehavior.from(binding.standardbottomsheet).apply {
            peekHeight = 100
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }
        setMap()

    }

    private fun mainView(rcView:RecyclerView){
        mainViewModel.getRecentReport()
        mainViewModel.disasterList.observe(this,{disaster->
            val adapter= disaster?.let { Disasteradapater(it) }
            rcView.adapter=adapter

        })
    }

    private fun getList(rvRec:RecyclerView) {
        ApiConfig.getApiService().getRecent(604800).enqueue(object: Callback<ApiResponse> {
            override fun onResponse(
                call: Call<ApiResponse>,
                response: Response<ApiResponse>
            ) {
                val recentDisaster=response.body()?.result?.objects?.output?.geometries
                Log.i("CHECK_RESPONSE", "${response.body()?.statusCode}")
                recentDisaster?.forEachIndexed { index, _ -> addAnnotationToMap(recentDisaster[index].coordinates!![0], recentDisaster[index].coordinates!![1]) }

                response.body()?.result?.objects?.output?.geometries?.let{list.addAll(it)}

                val adapter=Disasteradapater(list)
                rvRec.adapter=adapter
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.i("CHECK_RESPONSE", "${t.message}")
            }


        })

        }
    private fun setMap(){
        mapView=binding.mapView
        mapView?.getMapboxMap()?.loadStyleUri(Style.MAPBOX_STREETS)
        mapView?.scalebar?.enabled = false
        searchDisaster()
    }
    private fun addAnnotationToMap(lng:Double,lat:Double ) {
        bitmapFromDrawableRes(
            this@MainActivity,
            R.drawable.red_marker
        )?.let {
            val annotationApi = mapView?.annotations
            val pointAnnotationManager = annotationApi?.createPointAnnotationManager()
            val pointAnnotationOptions: PointAnnotationOptions = PointAnnotationOptions()
                .withPoint(com.mapbox.geojson.Point.fromLngLat(lng, lat))
                .withIconImage(it)
            pointAnnotationManager?.create(pointAnnotationOptions)
        }
    }
    private fun bitmapFromDrawableRes(context: Context, @DrawableRes resourceId: Int) =
        convertDrawableToBitmap(AppCompatResources.getDrawable(context, resourceId))
    private fun convertDrawableToBitmap(sourceDrawable: Drawable?): Bitmap? {
        if (sourceDrawable == null) {
            return null
        }
        return if (sourceDrawable is BitmapDrawable) {
            sourceDrawable.bitmap
        } else {
            val constantState = sourceDrawable.constantState ?: return null
            val drawable = constantState.newDrawable().mutate()
            val bitmap: Bitmap = Bitmap.createBitmap(
                drawable.intrinsicWidth, drawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            bitmap
        }
    }
    fun searchDisaster(){
        binding.searchBar.inflateMenu(R.menu.option_menu)
        binding.searchBar.setOnMenuItemClickListener{
            when(it.itemId){
                R.id.setting->{
                    val intent= Intent(applicationContext,DarkThemeActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.notifikasi->{
                    val intent= Intent(applicationContext,NotifikasiActivity::class.java)
                    startActivity(intent)
                    true
                }

                else -> true
            }

        }
        binding.searchView.setupWithSearchBar(binding.searchBar)
        binding.searchView
            .editText

    }

  fun settingDarkmode(){
      val settingDark=DataPreferences.getInstance(dataStore)
      val viewModeldark=ViewModelProvider(this,ViewModelFactory(settingDark)).get(DarkThemeViewModel::class.java)

  viewModeldark.getThemeSetting().observe(this){
      isNightActive->
      if (isNightActive){
          AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
      }else{
          AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
      }
  }
  }
}