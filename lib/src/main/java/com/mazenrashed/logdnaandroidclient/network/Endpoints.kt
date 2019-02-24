package com.mazenrashed.logdnaandroidclient.network

import com.mazenrashed.logdnaandroidclient.models.LogRequest
import com.mazenrashed.logdnaandroidclient.models.LogResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface Endpoints {
    @POST("/logs/ingest")
    fun log(@Query("hostname") hostName: String, @Query("now") now: Long, @Query("apikey") apiKey: String, @Body logRequest: LogRequest): Single<Response<LogResponse>>
}