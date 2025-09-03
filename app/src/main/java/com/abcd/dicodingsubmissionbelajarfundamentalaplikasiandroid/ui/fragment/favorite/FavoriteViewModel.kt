package com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.ui.fragment.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.data.modal.ListEventsModel
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.data.modal.ResponseModel
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.data.repository.FavoriteEventsRepository
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.utils.network.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: FavoriteEventsRepository
): ViewModel() {
    private val _favoriteEvents = MutableLiveData<UIState<List<ListEventsModel>>>()
    val getResponseFavoriteEvents : LiveData<UIState<List<ListEventsModel>>> = _favoriteEvents

    fun fetchFavoriteEvents(){
        _favoriteEvents.value = UIState.Loading
        viewModelScope.launch {
            try {
                val data = repository.getAllEvents()
                _favoriteEvents.value = UIState.Success(data)
            } catch (ex: Exception){
                _favoriteEvents.value = UIState.Failure("Error: ${ex.message}")
            }
        }
    }

}