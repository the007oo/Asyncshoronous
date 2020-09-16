package com.phattarapong.asynchronous

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_example3.*
import kotlinx.coroutines.*

class Example3Activity : AppCompatActivity() {

    private var job = Job()
    private var scope = CoroutineScope(Dispatchers.Main + job)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example3)

        startBtn.setOnClickListener {
            scope.launch {
                val path = async(Dispatchers.IO) { downloadImage() }.await() // In Background
                val status = async(Dispatchers.IO) { saveImage(path) }.await() // In Background

                Toast.makeText(this@Example3Activity,status,Toast.LENGTH_SHORT).show() // In Ui
            }
        }

        /*downloadBtn.setOnClickListener {
            scope.launch {
                val str = async(Dispatchers.IO) { saveImage() }.await()
                Toast.makeText(this@Example3Activity, str, Toast.LENGTH_SHORT).show()
            }
        }*/
    }

    suspend fun saveImage(path : String): String {
        delay(5000)
        return "Save success"
    }

    suspend fun downloadImage() : String {
        delay(5000)
        return "path"
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}