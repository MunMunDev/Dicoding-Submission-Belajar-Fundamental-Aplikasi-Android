package com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.ui.fragment.upcoming

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
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.databinding.FragmentUpcomingBinding
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.utils.network.CheckNetwork
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.utils.network.UIState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UpcomingFragment : Fragment() {

    private lateinit var binding: FragmentUpcomingBinding
    private val viewModel: UpcomingViewModel by viewModels()
    private lateinit var eventFinishedAdapter : EventsFinishedAdapter
    @Inject lateinit var checkNetwork: CheckNetwork

    override fun onResume() {
        super.onResume()
        if(checkNetwork.isNetworkAvailable()){
            viewModel.fetchUpcomingEvents(1, "")
        } else{
            Toast.makeText(requireContext(), "Tolong Aktifkan Jaringan Anda", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpcomingBinding.inflate(layoutInflater)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getResponseUpcomingEvents()
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

    private fun getResponseUpcomingEvents(){
        viewModel.getResponseUpcomingEvents.observe(viewLifecycleOwner){result->
            when(result){
                is UIState.Loading-> setStartShimmerUpcomingEvents()
                is UIState.Success-> getResponseSuccessUpcomingEvents(result.data)
                is UIState.Failure-> getResponseFailureUpcomingEvents(result.message)
            }
        }
    }

    private fun getResponseSuccessUpcomingEvents(data: ResponseModel) {
        if(data.error == false){
            data.listEvents?.let {
                setAdapterUpcomingEvents(it)
            }
        } else{
            Toast.makeText(requireContext(), data.message, Toast.LENGTH_SHORT).show()
        }
        setStopShimmerUpcomingEvents()
    }

    private fun getResponseFailureUpcomingEvents(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        setStopShimmerUpcomingEvents()
    }

    private fun setAdapterUpcomingEvents(listEvents: List<ListEventsModel>) {
        binding.apply {
            eventFinishedAdapter = EventsFinishedAdapter(listEvents, false)
            rvUpcomingEvents.apply {
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                adapter = eventFinishedAdapter
            }
            setSearchBerita()
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

}