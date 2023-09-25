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

/*
* Hilt module used to provide a retrofit instance which can be used to call the HTTP methods defined
* in UserPostApiService
* */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    /*
    * Used to inject the UserPostApiService into other classes, returns a retrofit singleton
    * */
//    @Provides
//    @Singleton
    fun providePostsService(): UserPostApiService {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(POSTS_BASE_URL).build().create(UserPostApiService::class.java)

    }
// add other services here such as login api, etc.

}