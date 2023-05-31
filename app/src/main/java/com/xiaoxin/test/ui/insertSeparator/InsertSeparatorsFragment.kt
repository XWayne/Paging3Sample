package com.xiaoxin.test.ui.insertSeparator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.xiaoxin.test.databinding.FragmentRvBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

/**
 * @author XiaoXinC
 */
class InsertSeparatorsFragment : Fragment() {

    private var _binding: FragmentRvBinding? = null

    private val binding get() = _binding!!

    private val viewModel by viewModels<InsertSeparatorViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRvBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val adapter = InsertSeparatorAdapter()
        binding.rv.layoutManager = LinearLayoutManager(requireContext())
        binding.rv.adapter = adapter



        lifecycleScope.launch {

            viewModel.flow
                .collectLatest { adapter.submitData(it) }

        }


        return root
    }

}