package com.example.infiniteerp.approval.draft

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.infiniteerp.data.remote.response.ListOrder
import com.example.infiniteerp.databinding.FragmentDraftBinding


class DraftFragment : Fragment() {

    private var _binding: FragmentDraftBinding? = null
    private val binding get() = _binding!!
    private lateinit var draftViewModel: DraftViewModel
    private lateinit var adapterDraft: DraftAdapter


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
        draftViewModel.showListRelease("DR", false)
        showLoading()


        binding.swipeContianerDraft.setOnRefreshListener {
            draftViewModel = DraftViewModel(this)
            draftViewModel.showListRelease("DR", false)
            showLoading()
            binding.svDraft.clearFocus()
            binding.swipeContianerDraft.isRefreshing = false
        }

        binding.svDraft.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                binding.svDraft.clearFocus()
                draftViewModel.searchHeaderList(query.toString())


                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                draftViewModel.searchHeaderList(newText.toString())

                return false
            }

        })


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
        adapterDraft = DraftAdapter(dataOrder)
        adapterDraft.notifyDataSetChanged()
        binding.apply {
            rvRelease.setHasFixedSize(true)
            rvRelease.layoutManager = LinearLayoutManager(activity)
            rvRelease.adapter = adapterDraft
        }

    }

    fun setResultSearch(dataOrder: List<ListOrder>?) {
        adapterDraft = DraftAdapter(dataOrder)
        adapterDraft.notifyDataSetChanged()
        binding.apply {
            rvRelease.setHasFixedSize(true)
            rvRelease.layoutManager = LinearLayoutManager(activity)
            rvRelease.adapter = adapterDraft
        }


    }


}