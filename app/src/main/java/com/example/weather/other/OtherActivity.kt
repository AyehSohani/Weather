package com.example.weather.other

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log

class OtherActivity : Activity() {
    private val TAG = "A"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ${intent.getStringExtra("data")}")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d(TAG, "onNewIntent: ${intent?.extras}")
    }
}