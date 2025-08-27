package com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.ui.fragment.home

import android.content.Intent
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
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.ui.activity.search_events.SearchEventsActivity
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.utils.network.CheckNetwork
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.utils.network.UIState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var eventUpcomingAdapter : UpcomingEventsAdapter
    private lateinit var eventFinishedAdapter : EventsFinishedAdapter
    @Inject
    lateinit var checkNetwork: CheckNetwork

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            if(checkNetwork.isNetworkAvailable()){
                viewModel.fetchActiveEvents(1, "")
                viewModel.fetchEventsFinished(0, "")
            } else{
                Toast.makeText(requireContext(), "Tolong Aktifkan Jaringan Anda", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchActiveEvents(1, "")
        viewModel.fetchEventsFinished(0, "")
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

        setButton()
        getResponseActiveEvents()
        getResponseEventsFinished()
    }

    private fun setButton() {
        binding.apply {
            tvSrcData.setOnClickListener {
                startActivity(Intent(requireActivity(), SearchEventsActivity::class.java))
            }
        }
    }

    private fun getResponseActiveEvents(){
        viewModel.getResponseActiveEvents.observe(viewLifecycleOwner){result->
            when(result){
                is UIState.Loading-> setStartShimmerUpcomingEvents()
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
        setStopShimmerUpcomingEvents()
    }

    private fun getResponseFailureActiveEvents(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        setStopShimmerUpcomingEvents()
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

    private fun setStartShimmerUpcomingEvents(){
        binding.apply {
            smUpcomingEvents.startShimmer()
            smUpcomingEvents.visibility = View.VISIBLE

            rvUpcomingEvents.visibility = View.GONE
        }
    }

    private fun setStopShimmerUpcomingEvents(){
        binding.apply {
            smUpcomingEvents.stopShimmer()
            smUpcomingEvents.visibility = View.GONE

            rvUpcomingEvents.visibility = View.VISIBLE
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