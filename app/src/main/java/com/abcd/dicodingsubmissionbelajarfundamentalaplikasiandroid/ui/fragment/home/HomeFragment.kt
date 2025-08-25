package com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.adapter.EventsFinishedAdapter
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.adapter.UpcomingEventsAdapter
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.data.modal.ListEventsModel
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.data.modal.ResponseModel
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.databinding.FragmentHomeBinding
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.utils.network.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var eventUpcomingAdapter : UpcomingEventsAdapter
    private lateinit var eventFinishedAdapter : EventsFinishedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (viewModel.getResponseActiveEvents.value == null) {
            viewModel.fetchActiveEvents(1, "")
        }
        if (viewModel.getResponseEventsFinished.value == null) {
            viewModel.fetchEventsFinished(0, "")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getResponseActiveEvents()
        getResponseEventsFinished()
    }

    private fun getResponseActiveEvents(){
        viewModel.getResponseActiveEvents.observe(viewLifecycleOwner){result->
            when(result){
                is UIState.Loading-> {}
                is UIState.Success-> getResponseSuccessActiveEvents(result.data)
                is UIState.Failure-> getResponseFailureActiveEvents(result.message)
            }
        }
    }

    private fun getResponseSuccessActiveEvents(data: ResponseModel) {
        if(data.error == false){
            data.listEvents?.let {
                setAdapterActiveEvents(it)
            }
        } else{
            Toast.makeText(requireContext(), data.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun getResponseFailureActiveEvents(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun setAdapterActiveEvents(listEvents: List<ListEventsModel>) {
        binding.apply {
            eventUpcomingAdapter = UpcomingEventsAdapter(listEvents, true)
            rvUpcomingEvents.apply {
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = eventUpcomingAdapter
            }
        }
    }

    private fun getResponseEventsFinished(){
        viewModel.getResponseEventsFinished.observe(viewLifecycleOwner){result->
            when(result){
                is UIState.Loading-> setStartShimmerFinishedEvents()
                is UIState.Success-> getResponseSuccessEventsFinished(result.data)
                is UIState.Failure-> getResponseFailureEventsFinished(result.message)
            }
        }
    }

    private fun getResponseSuccessEventsFinished(data: ResponseModel) {
        if(data.error == false){
            data.listEvents?.let {
                setAdapterEventsFinished(it)
            }
        } else{
            Toast.makeText(requireContext(), data.message, Toast.LENGTH_SHORT).show()
        }
        setStopShimmerFinishedEvents()
    }

    private fun getResponseFailureEventsFinished(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        setStopShimmerFinishedEvents()
    }

    private fun setAdapterEventsFinished(listEvents: List<ListEventsModel>) {
        binding.apply {
            eventFinishedAdapter = EventsFinishedAdapter(listEvents, true)
            rvFinishedEvents.apply {
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                adapter = eventFinishedAdapter
            }
        }
    }

    private fun setStartShimmerFinishedEvents(){
        binding.apply {
            smFinishedEvents.startShimmer()
            smFinishedEvents.visibility = View.VISIBLE

            rvFinishedEvents.visibility = View.GONE
        }
    }

    private fun setStopShimmerFinishedEvents(){
        binding.apply {
            smFinishedEvents.stopShimmer()
            smFinishedEvents.visibility = View.GONE

            rvFinishedEvents.visibility = View.VISIBLE
        }
    }
}