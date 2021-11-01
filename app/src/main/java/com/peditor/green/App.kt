package com.peditor.green

import android.app.Application
import android.content.Context
import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LifecycleObserver
import com.amplitude.api.Amplitude
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.firebase.FirebaseApp
import java.io.IOException


class App : Application(), LifecycleObserver {


    private val afDevKey = "fTHMhfusDFFptFAiXDJ2fU"

    override fun onCreate() {
        super.onCreate()

        sInstance = this
        // ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        Amplitude.getInstance()
                .initialize(this, "bc931ec86f1666be39370e1bbddb523e")
                .enableForegroundTracking(this)
        FirebaseApp.initializeApp(this)

        val conversionDataListener = object : AppsFlyerConversionListener {
            override fun onConversionDataSuccess(data: MutableMap<String, Any>?) {
                campaign = data!!["campaign"].toString()
            }

            override fun onConversionDataFail(error: String?) {
                //Log.e("LOG_TAG", "error onAttributionFailure :  $error")
            }

            override fun onAppOpenAttribution(data: MutableMap<String, String>?) {
                data?.map {
                    //Log.d("LOG_TAG", "onAppOpen_attribute: ${it.key} = ${it.value}")
                }
            }

            override fun onAttributionFailure(error: String?) {
                //Log.e("LOG_TAG", "error onAttributionFailure :  $error")
            }
        }
        AppsFlyerLib.getInstance().init(afDevKey, conversionDataListener, applicationContext)
        AppsFlyerLib.getInstance().start(this)
    }

    companion object {

        private lateinit var sInstance: App

        fun getInstance(): App {
            return sInstance
        }

        var campaign: String = "null"
        var gadid: String? = null

    }

    fun getAdvertisingId(context: Context) {
        AsyncTask.execute {
            try {
                val adInfo = AdvertisingIdClient.getAdvertisingIdInfo(context)
                gadid = adInfo?.id
            } catch (exception: IOException) {
                Log.i("TAG_EXCEPTION", exception.toString())
            } catch (exception: GooglePlayServicesRepairableException) {
                Log.i("TAG_EXCEPTION", exception.toString())
            } catch (exception: GooglePlayServicesNotAvailableException) {
                Log.i("TAG_EXCEPTION", exception.toString())
            }
        }
    }
}

