package com.example.infiniteerp.approval.draft

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.infiniteerp.data.remote.response.ListOrder
import com.example.infiniteerp.databinding.FragmentDraftBinding


class DraftFragment : Fragment() {

    private var _binding: FragmentDraftBinding? = null
    private val binding get() = _binding!!
    private lateinit var draftViewModel: DraftViewModel
    private lateinit var adapter: DraftAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDraftBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        draftViewModel = DraftViewModel(this)
        draftViewModel.showListRelease("DR")

        showLoading()


    }

    fun showLoading() {
        draftViewModel.isLoading.observe(viewLifecycleOwner) {
            binding?.apply {
                if (it) {
                    pbRelease.visibility = View.VISIBLE
                    rvRelease.visibility = View.INVISIBLE
                } else {
                    pbRelease.visibility = View.GONE
                    rvRelease.visibility = View.VISIBLE
                }
            }
        }
    }

    fun setListOrder(dataOrder: List<ListOrder>?) {
        adapter = DraftAdapter(dataOrder)
        adapter.notifyDataSetChanged()
        binding.apply {
            rvRelease.setHasFixedSize(true)
            rvRelease.layoutManager = LinearLayoutManager(activity)
            rvRelease.adapter = adapter
        }

    }


}