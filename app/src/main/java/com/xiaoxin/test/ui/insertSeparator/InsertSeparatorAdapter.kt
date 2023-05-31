package com.xiaoxin.test.ui.insertSeparator

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.xiaoxin.test.R
import com.xiaoxin.test.db.Message

// step 1.3 定义多类型viewHolder和adapter
class SeparatorViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_message_separator, parent, false)
) {

    private val separatorTv get() = itemView.findViewById<TextView>(R.id.tvSeparator)

    @SuppressLint("SetTextI18n")
    fun bindTo(model: SeparatorModel) {
        separatorTv.text = model.desc
    }
}

class InsertSeparatorViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_message_content, parent, false)
) {

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


class InsertSeparatorAdapter : PagingDataAdapter<Any, RecyclerView.ViewHolder>(diffCallback) {


    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item is Message) 1 else 2
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as? SeparatorViewHolder)?.let {
            it.bindTo(getItem(position) as SeparatorModel)
            return
        }

        (holder as? InsertSeparatorViewHolder)?.bindTo(getItem(position) as? Message)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        if (viewType == 1) InsertSeparatorViewHolder(parent) else SeparatorViewHolder(parent)

    companion object {
        // step 1.2 定义diffCallback
        private val diffCallback = object : DiffUtil.ItemCallback<Any>() {
            override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
                return (oldItem is Message && newItem is Message && oldItem.id == newItem.id)
                        || (oldItem is SeparatorModel && newItem is SeparatorModel && oldItem.desc == newItem.desc)
            }

            override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean =
                (oldItem is Message && newItem is Message && oldItem == newItem)
                        || (oldItem is SeparatorModel && newItem is SeparatorModel && oldItem == newItem)
        }
    }
}


// step 1.1 定义不同viewType model
data class SeparatorModel(val desc: String)