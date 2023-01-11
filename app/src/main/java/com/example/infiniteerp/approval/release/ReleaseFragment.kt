package com.example.infiniteerp.approval.release

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.infiniteerp.approval.draft.DraftAdapter
import com.example.infiniteerp.data.remote.response.ListOrder
import com.example.infiniteerp.databinding.FragmentReleaseBinding


class ReleaseFragment : Fragment() {

    private var _binding: FragmentReleaseBinding? = null
    private val binding get() = _binding!!
    private lateinit var releaseViewModel: ReleaseViewModel
    private lateinit var adapterRelease: ReleaseAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReleaseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        releaseViewModel = ReleaseViewModel(this)
        releaseViewModel.showListRelease("CO", false)
        showLoading()

        binding.swipefreshlayoutDraft.setOnRefreshListener {
            releaseViewModel = ReleaseViewModel(this)
            releaseViewModel.showListRelease("CO", false)
            showLoading()
            binding.swipefreshlayoutDraft.isRefreshing = false


        }

        binding.svRelease.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                binding.svRelease.clearFocus()
                releaseViewModel.searchHeaderList(query.toString())


                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                releaseViewModel.searchHeaderList(newText.toString())

                return false
            }

        })


    }

    fun showLoading() {
        releaseViewModel.isLoading.observe(viewLifecycleOwner) {
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
        adapterRelease = ReleaseAdapter(dataOrder)
        adapterRelease.notifyDataSetChanged()
        binding.apply {
            rvRelease.setHasFixedSize(true)
            rvRelease.layoutManager = LinearLayoutManager(activity)
            rvRelease.adapter = adapterRelease
        }

    }


    fun setResultSearch(dataOrder: List<ListOrder>?) {
        adapterRelease = ReleaseAdapter(dataOrder)
        adapterRelease.notifyDataSetChanged()
        binding.apply {
            rvRelease.setHasFixedSize(true)
            rvRelease.layoutManager = LinearLayoutManager(activity)
            rvRelease.adapter = adapterRelease
        }


    }




}