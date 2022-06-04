package com.example.mymovie.ui.location

import android.Manifest
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.mymovie.databinding.FragmentLocationBinding
import com.example.mymovie.ui.base.BaseFragment
import org.osmdroid.api.IMapController
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
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

        // Request Location permission
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PERMISSION_GRANTED
        ) {
            println("Location Permission GRANTED")
        } else {
            println("Location Permission DENIED")
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
        }

        map = mapView
        // Set tile source + display settings
        map.setTileSource(TileSourceFactory.MAPNIK)
        map.setMultiTouchControls(true)
        map.zoomController.setVisibility(CustomZoomButtonsController.Visibility.NEVER)

        // Create MapController and set starting location
        mapController = map.controller

        // Create location overlay
        myLocationOverlay = MyLocationNewOverlay(GpsMyLocationProvider(requireContext()), map)
        myLocationOverlay.enableMyLocation()
        myLocationOverlay.enableFollowLocation()
        myLocationOverlay.isDrawAccuracyEnabled = true
        myLocationOverlay.runOnFirstFix {
            requireActivity().runOnUiThread {
                mapController.animateTo(GeoPoint(myLocationOverlay.myLocation))
                mapController.setZoom(10.0)
                mapController.setCenter(GeoPoint(myLocationOverlay.myLocation))

                binding.latitude.text = myLocationOverlay.myLocation.latitude.toString()
                binding.longitude.text = myLocationOverlay.myLocation.longitude.toString()
            }
        }
        map.overlays.add(myLocationOverlay)

        // Set user agent
        Configuration.getInstance().userAgentValue = "RossMaps"

        println(myLocationOverlay.myLocation)
        println("Create done")

    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }

}