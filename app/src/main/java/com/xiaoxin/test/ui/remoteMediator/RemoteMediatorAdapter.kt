package com.xiaoxin.test.ui.remoteMediator

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.xiaoxin.test.R
import com.xiaoxin.test.db.Message


class RemoteMediatorViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_message_content, parent, false)) {

    private val contentView get() = itemView.findViewById<TextView>(R.id.tvContent)
    private val idView get() = itemView.findViewById<TextView>(R.id.tvId)

    private var msg: Message? = null

    @SuppressLint("SetTextI18n")
    fun bindTo(msg: Message?) {
        this.msg = msg
        contentView.text = msg?.content
        idView.text = "ID:${msg?.id?.toString() ?: "0"}"
    }
}


class RemoteMediatorAdapter : PagingDataAdapter<Message, RemoteMediatorViewHolder>(diffCallback) {

    override fun onBindViewHolder(holder: RemoteMediatorViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RemoteMediatorViewHolder =
        RemoteMediatorViewHolder(parent)

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Message>() {
            override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean =
                oldItem == newItem
        }
    }
}

