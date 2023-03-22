package com.network.newsly.news_api.authentication.data.repository

import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(private val auth: FirebaseAuth) :
    AuthenticationRepository {

    private val user = auth.currentUser

    override suspend fun signIn(
        email: String,
        password: String,
        onSuccessListener: OnSuccessListener<AuthResult>,
        onFailureListener: OnFailureListener
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener(onSuccessListener)
            .addOnFailureListener(onFailureListener)
    }

    override suspend fun signUp(
        email: String,
        password: String,
        onSuccessListener: OnSuccessListener<AuthResult>,
        onFailureListener: OnFailureListener
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener(onSuccessListener)
            .addOnFailureListener(onFailureListener)
    }

    override suspend fun singOut() = auth.signOut()

    override suspend fun updateUserData(
        firstName: String,
        lastName: String,
        onSuccessListener: OnSuccessListener<Void>,
        onFailureListener: OnFailureListener
    ) {
        user?.updateProfile(userProfileChangeRequest {
            displayName = "$firstName $lastName"
        })
            ?.addOnSuccessListener(onSuccessListener)
            ?.addOnFailureListener(onFailureListener)
    }
}