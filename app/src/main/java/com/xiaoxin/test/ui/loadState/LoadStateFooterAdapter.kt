package com.xiaoxin.test.ui.loadState

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xiaoxin.test.R
import com.xiaoxin.test.databinding.ItemLoadStateFooterBinding


/**
 * step 3 定义转用于状态管理的 footer
 */
class LoadStateFooterAdapter(private val retry: () -> Unit) : LoadStateAdapter<LoadStateFooterViewHolder>() {
    override fun onBindViewHolder(holder: LoadStateFooterViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateFooterViewHolder {
        return LoadStateFooterViewHolder.create(parent, retry)
    }


    override fun displayLoadStateAsItem(loadState: LoadState): Boolean {
//        return true
        return super.displayLoadStateAsItem(loadState)
    }
}

class LoadStateFooterViewHolder(
    private val binding: ItemLoadStateFooterBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.retry.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        //step 4  根据 LoadState 渲染 item
        if (loadState is LoadState.Error) {
            binding.errorMsg.text = loadState.error.localizedMessage
        }
        binding.loading.isVisible = loadState is LoadState.Loading
        binding.retry.isVisible = loadState is LoadState.Error
        binding.errorMsg.isVisible = loadState is LoadState.Error
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): LoadStateFooterViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_load_state_footer, parent, false)
            val binding = ItemLoadStateFooterBinding.bind(view)
            return LoadStateFooterViewHolder(binding, retry)
        }
    }
}

