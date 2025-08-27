package com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.ui.activity.search_events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.data.modal.ResponseModel
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.data.repository.ListEventsRepository
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.utils.network.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchEventsViewModel  @Inject constructor(
    private val repository: ListEventsRepository
) : ViewModel() {
    private val _events: MutableLiveData<UIState<ResponseModel>> = MutableLiveData<UIState<ResponseModel>>()
    val getResponseEvents: LiveData<UIState<ResponseModel>> = _events

    fun fetchEvents(
        active: Int,
        search: String
    ) {
        _events.value = UIState.Loading
        viewModelScope.launch {
            try {
                val data = repository.getResponseEvents(active, search)
                _events.value = UIState.Success(data)
            } catch (ex: Exception) {
                _events.value = UIState.Failure("Error: ${ex.message}")
            }
        }
    }
}