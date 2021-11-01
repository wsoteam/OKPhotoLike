package com.peditor.green.Utils

import com.amplitude.api.Amplitude
import org.json.JSONException
import org.json.JSONObject

object Analytics {

    fun open(){
        Amplitude.getInstance().logEvent("open")
    }

    fun showButton(){
        Amplitude.getInstance().logEvent("show_button")
    }

    fun clickButtonK(){
        Amplitude.getInstance().logEvent("click_button_k")
    }


    fun clickFirstButton(){
        Amplitude.getInstance().logEvent("click_first_button_v")
    }

    fun clickSecondButton(){
        Amplitude.getInstance().logEvent("click_second_button_v")
    }

    fun goToScreen(){
        Amplitude.getInstance().logEvent("go_to_screen")
    }

    fun openWhite(){

        Amplitude.getInstance().logEvent("open_white")
    }

    fun logUrl(url: String) {
        val eventProperties = JSONObject()
        try {
            eventProperties.put("url", url)
        } catch (exception: JSONException) {
            exception.printStackTrace()
        }
        Amplitude.getInstance().logEvent("log_url", eventProperties)
    }

    fun logError(error : String){
        val eventProperties = JSONObject()
        try {
            eventProperties.put("error", error)
        } catch (exception: JSONException) {
            exception.printStackTrace()
        }
        Amplitude.getInstance().logEvent("log_error", eventProperties)
    }

}