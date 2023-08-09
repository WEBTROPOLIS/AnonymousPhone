package com.example.anonymousphone.view

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.anonymousphone.R
import com.example.anonymousphone.databinding.ActivityMainBinding
import com.example.anonymousphone.databinding.FragmentPhoneDetailsBinding
import com.example.anonymousphone.model.local.PhoneDetailsData
import com.example.anonymousphone.viewModel.PhoneViewModel
import kotlinx.coroutines.launch
import java.text.NumberFormat


class PhoneDetailsFragment : Fragment() {

    private lateinit var mBinding: FragmentPhoneDetailsBinding
    private val viewModel: PhoneViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentPhoneDetailsBinding.inflate(inflater, container, false)
        val phoneId = arguments?.getInt("phoneId", -1) ?: -1
        var phoneDetails: PhoneDetailsData? = null

        if (phoneId != -1) {
            viewLifecycleOwner.lifecycleScope.launch {
                 phoneDetails = viewModel.getOnePhone(phoneId)
                val imageUrl : String
                if (phoneDetails != null) {
                    val priceFormated = NumberFormat.getNumberInstance().format(phoneDetails!!.price)
                    val lastPriceFormated = NumberFormat.getNumberInstance().format(phoneDetails!!.lastPrice)
                    mBinding.tvName.text = phoneDetails!!.name
                    mBinding.tvDescription.text = phoneDetails!!.description
                    mBinding.toolbar.title = phoneDetails?.name
                    "${getString(R.string.antes_precio)}$lastPriceFormated".also { mBinding.tvLastPrice.text = it }
                    mBinding.tvLastPrice.paintFlags = mBinding.tvLastPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    "${getString(R.string.ahora_precio)} ${priceFormated}".also { mBinding.tvPrice.text = it }
                    imageUrl = phoneDetails!!.image
                    Glide.with(mBinding.root)
                        .load(imageUrl)
                        .fitCenter()
                        .placeholder(R.drawable.ic_image_placeholder)
                        .error(R.drawable.ic_image_not_found)
                        .into(mBinding.imgPhone)

                    if (phoneDetails!!.credit){
                        mBinding.tvFormaPago.text = getString(R.string.formadepago_true)
                        mBinding.imageCredit.setImageResource(R.drawable.ic_credit_ok)
                    }else{
                        mBinding.tvFormaPago.text = getString(R.string.formadepago_false)
                        mBinding.imageCredit.setImageResource(R.drawable.ic_credit_off)
                    }
                }
            }

            mBinding.fabBack.setOnClickListener { parentFragmentManager.popBackStack() }

            mBinding.fabEmail.setOnClickListener {
                if (phoneDetails != null) {
                    val subject = getString(R.string.consulta_subject, phoneDetails!!.name, phoneDetails!!.id.toString())
                    val message = getString(R.string.consulta_message, phoneDetails!!.name, phoneDetails!!.id.toString())



                    val builder = AlertDialog.Builder(requireContext())
                    builder.setTitle(getString(R.string.dialog_title_message))
                    builder.setMessage(getString(R.string.dialog_message))
                    builder.setPositiveButton(getString(R.string.btn_positive_text)){_,_ ->
                        val intent = Intent(Intent.ACTION_SEND)
                        intent.type ="text/plain"
                        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(getString(R.string.email_to_send)))
                        intent.putExtra(Intent.EXTRA_SUBJECT,subject)
                        intent.putExtra(Intent.EXTRA_TEXT,message)
                        startActivity(Intent.createChooser(intent,"Enviar correo"))
                    }
                    builder.setNegativeButton(getString(R.string.btn_negative_text)) {dialog, _ ->
                        dialog.dismiss()
                    }

                    val alertDialog =builder.create()



                    alertDialog.setOnShowListener {
                        val positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
                        positiveButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.text_icon))
                        positiveButton.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.positive_color)

                        val negativeButton = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE)
                        negativeButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.text_icon))
                        negativeButton.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.negative_color)
                    }

                    alertDialog.show()


                }

            }
        }

        return mBinding.root
    }
}
