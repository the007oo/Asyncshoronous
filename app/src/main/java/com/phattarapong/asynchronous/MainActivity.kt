package com.phattarapong.asynchronous

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        exam1Btn.setOnClickListener {
            startActivity(Intent(this@MainActivity,Example1Activity::class.java))
        }

        exam2Btn.setOnClickListener {
            startActivity(Intent(this@MainActivity,Example2Activity::class.java))
        }

        exam3Btn.setOnClickListener {
            startActivity(Intent(this@MainActivity,Example3Activity::class.java))
        }
    }
}