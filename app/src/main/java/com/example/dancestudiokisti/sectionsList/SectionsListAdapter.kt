package com.example.dancestudiokisti.sectionsList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dancestudiokisti.R
import com.example.dancestudiokisti.newSection.Section

class SectionsListAdapter(private val onSectionLongPressListener: OnSectionLongPressListener, private val onSectionClickListener: OnSectionClickListener) : RecyclerView.Adapter<SectionsItemHolder>() {

    private var sections: List<Section> = emptyList()

    fun setSections(sections: List<Section>) {
        this.sections = sections
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionsItemHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.section_item, parent, false)
        return SectionsItemHolder(itemView, onSectionLongPressListener, onSectionClickListener)
    }

    override fun onBindViewHolder(holder: SectionsItemHolder, position: Int) {
        val section: Section = sections[position]
        holder.bind(section)
    }

    override fun getItemCount(): Int {
        return sections.size
    }
}