package pl.edu.pja.teamk.timetracking

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class HiltApplication : Application() {
    @Inject
    lateinit var store: TimeEntryStore
    override fun onCreate() {
        super.onCreate()
        store.loadData(applicationContext)
    }

    override fun onTerminate() {
        super.onTerminate()
        store.saveData(applicationContext, store.data)
    }
}