package com.firstlink.duo.widget.decorations

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.TypedValue
import android.view.View

class GridItemDecoration(f: Float, context: Context) : RecyclerView.ItemDecoration() {
    val div: Int

    init {
        div = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, f, context.resources.displayMetrics).toInt()
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
        outRect.bottom = div
        outRect.right = div
        val lm = parent.layoutManager
        val rowNum = when (lm) {
            is GridLayoutManager -> lm.spanCount
            is StaggeredGridLayoutManager -> lm.spanCount
            else -> 0
        }
        val position = parent.getChildAdapterPosition(view)
        if (position < rowNum) outRect.top = div
        outRect.left = when ((position + 1) % rowNum) { 1 -> div else -> 0
        }
    }

}
