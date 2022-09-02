package com.aries.category.ui.adapter

import android.widget.ImageView
import coil.load
import com.chad.library.adapter.base.BaseSectionQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.aries.category.R
import com.aries.category.ui.modal.CategoryModal
import com.aries.common.util.CoilUtil
import com.aries.common.util.ImageUtils

class SectionQuickAdapter(sectionHeadResId: Int, layoutResId: Int, data: MutableList<CategoryModal> ):
    BaseSectionQuickAdapter<CategoryModal, BaseViewHolder>(sectionHeadResId, layoutResId, data) {

    override fun convertHeader(helper: BaseViewHolder, item: CategoryModal) {
        helper.setText(R.id.textHeader, item.categoryName)
    }

    override fun convert(holder: BaseViewHolder, item: CategoryModal) {
        ImageUtils.load(item.iconUrl, holder.getView<ImageView>(R.id.thirdCategoryIcon))
        holder.setText(R.id.text_content, item.categoryName)
    }


}