package com.wadey.busalarm

import android.location.Location
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.amap.api.maps.AMap
import com.amap.api.maps.MapView
import com.amap.api.maps.model.MyLocationStyle


class AMapActivity : AppCompatActivity(), AMap.OnMyLocationChangeListener {

    private lateinit var mMapView: MapView
    private lateinit var myLocationStyle: MyLocationStyle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_amap)

        // Initialize map view
        mMapView = findViewById(R.id.map)
        mMapView.onCreate(savedInstanceState)
        var aMap = mMapView.map
        // Initialize my location style
        myLocationStyle = MyLocationStyle()
        myLocationStyle.interval(2000)
        myLocationStyle.showMyLocation(true)
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE)

        // Initialize the location service
        aMap.myLocationStyle = myLocationStyle
        aMap.uiSettings.isMyLocationButtonEnabled = true
        aMap.isMyLocationEnabled = true

    }

    override fun onMyLocationChange(loc: Location?) {
        Log.d("[WY DEBUG]", "My location: Latitude-${loc?.latitude} Longtitude-${loc?.longitude}")
    }

    override fun onDestroy() {
        super.onDestroy()
        // destroy the map when activity is destroyed
        mMapView.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        // resume the map when activity is resumed
        mMapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        // pause the map when activity is paused
        mMapView.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // save map's instance state
        mMapView.onSaveInstanceState(outState)
    }
}
