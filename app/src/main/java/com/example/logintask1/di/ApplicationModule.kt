package com.example.logintask1.di

import com.example.logintask1.data.api.UserPostApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val POSTS_BASE_URL = "https://64fce528605a026163aedf15.mockapi.io/"

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
        @Provides
        @Singleton
        fun providePostsService(): UserPostApiService {
            return Retrofit
                .Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(POSTS_BASE_URL)
                .build()
                .create(UserPostApiService::class.java)

        }
// add other services here such as login api, etc.

}