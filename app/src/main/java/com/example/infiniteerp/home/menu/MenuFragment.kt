package com.example.infiniteerp.home.menu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.infiniteerp.approval.ApprovalActivity
import com.example.infiniteerp.databinding.FragmentMenuBinding


class MenuFragment : Fragment() {


    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.procurementMenu.setOnClickListener {
            val intent = Intent(activity, ApprovalActivity::class.java)
            startActivity(intent)
        }
    }


}