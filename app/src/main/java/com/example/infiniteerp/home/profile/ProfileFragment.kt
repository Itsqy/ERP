package com.example.infiniteerp.home.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.infiniteerp.approval.ApprovalViewModel
import com.example.infiniteerp.data.model.UserModel
import com.example.infiniteerp.data.model.UserPreferences
import com.example.infiniteerp.databinding.FragmentProfileBinding
import com.example.infiniteerp.utils.ViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("settings")

class ProfileFragment : Fragment() {
    //binding
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var approveViewModel: ApprovalViewModel
    private lateinit var user: UserModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        approveViewModel = ViewModelProvider(
            this@ProfileFragment,
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

            binding.tvName.text = user.username

        }
    }


}