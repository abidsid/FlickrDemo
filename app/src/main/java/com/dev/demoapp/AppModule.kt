package com.dev.demoapp

import com.dev.demoapp.data.remote.ThumbnailApiService
import com.dev.demoapp.data.repository.ThumbnailRepository
import com.dev.demoapp.data.repository.ThumbnailRepositoryImpl
import com.dev.demoapp.domain.usecase.GetThumbnailsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideThumbnailApiService(): ThumbnailApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.flickr.com/services/feeds/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ThumbnailApiService::class.java)
    }

    @Provides
    fun provideThumbnailRepository(apiService: ThumbnailApiService): ThumbnailRepository {
        return ThumbnailRepositoryImpl(apiService)
    }

    @Provides
    fun provideGetThumbnailsUseCase(repository: ThumbnailRepository): GetThumbnailsUseCase {
        return GetThumbnailsUseCase(repository)
    }
}
