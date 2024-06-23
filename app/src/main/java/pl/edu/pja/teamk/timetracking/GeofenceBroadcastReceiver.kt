package pl.edu.pja.teamk.timetracking

import android.content.BroadcastReceiver
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingEvent
import com.google.android.gms.maps.model.LatLng
import java.util.UUID

class GeofenceBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val geofencingEvent = GeofencingEvent.fromIntent(intent)
        if (geofencingEvent!!.hasError()) {
            Log.d(TAG, "onReceive: Error receiving geofence event...")
            return
        }
        val transition = geofencingEvent.geofenceTransition
        if (transition == Geofence.GEOFENCE_TRANSITION_ENTER) {
            Log.d(TAG, "onReceive: ENTER")
        } else if (transition == Geofence.GEOFENCE_TRANSITION_EXIT) {
            Log.d(TAG, "onReceive: EXIT")
        }
    }
}


class GeofenceHelper {
    fun createGeofence(latLng: LatLng, radius: Float, transitionTypes: Int): Geofence {
        return Geofence.Builder()
            .setRequestId(generateRequestId())
            .setCircularRegion(latLng.latitude, latLng.longitude, radius)
            .setExpirationDuration(Geofence.NEVER_EXPIRE)
            .setTransitionTypes(transitionTypes)
            .build()
    }

    private fun generateRequestId(): String {
        return UUID.randomUUID().toString()
    }
}

