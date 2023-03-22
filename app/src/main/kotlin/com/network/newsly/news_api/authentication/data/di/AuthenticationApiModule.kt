package com.network.newsly.news_api.authentication.data.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.network.newsly.news_api.authentication.data.repository.AuthenticationRepository
import com.network.newsly.news_api.authentication.data.repository.AuthenticationRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface AuthenticationApiModule {

    companion object {
        @Singleton
        @Provides
        fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth
    }

    @Binds
    fun bindAuthenticationRepository(impl: AuthenticationRepositoryImpl): AuthenticationRepository
}