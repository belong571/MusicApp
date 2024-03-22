package com.example.musicapp.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.example.musicapp.R
import com.example.musicapp.databinding.ItemSongBinding

class SongAdapter : BaseQuickAdapter<com.example.musicapp.bean.Result, BaseDataBindingHolder<ItemSongBinding>>(R.layout.item_song) {

        override fun convert(
            holder: BaseDataBindingHolder<ItemSongBinding>,
            item: com.example.musicapp.bean.Result
        ) {
            holder.dataBinding?.let {  db ->
                db.item = item
                db.executePendingBindings()
                db.icon.setImageURI(item.artworkUrl60)
            }
        }
}