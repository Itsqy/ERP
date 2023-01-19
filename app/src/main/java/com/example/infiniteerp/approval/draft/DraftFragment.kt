package com.example.infiniteerp.approval.draft

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.infiniteerp.approval.ApprovalViewModel
import com.example.infiniteerp.data.model.UserModel
import com.example.infiniteerp.data.model.UserPreferences
import com.example.infiniteerp.data.remote.response.ListOrder

import com.example.infiniteerp.databinding.FragmentDraftBinding
import com.example.infiniteerp.utils.ViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("settings")

class DraftFragment : Fragment() {

    private var _binding: FragmentDraftBinding? = null
    private val binding get() = _binding!!
    private lateinit var draftViewModel: DraftViewModel
    private lateinit var adapterDraft: DraftAdapter
    private lateinit var approvalViewModel: ApprovalViewModel
    private lateinit var user: UserModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDraftBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        draftViewModel = DraftViewModel(this)
        setUpViewModel()
        showLoading()
        setUpSearchView()


    }

    private fun setUpViewModel() {

        approvalViewModel = ViewModelProvider(
            this@DraftFragment,
            ViewModelFactory(UserPreferences.getInstance(requireContext().dataStore))
        )[ApprovalViewModel::class.java]

        approvalViewModel.getUser().observe(requireActivity()) {

            user = UserModel(
                it.username, it.password, it.clientId, it.orgId, it.token, true
            )

        }

        draftViewModel.showListRelease(user!!.username, user!!.password, "DR", false)
        showLoading()


        binding.swipeContianerDraft.setOnRefreshListener {
            draftViewModel = DraftViewModel(this)
            draftViewModel.showListRelease(user!!.username, user!!.password, "DR", false)
            showLoading()
            binding.svDraft.clearFocus()
            binding.swipeContianerDraft.isRefreshing = false
        }
    }

    private fun setUpSearchView() {
        binding.svDraft.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                binding.svDraft.clearFocus()
                draftViewModel.searchHeaderList(user!!.username, user!!.password, query.toString())


                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                draftViewModel.searchHeaderList(
                    user!!.username,
                    user!!.password,
                    newText.toString()
                )

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