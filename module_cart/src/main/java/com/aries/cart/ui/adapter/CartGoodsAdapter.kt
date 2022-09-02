package com.aries.cart.ui.adapter

import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.aries.cart.R
import com.aries.cart.ui.CartBean
import com.aries.cart.ui.listener.OnCartItemChangeListener
import com.aries.cart.ui.listener.OnStepperChangeListener
import com.aries.common.util.CoilUtil
import com.aries.common.util.ImageUtils
import com.aries.common.widget.Stepper
import com.aries.common.widget.SwipeMenuLayout
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

open class CartGoodsAdapter(data: MutableList<CartBean>): BaseMultiItemQuickAdapter<CartBean, BaseViewHolder>(data) {
    private var imageLoader = CoilUtil.getImageLoader()
    private var onStepperChangeListener: OnStepperChangeListener? = null
    private var onCartItemChangeListener: OnCartItemChangeListener? = null

    init {
        addItemType(1, R.layout.fragment_cart_item_store)
        addItemType(2, R.layout.fragment_cart_item_goods)
    }

    override fun convert(holder: BaseViewHolder, item: CartBean) {
        when (holder.itemViewType) {
            1 -> {
                holder.setText(R.id.storeName, item.storeName)
                holder.getView<CheckBox>(R.id.storeCheckBox).isChecked = item.check
            }
            2 -> {
//                holder.getView<ImageView>(R.id.cartGoodsImg).load(item.imgUrl, imageLoader) {
//                    crossfade(true)
//                    placeholder(R.drawable.default_img)
//                    error(R.drawable.default_img)
//                }
                ImageUtils.load(item.imgUrl, holder.getView<ImageView>(R.id.cartGoodsImg))

                holder.getView<CheckBox>(R.id.goodsCheckBox).isChecked = item.check
                holder.setText(R.id.storeCode, item.storeCode)
                holder.setText(R.id.goodsCode, item.code)
                holder.setText(R.id.cartGoodsDes, item.description)
                holder.setText(R.id.cartGoodsPrice, "￥${item.price}")

                holder.getView<Stepper>(R.id.buyNum).run {
                    setInputValue(item.num!!)
                    setData(item)
                }
            }
        }
    }

    override fun bindViewClickListener(viewHolder: BaseViewHolder, viewType: Int) {
        super.bindViewClickListener(viewHolder, viewType)
        when (viewType) {
            2 -> {
                viewHolder.itemView.findViewById<Stepper>(R.id.buyNum).run {
                    setOnChangeValueListener(object : Stepper.OnChangeValueListener {
                        override fun onChangeValue(value: String) {
                            val data = getData()
                            if (data != null && (data as CartBean).num != value.toInt()) {
                                onStepperChangeListener?.onStepperChange(data, value.toInt())
                            }
                        }
                    })
                }
                viewHolder.itemView.findViewById<Button>(R.id.lookSimilar).setOnClickListener {
                    onMenuClick(viewHolder, "similar")
                }
                viewHolder.itemView.findViewById<Button>(R.id.btnDelete).setOnClickListener {
                    onMenuClick(viewHolder, "delete")
                }
            }
        }
    }

    open fun setOnStepperChangeListener(onStepperChangeListener: OnStepperChangeListener) {
        this.onStepperChangeListener = onStepperChangeListener
    }

    open fun setOnCartItemChangeListener(onCartItemChangeListener: OnCartItemChangeListener) {
        this.onCartItemChangeListener = onCartItemChangeListener
    }

    private fun onMenuClick(viewHolder: BaseViewHolder, type: String) {
        viewHolder.itemView.findViewById<SwipeMenuLayout>(R.id.goodsSwipeMenuLayout).quickClose()
        val goodsCode = viewHolder.itemView.findViewById<TextView>(R.id.goodsCode).text
        val storeCode = viewHolder.itemView.findViewById<TextView>(R.id.storeCode).text

        onCartItemChangeListener?.onItemStateChange(storeCode.toString() ,goodsCode.toString(), type)
    }
}