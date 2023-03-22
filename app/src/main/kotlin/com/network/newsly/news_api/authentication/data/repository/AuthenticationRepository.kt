package com.network.newsly.news_api.authentication.data.repository

import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.AuthResult

interface AuthenticationRepository {

    suspend fun signIn(
        email: String,
        password: String,
        onSuccessListener: OnSuccessListener<AuthResult>,
        onFailureListener: OnFailureListener
    )

    suspend fun signUp(
        email: String,
        password: String,
        onSuccessListener: OnSuccessListener<AuthResult>,
        onFailureListener: OnFailureListener
    )

    suspend fun singOut()

    suspend fun updateUserData(
        firstName: String,
        lastName: String,
        onSuccessListener: OnSuccessListener<Void>,
        onFailureListener: OnFailureListener
    )
}