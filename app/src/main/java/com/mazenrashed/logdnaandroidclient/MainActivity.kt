package com.mazenrashed.logdnaandroidclient

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.mazenrashed.logdnaandroidclient.models.Line
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val bag = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        LogDna.init(LOG_DNA_API_KEY, APP_NAME)

        testLog.setOnClickListener {
            LogDna.log(
                    Line.Builder().setLine("Some Test")
                            .addCustomField(Line.CustomField("fName", "mazen"))
                            .addCustomField(Line.CustomField("lName", "rashed"))
                            .addCustomField(Line.CustomField("age", 25))
                            .setLevel(Line.LEVEL_DEBUG)
                            .setTime(System.currentTimeMillis())
                            .build()
            )
        }

        bag.add(
                LogDna.logResults.subscribe { logResult ->
                    Log.d("LogDna", "${logResult.isSuccessful}, ${logResult.message}, ${logResult.logRequest.uid}")
                }
        )

        LogDna.logResultsListener = { logResult ->
            Log.d("LogDna", "${logResult.isSuccessful}, ${logResult.message}, ${logResult.logRequest.uid}")
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        bag.dispose()
    }

    companion object {
        const val APP_NAME = "YOUR_APP_NAME"
        const val LOG_DNA_API_KEY = "YOUR_KEY"
    }
}
