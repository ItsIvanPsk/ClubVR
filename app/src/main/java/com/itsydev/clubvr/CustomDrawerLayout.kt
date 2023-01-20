package com.itsydev.clubvr

import android.content.Context
import android.util.AttributeSet
import androidx.customview.widget.ViewDragHelper
import androidx.drawerlayout.widget.DrawerLayout
import java.lang.Integer.max

class CustomDrawerLayout : DrawerLayout {
    var swipeEdgeSize = 0

    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        var leftDraggerField = DrawerLayout::class.java.getDeclaredField("mLeftDragger")
        leftDraggerField.isAccessible = true
        val viewDragHelper = leftDraggerField[this] as ViewDragHelper

        val edgeSizeField = ViewDragHelper::class.java.getDeclaredField("mEdgeSize")
        edgeSizeField.isAccessible = true
        val origEdgeSize = edgeSizeField[viewDragHelper] as Int
        edgeSizeField.setInt(viewDragHelper, max(swipeEdgeSize, origEdgeSize))
    }
}