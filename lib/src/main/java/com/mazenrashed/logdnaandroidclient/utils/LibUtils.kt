package com.mazenrashed.logdnaandroidclient.utils

import okhttp3.RequestBody
import okio.Buffer
import java.io.IOException


object LibUtils {

    fun bodyToString(request: RequestBody?): String {
        try {
            val buffer = Buffer()
            if (request != null)
                request.writeTo(buffer)
            else
                return ""
            return buffer.readUtf8()
        } catch (e: IOException) {
            return "did not work"
        }

    }
}