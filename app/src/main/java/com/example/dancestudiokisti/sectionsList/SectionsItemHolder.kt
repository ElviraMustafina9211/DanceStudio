package com.example.dancestudiokisti.sectionsList

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.dancestudiokisti.R
import com.example.dancestudiokisti.newSection.Section


class SectionsItemHolder(view: View, onSectionLongPressListener: OnSectionLongPressListener,
                         onSectionClickListener: OnSectionClickListener) : RecyclerView.ViewHolder(view) {

    private lateinit var section: Section

    private val tvSectionName: TextView = view.findViewById(R.id.name_of_section)

    private val ivImageLink: ImageView = view.findViewById(R.id.image_of_section)

    init {
        view.setOnClickListener {
            onSectionClickListener.onClick(section)
        }

        view.setOnLongClickListener{
            onSectionLongPressListener.onLongPressed(section)
            Toast.makeText(view.context, "Секция удалена", Toast.LENGTH_SHORT).show()
            true
        }
    }

    fun bind(section: Section) {
        this.section = section
        tvSectionName.text = section.name
        Glide.with(itemView.context)
            .load(section.imageLink)
            .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(12)))
            .into(ivImageLink)
    }
}