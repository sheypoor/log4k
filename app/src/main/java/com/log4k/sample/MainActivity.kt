package com.log4k.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.log4k.d

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        d("onCreate is called")
    }
}