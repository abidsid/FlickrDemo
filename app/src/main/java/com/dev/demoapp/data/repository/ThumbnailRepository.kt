package com.dev.demoapp.data.repository

import com.dev.demoapp.data.model.Thumbnail

interface ThumbnailRepository {
    suspend fun searchThumbnails(query: String): Thumbnail
}
