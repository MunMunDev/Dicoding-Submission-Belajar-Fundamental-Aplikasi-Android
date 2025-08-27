package com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.R
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.data.modal.ListEventsModel
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.databinding.ItemListUpcomingEventsBinding
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.ui.activity.detail_event.DetailEventActivity
import com.bumptech.glide.Glide

class UpcomingEventsAdapter(
    private val listEvents : List<ListEventsModel>,
    private val home: Boolean
): RecyclerView.Adapter<UpcomingEventsAdapter.ViewHolder>() {
    class ViewHolder(
        val binding: ItemListUpcomingEventsBinding
    ): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemListUpcomingEventsBinding.inflate(inflater, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if(home && listEvents.size>=5) 5 else listEvents.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listEvents[position]
        holder.binding.apply {
            tvNameEvent.text = data.name

            Glide.with(holder.itemView)
                .load(data.imageLogo) // URL Gambar
                .error(R.drawable.image_error)
                .placeholder(R.drawable.loading_image)
                .into(ivImageEvent) // imageView mana yang akan diterapkan

            // Loading image
            // https://www.google.com/url?sa=i&url=https%3A%2F%2Fmedium.com%2F%40lisadziuba%2Feverything-you-need-to-know-about-loading-animations-10db7f9b61e&psig=AOvVaw1hr4PHxl90GaP8VxUSHtTA&ust=1756362505007000&source=images&cd=vfe&opi=89978449&ved=2ahUKEwjYofeqrqqPAxX1bmwGHXmiKowQjhx6BAgAEBo
        }
        holder.itemView.setOnClickListener {
            val i = Intent(
                holder.itemView.context.applicationContext, DetailEventActivity::class.java
            )
            i.putExtra("list_event", data)
            holder.itemView.context.startActivity(i)
        }
    }


}