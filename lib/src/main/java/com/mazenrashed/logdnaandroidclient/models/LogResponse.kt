package com.mazenrashed.logdnaandroidclient.models

import com.google.gson.annotations.SerializedName

data class LogResponse(
        @SerializedName("status")
        var status: String?
)