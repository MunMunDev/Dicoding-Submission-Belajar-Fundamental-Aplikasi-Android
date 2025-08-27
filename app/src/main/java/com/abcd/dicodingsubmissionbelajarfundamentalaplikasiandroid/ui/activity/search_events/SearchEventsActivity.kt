package com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.ui.activity.search_events

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.R
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.adapter.EventsFinishedAdapter
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.data.modal.ListEventsModel
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.data.modal.ResponseModel
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.databinding.ActivitySearchEventsBinding
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.utils.network.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchEventsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchEventsBinding
    private val viewModel: SearchEventsViewModel by viewModels()
    private lateinit var eventsAdapter : EventsFinishedAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchEventsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.fetchEvents(-1, "")
        getResponseEvents()
    }
    private fun setSearchEvents() {
        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    searchBar.setText(searchView.text)
                    searchView.hide()
                    viewModel.fetchEvents(-1, searchView.text.toString())
                    false
                }
        }
    }

    private fun getResponseEvents(){
        viewModel.getResponseEvents.observe(this@SearchEventsActivity){result->
            when(result){
                is UIState.Loading-> setStartShimmerEvents()
                is UIState.Success-> getResponseSuccessEvents(result.data)
                is UIState.Failure-> getResponseFailureEvents(result.message)
            }
        }
    }

    private fun getResponseSuccessEvents(data: ResponseModel) {
        if(data.error == false){
            data.listEvents?.let {
                setAdapterEvents(it)
            }
        } else{
            Toast.makeText(this@SearchEventsActivity, data.message, Toast.LENGTH_SHORT).show()
        }
        setStopShimmerEvents()
    }

    private fun getResponseFailureEvents(message: String) {
        Toast.makeText(this@SearchEventsActivity, message, Toast.LENGTH_SHORT).show()
        setStopShimmerEvents()
    }

    private fun setAdapterEvents(listEvents: List<ListEventsModel>) {
        binding.apply {
            eventsAdapter = EventsFinishedAdapter(listEvents, false)
            rvEvents.apply {
                layoutManager = LinearLayoutManager(this@SearchEventsActivity, LinearLayoutManager.VERTICAL, false)
                adapter = eventsAdapter
            }
            setSearchEvents()
        }
    }

    private fun setStartShimmerEvents(){
        binding.apply {
            smEvents.startShimmer()
            smEvents.visibility = View.VISIBLE

            rvEvents.visibility = View.GONE
        }
    }

    private fun setStopShimmerEvents(){
        binding.apply {
            smEvents.stopShimmer()
            smEvents.visibility = View.GONE

            rvEvents.visibility = View.VISIBLE
        }
    }

}