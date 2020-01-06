package com.zeropercenthappy.statusview_sample

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.zeropercenthappy.statusview.StatusCallback
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_empty.*
import kotlinx.android.synthetic.main.layout_error.*
import kotlinx.android.synthetic.main.layout_loading.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        clLoading.setOnClickListener(this)
        clEmpty.setOnClickListener(this)
        clError.setOnClickListener(this)

        statusView.addLoadingViewCallback(object : StatusCallback {
            override fun onStatusStart(status: View) {
                ivLoading.startAnimation(
                    AnimationUtils.loadAnimation(
                        this@MainActivity,
                        R.anim.anim_loading
                    )
                )
            }

            override fun onStatusFinish(status: View) {
                ivLoading.clearAnimation()
            }
        })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.clLoading -> {
                statusView.showLoading()
            }
            R.id.clEmpty -> {
                statusView.showEmpty()
            }
            R.id.clError -> {
                statusView.showError()
            }
            R.id.ivLoading, R.id.ivEmpty, R.id.ivError -> {
                statusView.showNormal()
            }
        }
    }
}
