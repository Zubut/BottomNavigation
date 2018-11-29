package com.zubut.bottomview

import android.content.Context
import android.graphics.PorterDuff
import android.os.Build
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * @author Alejandro Barba on 11/29/18.
 */
class Common constructor(context: Context, attrs: AttributeSet) : BottomNavigationView(context, attrs), CommonImpl {

    private var mPosition: Int? = null
    private var mMarginLeft: Int? = null
    private var mBadgeColor: Int? = null
    // TODO change to reference
    private var mMenuElement: Int? = null

    init {
        setupAttributes(attrs)
    }

    private fun setupAttributes(attrs: AttributeSet) {
        val attributes = context.theme.obtainStyledAttributes(attrs, R.styleable.Common, 0, 0)
        val color = ResourcesCompat.getColor(resources, R.color.def, null)
        try {
            mPosition = attributes.getInt(R.styleable.Common_position, -1)
            mMarginLeft = attributes.getInt(R.styleable.Common_marginLeft, resources.getDimension(R.dimen.badge_margin_left).toInt())
            mBadgeColor = attributes.getColor(R.styleable.Common_badgeColor, color)
        } finally {
            attributes.recycle()
        }
    }

    override fun addBadge() {
        mPosition?.let { position ->
            if (position > 0) {
                // Remove old badge
                this.removeBadge()
                val drawable = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    resources.getDrawable(R.drawable.ic_badge, null)
                } else {
                    resources.getDrawable(R.drawable.ic_badge)
                }

                drawable.setColorFilter(mBadgeColor!! , PorterDuff.Mode.MULTIPLY)

                val bottomNavigationMenuView = getChildAt(0) as BottomNavigationMenuView
                val v = bottomNavigationMenuView.getChildAt(position)
                val itemView = v as BottomNavigationItemView
                val badge = LayoutInflater.from(context).inflate(R.layout.badge_layout, bottomNavigationMenuView, false)
                badge.background = drawable
                val badgeLayout: FrameLayout.LayoutParams = FrameLayout.LayoutParams(badge?.layoutParams).apply {
                    gravity = Gravity.CENTER_HORIZONTAL
                    topMargin = resources.getDimension(R.dimen.design_bottom_navigation_margin).toInt()
                    leftMargin = mMarginLeft ?: 0
                }
                itemView.addView(badge, badgeLayout)
            }
        }
    }

    override fun removeBadge() {
        mPosition?.let { position ->
            if (position > 0) {
                val bottomNavigationMenuView = getChildAt(0) as BottomNavigationMenuView
                val v = bottomNavigationMenuView.getChildAt(position)
                val itemView = v as BottomNavigationItemView
                if (itemView.childCount == 3) {
                    itemView.removeViewAt(2)
                }
            }
        }
    }
}