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

class ColorThumbListAdapter(@LayoutRes layoutResId: Int, data: MutableList<BannerBean>): BaseQuickAdapter<BannerBean, BaseViewHolder>(layoutResId, data) {

    override fun convert(holder: BaseViewHolder, bean: BannerBean) {
        ImageUtils.load(bean.thumb, holder.getView<ImageView>(R.id.thumbImg))
        holder.getView<LinearLayout>(R.id.colorOptionLayout).run {
            if (bean.select!!)
                setBackgroundResource(R.drawable.detail_color_thumb_select)
            else
                setBackgroundResource(R.drawable.detail_color_thumb_unselect)
        }
    }
}