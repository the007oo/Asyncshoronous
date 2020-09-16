package com.phattarapong.asynchronous

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_example2.*
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.schedule

class Example2Activity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example2)

        startBtn.setOnClickListener {
            Observable.create<Int> {
                for (i in 1..10) {
                    Timer().schedule(i * 1000L){
                        it.onNext(i)
                        if (i == 10) {
                            it.onComplete()
                        }
                    }
                }
            }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Int> {
                    override fun onSubscribe(d: Disposable?) {
                        compositeDisposable.add(d)
                    }

                    override fun onNext(t: Int?) {
                        Log.d("Example2Activity", "onNext: ${t.toString()}")
                        countTextView.text = t.toString()
                    }

                    override fun onError(e: Throwable?) {
                        e!!.printStackTrace()
                    }

                    override fun onComplete() {
                        Toast.makeText(this@Example2Activity, "onComplete", Toast.LENGTH_SHORT)
                            .show()
                    }
                })
        }
    }

    override fun onPause() {
        super.onPause()
        compositeDisposable.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}