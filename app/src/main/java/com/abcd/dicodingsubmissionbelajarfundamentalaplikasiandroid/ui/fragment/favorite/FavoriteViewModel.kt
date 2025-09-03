package com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.ui.fragment.favorite

import androidx.lifecycle.ViewModel
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.data.repository.FavoriteEventsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: FavoriteEventsRepository
): ViewModel() {

}