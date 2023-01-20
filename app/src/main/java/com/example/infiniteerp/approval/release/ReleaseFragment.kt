package com.example.infiniteerp.approval.release

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
import com.example.infiniteerp.databinding.FragmentReleaseBinding
import com.example.infiniteerp.utils.ViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("settings")

class ReleaseFragment : Fragment() {


    private var _binding: FragmentReleaseBinding? = null
    private val binding get() = _binding!!
    private lateinit var releaseViewModel: ReleaseViewModel
    private lateinit var adapterRelease: ReleaseAdapter
    private lateinit var approveViewModel: ApprovalViewModel
    private lateinit var user: UserModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReleaseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        releaseViewModel = ReleaseViewModel(this)
        setUpViewModel()
        showLoading()
        setUpSearchView()


    }

    private fun setUpViewModel() {
        approveViewModel = ViewModelProvider(
            this@ReleaseFragment,
            ViewModelFactory(UserPreferences.getInstance(requireContext().dataStore))
        )[ApprovalViewModel::class.java]

        approveViewModel.getUser().observe(requireActivity()) {
            user = UserModel(
                it.username,
                it.password,
                it.clientId,
                it.orgId,
                it.token,
                true
            )
            releaseViewModel.showListRelease(user.username, user.password, "CO", false)
            binding.swipefreshlayoutDraft.setOnRefreshListener {
                releaseViewModel.showListRelease(user.username, user.password, "CO", false)
                showLoading()
                binding.swipefreshlayoutDraft.isRefreshing = false


            }


        }
    }

    private fun setUpSearchView() {
        binding.svRelease.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                binding.svRelease.clearFocus()
                releaseViewModel.searchHeaderList(user.username, user.password, query.toString())


                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                releaseViewModel.searchHeaderList(user.username, user.password, newText.toString())

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

// udh muncul response nya tapi g muncul di layar
//    fun testLiveData() {
//        binding.apply {
//            rvRelease.layoutManager = LinearLayoutManager(activity)
//            rvRelease.setHasFixedSize(true)
//            adapterReleaseNew = ReleaseAdapterNew()
////            adapterReleaseNew.notifyDataSetChanged()
//            rvRelease.adapter = adapterReleaseNew
//        }
//
//        releaseViewModel.showListRelease("demo", "demo", "CO", false)
//        releaseViewModel.itemStory.observe(requireActivity()) {
//            adapterReleaseNew.setListStory(it)
//        }
//    }
//jadi lebih update setelah approve , tapi geser dikit jadi loading halamannya
//    override fun onResume() {
//        super.onResume()
//        setUpViewModel()
//    }


}