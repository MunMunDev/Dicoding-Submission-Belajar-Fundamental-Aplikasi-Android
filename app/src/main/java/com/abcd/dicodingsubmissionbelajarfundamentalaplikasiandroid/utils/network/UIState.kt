package com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.utils.network

sealed class UIState<out R> {
    data object Loading: UIState<Nothing>()
    class Failure(val message: String): UIState<Nothing>()
    class Success<out T>(val data: T): UIState<T>()
}