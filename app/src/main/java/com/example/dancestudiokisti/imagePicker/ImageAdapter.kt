package com.example.dancestudiokisti.imagePicker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dancestudiokisti.R


class ImageAdapter(private val onImageClickListener: OnImageClickListener): RecyclerView.Adapter<ImagePickerItemHolder>() {

    private var images: List<Image> = emptyList()

    fun setImages(images: List<Image>) {
        this.images = images
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagePickerItemHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.image_picker_item, parent, false)
        return ImagePickerItemHolder(itemView, onImageClickListener)
    }

    override fun onBindViewHolder(holder: ImagePickerItemHolder, position: Int) {
        val image: Image = images[position]
        holder.bind(image.linkOfImage)
    }

    override fun getItemCount(): Int {
        return images.size
    }
}