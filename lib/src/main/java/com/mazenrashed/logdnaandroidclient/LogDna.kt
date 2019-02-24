package com.mazenrashed.logdnaandroidclient

import com.google.gson.Gson
import com.jakewharton.rxrelay2.PublishRelay
import com.mazenrashed.logdnaandroidclient.models.Line
import com.mazenrashed.logdnaandroidclient.models.LogRequest
import com.mazenrashed.logdnaandroidclient.models.LogResult
import com.mazenrashed.logdnaandroidclient.network.ServiceGenerator
import com.mazenrashed.logdnaandroidclient.utils.LibUtils
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import kotlin.collections.ArrayList

object LogDna {

    private const val hostName = "Android App"
    private var apiKey: String? = null
    var appName: String? = null
    var logResults = PublishRelay.create<LogResult>()
    var logResultsListener: ((LogResult) -> Unit)? = null

    fun init(apiKey: String, appName: String?) {
        this.apiKey = apiKey
        this.appName = appName
    }

    fun log(line: Line) {
        val disposable = CompositeDisposable()
        val uid = UUID.randomUUID().toString()
        val logRequest = LogRequest(ArrayList<Line>().apply { add(line) }, uid)

        disposable.add(
                ServiceGenerator
                        .restService
                        .log(
                                hostName,
                                System.currentTimeMillis() / 1000,
                                apiKey ?: error("You must call LogDna.init()"),
                                logRequest
                        )
                        .subscribeOn(Schedulers.io())
                        .subscribe({
                            val logResult = LogResult(
                                    it.isSuccessful,
                                    if (it.isSuccessful) it.body()?.status!! else it.errorBody()?.string()!!,
                                    Gson().fromJson(LibUtils.bodyToString(it.raw().request().body()), LogRequest::class.java)
                            )

                            if (logResults.hasObservers())
                                logResults.accept(logResult)
                            logResultsListener?.invoke(logResult)

                            disposable.dispose()
                        }, {
                            it.printStackTrace()
                            disposable.dispose()
                        })
        )
    }

    fun log(lines: ArrayList<Line>) {
        val disposable = CompositeDisposable()
        val uid = UUID.randomUUID().toString()
        val logRequest = LogRequest(lines, uid)

        disposable.add(
                ServiceGenerator
                        .restService
                        .log(
                                hostName,
                                System.currentTimeMillis() / 1000,
                                apiKey ?: error("You must call LogDna.init()"),
                                logRequest
                        )
                        .subscribeOn(Schedulers.io())
                        .subscribe({
                            val logResult = LogResult(
                                    it.isSuccessful,
                                    if (it.isSuccessful) it.body()?.status!! else it.errorBody()?.string()!!,
                                    Gson().fromJson(LibUtils.bodyToString(it.raw().request().body()), LogRequest::class.java)
                            )

                            if (logResults.hasObservers())
                                logResults.accept(logResult)
                            logResultsListener?.invoke(logResult)

                            disposable.dispose()
                        }, {
                            it.printStackTrace()
                            disposable.dispose()
                        })
        )
    }

}