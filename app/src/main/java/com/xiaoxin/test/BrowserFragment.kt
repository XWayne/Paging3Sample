package com.xiaoxin.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.xiaoxin.test.databinding.FragmentBrowserBinding

/**
 * @author XiaoXinC
 */
class BrowserFragment : Fragment() {
    private var _binding: FragmentBrowserBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBrowserBinding.inflate(inflater, container, false)


        with(binding){
            toSimple.setOnClickListener {
                findNavController().navigate(R.id.action2HelloWorldFragment)
            }
            toPlaceHolder.setOnClickListener {
                findNavController().navigate(R.id.action2Placeholder)
            }
            toInsertSeparators.setOnClickListener {
                findNavController().navigate(R.id.action2InsertSeparators)
            }
        }


        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}