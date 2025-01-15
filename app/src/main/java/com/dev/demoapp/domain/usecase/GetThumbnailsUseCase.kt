package com.dev.demoapp.domain.usecase

import com.dev.demoapp.data.model.Thumbnail
import com.dev.demoapp.data.repository.ThumbnailRepository

class GetThumbnailsUseCase(private val repository: ThumbnailRepository) {
    suspend fun execute(query: String): Thumbnail {
        return repository.searchThumbnails(query)
    }
}
