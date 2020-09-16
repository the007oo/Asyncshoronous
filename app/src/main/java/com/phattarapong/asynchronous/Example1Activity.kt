package com.phattarapong.asynchronous

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_example1.*

class Example1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example1)

        startBtn.setOnClickListener {
            for (i in 1..10) {
                Log.d("Example1Activity", "i : ${i}")
                Thread.sleep(1000)
                countTextView.text = i.toString()
            }
        }

    }
}