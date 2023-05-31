package com.xiaoxin.test.ui.placeholder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.recyclerview.widget.LinearLayoutManager
import com.xiaoxin.test.FakeService
import com.xiaoxin.test.databinding.FragmentRvBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PlaceholderFragment : Fragment() {

    private var _binding: FragmentRvBinding? = null


    private val binding get() = _binding!!

    private val viewModel by viewModels<PlaceHolderViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRvBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val adapter = PlaceholderAdapter()
        binding.rv.layoutManager = LinearLayoutManager(requireContext())
        binding.rv.adapter = adapter



        lifecycleScope.launch {

            viewModel.flow
                .collectLatest {
                    adapter.submitData(it)
                }

        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}


