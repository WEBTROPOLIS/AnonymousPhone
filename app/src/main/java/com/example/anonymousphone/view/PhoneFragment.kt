package com.example.anonymousphone.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anonymousphone.R
import com.example.anonymousphone.databinding.FragmentPhoneBinding
import com.example.anonymousphone.viewModel.OnClickListenerPhone
import com.example.anonymousphone.viewModel.PhoneAdapter
import com.example.anonymousphone.viewModel.PhoneViewModel


class PhoneFragment : Fragment(), OnClickListenerPhone {

    private lateinit var mBinding : FragmentPhoneBinding
    private val viewModel : PhoneViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentPhoneBinding.inflate(inflater,container,false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = PhoneAdapter(mutableListOf(),this)
        mBinding.rvPhone.adapter = adapter
        mBinding.rvPhone.layoutManager =GridLayoutManager(requireContext(),2)

        viewModel.getAllPhone().observe(viewLifecycleOwner) { phoneList ->
            adapter.phones = phoneList.toMutableList()
            adapter.notifyDataSetChanged()
            val countInfo = resources.getString(R.string.count_phone_info, adapter.itemCount)
            mBinding.tvCountInfo.text = countInfo
        }


        mBinding.favExit.setOnClickListener { requireActivity().finish() }
    }

    override fun onClick(phoneId: Int) {
        val fragment = PhoneDetailsFragment()
        val bundle = Bundle()
        bundle.putInt("phoneId",phoneId)
        fragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .replace(R.id.frame, fragment)
            .addToBackStack(null)
            .commit()
    }


}