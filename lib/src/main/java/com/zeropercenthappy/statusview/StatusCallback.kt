package com.zeropercenthappy.statusview

import android.view.View

interface StatusCallback {
    fun onStatusStart(status: View)

    fun onStatusFinish(status: View)
}