package com.example.dancestudiokisti.imagePicker

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.dancestudiokisti.R

class ImagePickerItemHolder(view: View, onImageClickListener: OnImageClickListener) : RecyclerView.ViewHolder(view) {
    private lateinit var linkOfImage: String

    private val ivLinkOfImage: ImageView = view.findViewById(R.id.image_for_section)

    init {
        view.setOnClickListener {
            onImageClickListener.onImageClicked(linkOfImage)
        }
    }

    fun bind(linkOfImage: String) {
        this.linkOfImage = linkOfImage
        Glide.with(itemView.context)
            .load(linkOfImage)
            .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(12)))
            .into(ivLinkOfImage)
    }
}