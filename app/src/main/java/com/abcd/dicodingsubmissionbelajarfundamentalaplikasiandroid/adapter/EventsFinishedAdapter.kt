package com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.R
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.data.modal.ListEventsModel
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.databinding.ItemListFinishedEventsBinding
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.ui.activity.detail_event.DetailEventActivity
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.utils.DateAndTime
import com.bumptech.glide.Glide
import java.util.Locale

class EventsFinishedAdapter(
    private val listEvents : List<ListEventsModel>,
    private val home: Boolean
): RecyclerView.Adapter<EventsFinishedAdapter.ViewHolder>() {

    private var tempEvents = listEvents

    @SuppressLint("NotifyDataSetChanged", "DefaultLocale")
    fun searchData(kata: String){
        val vKata = kata.lowercase(Locale.getDefault()).trim()
        val data = listEvents.filter {
            (
                it.name!!.lowercase().trim().contains(vKata)
                or
                it.ownerName!!.lowercase().trim().contains(vKata)
            )
        }
        tempEvents = data
        notifyDataSetChanged()
    }

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
        return if(home && tempEvents.size>=5) 5 else tempEvents.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = tempEvents[position]
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
                .placeholder(R.drawable.loading)
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