package com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.ui.fragment.upcoming

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
class UpcomingViewModel @Inject constructor(
    private val repository: ListEventsRepository
) : ViewModel() {
    private val _upcomingEvents = MutableLiveData<UIState<ResponseModel>>()
    val getResponseUpcomingEvents: LiveData<UIState<ResponseModel>> = _upcomingEvents

    fun fetchUpcomingEvents(
        active: Int,
        search: String
    ) {
        _upcomingEvents.value = UIState.Loading
        viewModelScope.launch {
            try {
                val data = repository.getResponseEvents(active, search)
                _upcomingEvents.value = UIState.Success(data)
            } catch (ex: Exception) {
                _upcomingEvents.value = UIState.Failure("Error: ${ex.message}")
            }
        }
    }

}



