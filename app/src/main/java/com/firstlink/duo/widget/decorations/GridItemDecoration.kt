package com.firstlink.duo.widget.decorations

import android.graphics.Rect
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.TypedValue
import android.view.View
import com.firstlink.duo.App

class GridItemDecoration(f: Float) : RecyclerView.ItemDecoration() {
    val div: Int


    init {
        div = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, f, App.instance?.resources?.displayMetrics).toInt()
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {

        outRect.bottom = div / 2
        outRect.top = div / 2

        val lm = parent.layoutManager
        val rowNum = when (lm) {
            is GridLayoutManager -> lm.spanCount
            is StaggeredGridLayoutManager -> lm.spanCount
            else -> 0
        }
        val position = parent.getChildAdapterPosition(view)

        when (position % rowNum) {
            0 -> {
                outRect.left = div
                outRect.right = div/2
            }
            1 -> {
                outRect.right = div
                outRect.left = div/2
            }
        }
    }

}
