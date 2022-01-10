package com.example.businesspal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class DisplaysDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_data)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}