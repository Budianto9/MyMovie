package com.example.mymovie.ui.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mymovie.databinding.FragmentLocationBinding
import com.example.mymovie.ui.base.BaseFragment
import org.osmdroid.api.IMapController
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.views.CustomZoomButtonsController
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

class LocationFragment : BaseFragment<FragmentLocationBinding>() {

    private lateinit var map: MapView
    private lateinit var mapController: IMapController
    private lateinit var myLocationOverlay: MyLocationNewOverlay


    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLocationBinding
        get() = FragmentLocationBinding::inflate


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            initUI()
        }

    }

    override fun FragmentLocationBinding.initUI() {
        map = mapView
        // Set tile source + display settings
        map.setTileSource(TileSourceFactory.MAPNIK)
        map.setBuiltInZoomControls(true)
        map.setMultiTouchControls(true)
        map.zoomController.setVisibility(CustomZoomButtonsController.Visibility.NEVER)

        // Create MapController and set starting location
        mapController = map.controller

        // Create location overlay
        myLocationOverlay = MyLocationNewOverlay(GpsMyLocationProvider(requireContext()), map)
        myLocationOverlay.enableMyLocation()
        myLocationOverlay.enableFollowLocation()
        myLocationOverlay.isDrawAccuracyEnabled = true
        myLocationOverlay.runOnFirstFix{ activity?.runOnUiThread {
            mapController.animateTo(myLocationOverlay.myLocation)
            mapController.setZoom(20.5)
        }}

        myLocationOverlay.enableMyLocation()
        map.overlays.add(myLocationOverlay)
        map.overlays.add(myLocationOverlay)

//        val startPoint = GeoPoint(-6.2296338, 106.7588497)
//        mapController.setCenter(startPoint)

        // Set user agent
        Configuration.getInstance().userAgentValue = "RossMaps"

        println(myLocationOverlay.myLocation)
        println("Create done")
    }

    override fun onResume() {
        super.onResume()
        map.onResume()
    }

    override fun onPause() {
        super.onPause()
        map.onPause()
    }
}