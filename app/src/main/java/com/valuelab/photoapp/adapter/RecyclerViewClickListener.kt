package com.valuelab.photoapp.adapter

import android.view.View

/**
 * Provides onClickLister callback to activity or fragment
 */
interface RecyclerViewClickListener {
    fun onRecyclerViewItemClick(view: View, id: Int)
}