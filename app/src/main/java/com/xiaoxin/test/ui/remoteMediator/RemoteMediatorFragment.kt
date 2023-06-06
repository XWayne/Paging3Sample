package com.xiaoxin.test.ui.remoteMediator

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.xiaoxin.test.databinding.FragmentLoadStateBinding
import com.xiaoxin.test.ui.loadState.LoadStateFooterAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RemoteMediatorFragment : Fragment() {

    private var _binding: FragmentLoadStateBinding? = null


    private val binding get() = _binding!!

    private val simpleViewModel by viewModels<RemoteMediatorViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoadStateBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val adapter = RemoteMediatorAdapter()
        binding.rv.layoutManager = LinearLayoutManager(requireContext()).apply {
//            reverseLayout = true
        }
        binding.rv.adapter = adapter
        binding.retry.setOnClickListener { adapter.retry() }

        lifecycleScope.launch {

            simpleViewModel.flow
                .collectLatest {
                    adapter.submitData(it)
                }

        }


        lifecycleScope.launch {
            adapter.loadStateFlow.collect { loadStates ->
                binding.loading.isVisible = loadStates.refresh is LoadState.Loading
                binding.retry.isVisible = loadStates.refresh is LoadState.Error
                binding.rv.isVisible = loadStates.refresh is LoadState.NotLoading
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}