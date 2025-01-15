package com.dev.demoapp.data.repository

import com.dev.demoapp.data.model.Thumbnail
import com.dev.demoapp.data.remote.ThumbnailApiService

class ThumbnailRepositoryImpl(private val apiService: ThumbnailApiService): ThumbnailRepository {
    override suspend fun searchThumbnails(query: String): Thumbnail {
        return apiService.searchThumbnails(query)
    }
}
