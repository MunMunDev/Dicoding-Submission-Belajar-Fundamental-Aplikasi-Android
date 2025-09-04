package com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.ui.activity.detail_event

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.data.modal.ListEventsModel
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.data.repository.FavoriteEventsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CrudFavoriteViewModel @Inject constructor(
    private val repository: FavoriteEventsRepository
): ViewModel() {

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> get() = _isFavorite

    private val _insertFavorite = MutableLiveData<Long>()
    val insertFavorite: LiveData<Long> get() = _insertFavorite

    private val _deleteFavorite = MutableLiveData<Int>()
    val deleteFavorite: LiveData<Int> get() = _deleteFavorite

    fun searchEventsFavorite(
        id: Int
    ){
        viewModelScope.launch {
            _isFavorite.value = repository.searchFavoriteEvents(id)
        }
    }

    fun insertFavoriteEvents(
        event: ListEventsModel
    ){
        viewModelScope.launch {
            _insertFavorite.value = repository.insert(event)
        }
    }

    fun deleteFavoriteEvents(
        event: ListEventsModel
    ){
        viewModelScope.launch {
            _deleteFavorite.value = repository.delete(event)
        }
    }

}