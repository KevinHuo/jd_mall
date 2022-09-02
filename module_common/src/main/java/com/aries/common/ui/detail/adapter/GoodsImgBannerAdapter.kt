package com.aries.common.ui.detail.adapter

import android.view.ViewGroup
import android.widget.ImageView
import coil.ImageLoader
import coil.load
import com.aries.common.R
import com.aries.common.util.CoilUtil
import com.aries.common.util.ImageUtils
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder

class GoodsImgBannerAdapter(data: List<String>): BannerImageAdapter<String>(data) {
    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): BannerImageHolder {
        val imageView = ImageView(parent?.context)
        imageView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        imageView.scaleType = ImageView.ScaleType.FIT_XY
        return BannerImageHolder(imageView)
    }
    override fun onBindView(holder: BannerImageHolder?, data: kotlin.String?, position: Int, size: Int) {
        ImageUtils.load(data!!, holder?.imageView)
    }
}