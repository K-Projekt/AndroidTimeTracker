package pl.edu.pja.teamk.timetracking

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HiltApplication : Application() {
//    init {
//        if (application == null)
//            application = this
//    }
//
//    companion object {
//        private lateinit var application: Application
//
//        fun getApplication(): Application {
//            return application
//        }
//
//        fun getContext(): Context {
//            return getApplication().applicationContext
//        }
//    }
}