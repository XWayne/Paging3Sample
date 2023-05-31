package com.xiaoxin.test.ui.helloWorld

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
import kotlinx.coroutines.launch

class HelloWorldFragment : Fragment() {

    private var _binding: FragmentRvBinding? = null


    private val binding get() = _binding!!

    private val simpleViewModel by viewModels<HelloWorldViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRvBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val adapter = HelloWorldAdapter()
        binding.rv.layoutManager = LinearLayoutManager(requireContext())
        binding.rv.adapter = adapter



        lifecycleScope.launch {

            simpleViewModel.flow
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