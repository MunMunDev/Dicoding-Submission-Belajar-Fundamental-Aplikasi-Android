package com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.ui.fragment.finished

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.adapter.EventsFinishedAdapter
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.data.modal.ListEventsModel
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.data.modal.ResponseModel
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.databinding.FragmentFinishedBinding
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.ui.activity.search_events.SearchEventsActivity
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.utils.network.CheckNetwork
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.utils.network.UIState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FinishedFragment : Fragment() {

    private lateinit var binding: FragmentFinishedBinding
    private val viewModel: FinishedViewModel by viewModels()
    private lateinit var eventFinishedAdapter : EventsFinishedAdapter
    @Inject
    lateinit var checkNetwork: CheckNetwork

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            if(checkNetwork.isNetworkAvailable()){
                viewModel.fetchFinishedEvents(0, "")
            } else{
                Toast.makeText(requireContext(), "Tolong Aktifkan Jaringan Anda", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFinishedBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getResponseFinishedEvents()
    }

    private fun setSearchBerita() {
        binding.topAppBar.etSearch.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                eventFinishedAdapter.searchData(s.toString())
            }

        })
    }

    private fun getResponseFinishedEvents(){
        viewModel.getResponseFinishedEvents.observe(viewLifecycleOwner){result->
            when(result){
                is UIState.Loading-> setStartShimmerFinishedEvents()
                is UIState.Success-> getResponseSuccessFinishedEvents(result.data)
                is UIState.Failure-> getResponseFailureFinishedEvents(result.message)
            }
        }
    }

    private fun getResponseSuccessFinishedEvents(data: ResponseModel) {
        if(data.error == false){
            data.listEvents?.let {
                setAdapterFinishedEvents(it)
            }
        } else{
            Toast.makeText(requireContext(), data.message, Toast.LENGTH_SHORT).show()
        }
        setStopShimmerFinishedEvents()
    }

    private fun getResponseFailureFinishedEvents(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        setStopShimmerFinishedEvents()
    }

    private fun setAdapterFinishedEvents(listEvents: List<ListEventsModel>) {
        binding.apply {
            eventFinishedAdapter = EventsFinishedAdapter(listEvents, false)
            rvFinishedEvents.apply {
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                adapter = eventFinishedAdapter
            }
            setSearchBerita()
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