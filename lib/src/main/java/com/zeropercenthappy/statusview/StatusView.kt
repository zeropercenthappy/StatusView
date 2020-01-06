package com.zeropercenthappy.statusview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ViewFlipper

class StatusView : ViewFlipper {

    private val statusNormal = 0
    private var statusLoading = -1
    private var statusEmpty = -1
    private var statusError = -1

    private var loadingView: View? = null
    private var emptyView: View? = null
    private var errorView: View? = null

    private var loadingViewCallback: StatusCallback? = null
    private var emptyViewCallback: StatusCallback? = null
    private var errorViewCallback: StatusCallback? = null

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initAttrs(context, attrs)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        if (loadingView != null) {
            addView(loadingView)
            statusLoading = childCount - 1
        }
        if (emptyView != null) {
            addView(emptyView)
            statusEmpty = childCount - 1
        }
        if (errorView != null) {
            addView(errorView)
            statusError = childCount - 1
        }
    }

    private fun initAttrs(context: Context, attrs: AttributeSet) {
        context.obtainStyledAttributes(attrs, R.styleable.StatusView).apply {
            // Loading view
            getResourceId(R.styleable.StatusView_loadingView, -1).let { layoutId ->
                if (layoutId != -1) {
                    loadingView = LayoutInflater.from(context).inflate(layoutId, null)
                    val layoutParams = LayoutParams(
                        LayoutParams.MATCH_PARENT,
                        LayoutParams.MATCH_PARENT
                    )
                    loadingView?.layoutParams = layoutParams
                }
            }
            // Empty view
            getResourceId(R.styleable.StatusView_emptyView, -1).let { layoutId ->
                if (layoutId != -1) {
                    emptyView = LayoutInflater.from(context).inflate(layoutId, null)
                    val layoutParams = LayoutParams(
                        LayoutParams.MATCH_PARENT,
                        LayoutParams.MATCH_PARENT
                    )
                    emptyView?.layoutParams = layoutParams
                }
            }
            // Error view
            getResourceId(R.styleable.StatusView_errorView, -1).let { layoutId ->
                if (layoutId != -1) {
                    errorView = LayoutInflater.from(context).inflate(layoutId, null)
                    val layoutParams = LayoutParams(
                        LayoutParams.MATCH_PARENT,
                        LayoutParams.MATCH_PARENT
                    )
                    errorView?.layoutParams = layoutParams
                }
            }
            recycle()
        }
    }

    /**
     * @return loading status view or null if it doesn't exist.
     */
    fun getLoadingView(): View? {
        return loadingView
    }

    /**
     * @return loading status view or null if it doesn't exist.
     */
    fun getEmptyView(): View? {
        return emptyView
    }

    /**
     * @return loading status view or null if it doesn't exist.
     */
    fun getErrorView(): View? {
        return errorView
    }

    /**
     * Add a loading status view.
     *
     * It will remove old loading status view if it exist.
     */
    fun addLoadingView(view: View) {
        loadingView?.let {
            removeView(it)
            statusLoading = -1
        }
        addView(view)
        statusLoading = childCount - 1
    }

    /**
     * Add a empty status view.
     *
     * It will remove old empty status view if it exist.
     */
    fun addEmptyView(view: View) {
        emptyView?.let {
            removeView(it)
            statusEmpty = -1
        }
        addView(view)
        statusEmpty = childCount - 1
    }

    /**
     * Add a error status view.
     *
     * It will remove old error status view if it exist.
     */
    fun addErrorView(view: View) {
        errorView?.let {
            removeView(it)
            statusError = -1
        }
        addView(view)
        statusError = childCount - 1
    }

    /**
     * Add a listener to loading status view.
     */
    fun addLoadingViewCallback(statusCallback: StatusCallback) {
        loadingViewCallback = statusCallback
    }

    /**
     * Add a listener to empty status view.
     */
    fun addEmptyViewCallback(statusCallback: StatusCallback) {
        emptyViewCallback = statusCallback
    }

    /**
     * Add a listener to loading status view.
     */
    fun addErrorViewCallback(statusCallback: StatusCallback) {
        errorViewCallback = statusCallback
    }

    /**
     * Show normal status view.
     */
    fun showNormal() {
        show(statusNormal)
    }

    /**
     * Show loading status view if it exist.
     */
    fun showLoading() {
        show(statusLoading)
        loadingViewCallback?.onStatusStart(requireNotNull(loadingView))
    }

    /**
     * Show empty status view if it exist.
     */
    fun showEmpty() {
        show(statusEmpty)
        emptyViewCallback?.onStatusStart(requireNotNull(emptyView))
    }

    /**
     * Show error status view if it exist.
     */
    fun showError() {
        show(statusError)
        errorViewCallback?.onStatusStart(requireNotNull(errorView))
    }

    private fun show(status: Int) {
        if (status == -1) {
            return
        }
        when (displayedChild) {
            statusLoading -> {
                loadingViewCallback?.onStatusFinish(requireNotNull(loadingView))
            }
            statusEmpty -> {
                emptyViewCallback?.onStatusFinish(requireNotNull(emptyView))
            }
            statusError -> {
                errorViewCallback?.onStatusFinish(requireNotNull(errorView))
            }
        }
        // Show destination status
        displayedChild = status
    }
}