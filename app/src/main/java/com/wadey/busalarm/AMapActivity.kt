package com.wadey.busalarm

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.amap.api.maps2d.MapView

class AMapActivity : AppCompatActivity() {

    private lateinit var mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_amap)

        mapView = findViewById(R.id.map)
        mapView.onCreate(savedInstanceState)
        var aMap = mapView.map
    }
}
