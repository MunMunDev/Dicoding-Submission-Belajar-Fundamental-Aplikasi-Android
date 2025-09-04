package com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.utils

import androidx.recyclerview.widget.DiffUtil
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.data.modal.ListEventsModel

class EventDiffCallback(
    private val oldEventList: List<ListEventsModel>,
    private val newEventList: List<ListEventsModel>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldEventList.size
    override fun getNewListSize(): Int = newEventList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldEventList[oldItemPosition].id == newEventList[newItemPosition].id
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEvent = oldEventList[oldItemPosition]
        val newEvent = newEventList[newItemPosition]
        return oldEvent.id == newEvent.id
    }
}