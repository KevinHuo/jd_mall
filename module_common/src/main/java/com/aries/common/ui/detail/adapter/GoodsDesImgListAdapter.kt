package com.aries.common.ui.detail.adapter

import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.LayoutRes
import coil.load
import com.aries.common.R
import com.aries.common.ui.detail.BannerBean
import com.aries.common.util.CoilUtil
import com.aries.common.util.ImageUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

class GoodsDesImgListAdapter(@LayoutRes layoutResId: Int, data: MutableList<String>): BaseQuickAdapter<String, BaseViewHolder>(layoutResId, data) {

    override fun convert(holder: BaseViewHolder, url: String) {
        ImageUtils.load(url, holder.getView<ImageView>(R.id.desImg))
    }
}