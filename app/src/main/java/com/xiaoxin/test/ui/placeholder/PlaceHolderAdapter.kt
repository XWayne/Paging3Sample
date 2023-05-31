package com.xiaoxin.test.ui.placeholder

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.xiaoxin.test.R
import com.xiaoxin.test.db.Message


class PlaceholderViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_message_content, parent, false)) {

    private val contentView get() = itemView.findViewById<TextView>(R.id.tvContent)
    private val idView get() = itemView.findViewById<TextView>(R.id.tvId)

    private var msg: Message? = null

    @SuppressLint("SetTextI18n")
    fun bindTo(msg: Message?) {
        this.msg = msg

        //step 3 null时显示content为 "我是占位符"
        contentView.text = msg?.content ?: "我是占位符"
        idView.text = msg?.id?.let { "ID:$it" } ?: "----"
    }
}


class PlaceholderAdapter : PagingDataAdapter<Message, PlaceholderViewHolder>(diffCallback) {

    override fun onBindViewHolder(holder: PlaceholderViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceholderViewHolder =
        PlaceholderViewHolder(parent)

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Message>() {
            override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean =
                oldItem == newItem
        }
    }
}

