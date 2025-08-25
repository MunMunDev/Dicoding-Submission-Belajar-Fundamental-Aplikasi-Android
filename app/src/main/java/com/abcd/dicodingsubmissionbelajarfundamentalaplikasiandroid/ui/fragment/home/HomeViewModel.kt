package com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.ui.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.data.database.api.ApiService
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.data.modal.ResponseModel
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.data.repository.ListEventsRepository
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.utils.network.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ListEventsRepository
) : ViewModel() {
    private val _activeEvents = MutableLiveData<UIState<ResponseModel>>()
    val getResponseActiveEvents : LiveData<UIState<ResponseModel>> = _activeEvents

    private val _eventsFinished = MutableLiveData<UIState<ResponseModel>>()
    val getResponseEventsFinished : LiveData<UIState<ResponseModel>> = _eventsFinished

    fun fetchActiveEvents(
        active: Int,
        search: String
    ){
        _activeEvents.value = UIState.Loading
        viewModelScope.launch {
            try {
                val data = repository.getResponseEvents(active, search)
                _activeEvents.value = UIState.Success(data)
            } catch (ex: Exception){
                _activeEvents.value = UIState.Failure("Error: ${ex.message}")
            }
        }
    }

    fun fetchEventsFinished(
        active: Int,
        search: String
    ){
        _eventsFinished.value = UIState.Loading
        viewModelScope.launch {
            try {
                val data = repository.getResponseEvents(active, search)
                _eventsFinished.value = UIState.Success(data)
            } catch (ex: Exception){
                _eventsFinished.value = UIState.Failure("Error: ${ex.message}")
            }
        }
    }



}