package com.dev.demoapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.demoapp.data.model.Thumbnail
import com.dev.demoapp.domain.usecase.GetThumbnailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThumbnailViewModel @Inject constructor(private val getThumbnailsUseCase: GetThumbnailsUseCase) : ViewModel() {

    private val _thumbnails = MutableStateFlow<Thumbnail?>(null)
    val thumbnails: StateFlow<Thumbnail?> = _thumbnails

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }

    fun searchThumbnails(query: String) {
        viewModelScope.launch {
            _thumbnails.value = getThumbnailsUseCase.execute(query)
        }
    }

    init {
        // Initial loading with empty query or default query
        Log.d("Search>>", _searchQuery.value)
        searchThumbnails( _searchQuery.value )
    }
}
