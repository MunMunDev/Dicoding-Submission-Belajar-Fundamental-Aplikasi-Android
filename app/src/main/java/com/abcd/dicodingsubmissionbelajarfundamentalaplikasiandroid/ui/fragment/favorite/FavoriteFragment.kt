package com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.ui.fragment.favorite

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.adapter.EventsFinishedAdapter
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.data.modal.ListEventsModel
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.databinding.FragmentFavoriteBinding
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.utils.network.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private var binding: FragmentFavoriteBinding? = null
    private lateinit var eventFavoriteAdapter : EventsFinishedAdapter
    private val viewModel: FavoriteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.fetchFavoriteEvents()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getResponseFavoriteEvents()
    }

    private fun setSearchEvents() {
        binding?.topAppBar!!.etSearch.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                eventFavoriteAdapter.searchData(s.toString())
            }

        })
    }

    private fun getResponseFavoriteEvents(){
        viewModel.getResponseFavoriteEvents.observe(viewLifecycleOwner){result->
            when(result){
                is UIState.Loading-> {}
                is UIState.Success-> getResponseSuccessFavoriteEvents(result.data)
                is UIState.Failure-> getResponseFailureFavoriteEvents(result.message)
            }
        }
    }

    private fun getResponseSuccessFavoriteEvents(listEvents: List<ListEventsModel>) {
        if(listEvents.isNotEmpty()){
            setAdapterFavoriteEvents(listEvents)
        } else{
            Toast.makeText(requireContext(), "Tidak Ada data", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getResponseFailureFavoriteEvents(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        Log.d("DetailTAG", "getResponseFailureFavoriteEvents: $message")
    }

    private fun setAdapterFavoriteEvents(listEvents: List<ListEventsModel>) {
        binding?.let { mBinding->
            mBinding.apply {
                eventFavoriteAdapter = EventsFinishedAdapter(listEvents, false)
                rvFavoriteEvents.apply {
                    layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                    adapter = eventFavoriteAdapter
                }
                setSearchEvents()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}