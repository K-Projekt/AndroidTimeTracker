package pl.edu.pja.teamk.timetracking

import android.Manifest
import android.app.PendingIntent
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingClient
import com.google.android.gms.location.GeofencingRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import pl.edu.pja.teamk.timetracking.databinding.ActivityMainBinding
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var store: TimeEntryStore

    private var geofencingClient: GeofencingClient? = null
    private var geofenceHelper: GeofenceHelper? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        geofencingClient = LocationServices.getGeofencingClient(this)
        geofenceHelper = GeofenceHelper()

        // TODO: Implement geofence handling in GUI
        addGeofence(LatLng(52.229675, 21.012230), 50f)
    }


    private fun addGeofence(latLng: LatLng, radius: Float) {
        val geofence = geofenceHelper!!.createGeofence(
            latLng,
            radius,
            Geofence.GEOFENCE_TRANSITION_ENTER or Geofence.GEOFENCE_TRANSITION_EXIT
        )
        val geofencingRequest = GeofencingRequest.Builder()
            .addGeofence(geofence)
            .build()
        val pendingIntent: PendingIntent = getGeofencePendingIntent()
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Log.e(TAG, "Insufficient permissions.")
            return
        }
        geofencingClient!!.addGeofences(geofencingRequest, pendingIntent)
            .addOnSuccessListener(
                this
            ) {
                Log.i(TAG, "Success: Geofence Added Successfully")
            }
            .addOnFailureListener(
                this
            ) { e -> Log.i(TAG, "Failure: Failed to add geofence. Error: ${e.message}") }
    }


    private fun getGeofencePendingIntent(): PendingIntent {
        val intent = Intent(this, GeofenceBroadcastReceiver::class.java)
        return PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
    }


    override fun onResume() {
        store.loadData(applicationContext)
        super.onResume()
    }

    override fun onPause() {
        store.saveData(applicationContext, store.data)
        super.onPause()
    }
}