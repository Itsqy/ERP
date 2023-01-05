package com.example.infiniteerp.approval.release

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.infiniteerp.data.remote.response.ListOrder
import com.example.infiniteerp.databinding.FragmentReleaseBinding


class ReleaseFragment : Fragment() {

    private var _binding: FragmentReleaseBinding? = null
    private val binding get() = _binding!!
    private lateinit var releaseViewModel: ReleaseViewModel
    private lateinit var adapter: ReleaseAdapter


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
        releaseViewModel.showListRelease("CO")
        showLoading()


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
        adapter = ReleaseAdapter(dataOrder)
        adapter.notifyDataSetChanged()
        binding.apply {
            rvRelease.setHasFixedSize(true)
            rvRelease.layoutManager = LinearLayoutManager(activity)
            rvRelease.adapter = adapter
        }

    }


}