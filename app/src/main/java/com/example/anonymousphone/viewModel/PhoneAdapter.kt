package com.example.anonymousphone.viewModel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.anonymousphone.R
import com.example.anonymousphone.databinding.ItemPhoneBinding
import com.example.anonymousphone.model.local.PhoneData
import com.example.anonymousphone.view.PhoneFragment
import java.text.NumberFormat

class PhoneAdapter(
    var phones : MutableList<PhoneData>,
    private var listener :OnClickListenerPhone,


) : RecyclerView.Adapter<PhoneAdapter.ViewHolder>() {

    private lateinit var mContext: Context

    inner class ViewHolder (view : View):RecyclerView.ViewHolder(view){
        val binding = ItemPhoneBinding.bind(view)

        init {
            view.setOnClickListener { listener.onClick(phones[adapterPosition].id) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_phone,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return phones.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val phone = phones.get(position)
        val imageUrl = phone.image

        with(holder){

            binding.textViewName.text = phone.name
            val priceFormatted = NumberFormat.getNumberInstance().format(phone.price)
            val priceText = mContext.getString(R.string.price_format, priceFormatted)
            binding.textViewPrice.text = priceText

            Glide.with(mContext)
                .load(imageUrl)

                .fitCenter()
                .placeholder(R.drawable.ic_image_placeholder)
                .error(R.drawable.ic_image_not_found)
                .into(binding.imageView)


        }
    }

}