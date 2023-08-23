package com.example.webproject.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.webproject.R
import com.example.webproject.entity.GameBean

class GameAdapter : BaseQuickAdapter<GameBean,BaseViewHolder>(R.layout.item_game) {
    init {
        addChildClickViewIds(R.id.gameTv,R.id.kuaiShouImg,R.id.douyinImg)
    }
    override fun convert(holder: BaseViewHolder, item: GameBean) {
        holder.setText(R.id.gameNameTv,item.name)
        holder.setImageResource(R.id.iconImg,item.icon)
        holder.setGone(R.id.kuaiShouImg,item.kuaiShouUrl.isEmpty())
    }
}