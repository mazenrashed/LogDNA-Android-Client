package com.mazenrashed.logdnaandroidclient.models

import com.google.gson.annotations.SerializedName
import com.mazenrashed.logdnaandroidclient.LogDna

class Line private constructor(
        @SerializedName("level") var level: String,
        @SerializedName("line") var line: String,
        @SerializedName("meta") var meta: HashMap<String, Any>?,
        @SerializedName("timestamp") var timestamp: Long,
        @SerializedName("host") var host: String?
) {

    @SerializedName("app")
    var app: String = LogDna.appName ?: error("You must call LogDna.init()")


    class Builder {
        @SerializedName("level")
        private var level: String = ""
        @SerializedName("line")
        private var line: String = ""
        @SerializedName("meta")
        private var meta: HashMap<String, Any>? = null
        @SerializedName("timestamp")
        private var timestamp: Long = System.currentTimeMillis() / 1000
        @SerializedName("host")
        private var host: String? = null

        fun setHost(host: String): Builder {
            this.host = host
            return this
        }

        fun setLevel(level: String): Builder {
            this.level = level
            return this
        }

        fun setLine(line: String): Builder {
            this.line = line
            return this
        }

        fun setTime(timeInMilles: Long): Builder {
            this.timestamp = timeInMilles / 1000
            return this
        }

        fun addCustomField(field: CustomField): Builder {
            if (meta == null)
                meta = HashMap()
            meta?.put(field.name, field.value)
            return this
        }

        fun build(): Line =
                Line(level, line, meta, timestamp, host)

    }

    class CustomField(var name: String, var value: Any)

    companion object {
        const val LEVEL_INFO = "INFO"
        const val LEVEL_ERROR = "ERROR"
        const val LEVEL_WARN = "WARN"
        const val LEVEL_TRACE = "TRACE"
        const val LEVEL_DEBUG = "DEBUG"
    }
}

