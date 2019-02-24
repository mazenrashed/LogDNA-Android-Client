package com.mazenrashed.logdnaandroidclient.models

import com.google.gson.annotations.SerializedName

data class LogRequest(@SerializedName("lines") var lines: ArrayList<Line>, var uid: String)