package dev.jahidhasanco.fliq.data.network

import com.google.firebase.auth.FirebaseAuth

class FirebaseSource {

    private val firebaseAuth: FirebaseAuth
    get() = FirebaseAuth.getInstance()



    fun logout() = firebaseAuth.signOut()

    fun currentUser() = firebaseAuth.currentUser

}