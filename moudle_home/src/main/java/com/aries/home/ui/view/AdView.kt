package com.aries.home.ui.view

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import coil.ImageLoader
import coil.load
import com.aries.common.util.CoilUtil
import com.aries.common.util.ImageUtils
import com.aries.home.R
import kotlinx.android.synthetic.main.home_ad.view.*

class AdView(context: Context): FrameLayout(context) {
    private var imageLoader: ImageLoader = CoilUtil.getImageLoader()
    init {
        LayoutInflater.from(context).inflate(R.layout.home_ad, this, true)
    }

    fun setData(url: String) {
//        adImg.load(url, imageLoader ) {
//            crossfade(true)
//            placeholder(R.drawable.default_img)
//            error(R.drawable.default_img)
//        }
        ImageUtils.load(url, adImg)
    }
}