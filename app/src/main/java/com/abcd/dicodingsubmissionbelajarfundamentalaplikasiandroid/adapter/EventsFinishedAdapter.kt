package com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.R
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.data.modal.ListEventsModel
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.databinding.ItemListFinishedEventsBinding
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.utils.DateAndTime
import com.bumptech.glide.Glide

class EventsFinishedAdapter(
    private val listEvents : List<ListEventsModel>,
    private val home: Boolean
): RecyclerView.Adapter<EventsFinishedAdapter.ViewHolder>() {
    private val dateAndTime = DateAndTime()
    class ViewHolder(
        val binding: ItemListFinishedEventsBinding
    ): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemListFinishedEventsBinding.inflate(inflater, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if(home) 5 else listEvents.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listEvents[position]
        holder.binding.apply {
            tvNameEvent.text = data.name
            tvDescription.text = data.summary
            tvCategory.text = data.category
            data.beginTime?.let {
                tvTglPelatihan.text = dateAndTime.konversiBulanDanWaktu(it)
            }

            Glide.with(holder.itemView)
                .load(data.imageLogo) // URL Gambar
                .error(R.drawable.image_error)
                .into(ivImageEvent) // imageView mana yang akan diterapkan
        }
    }


}