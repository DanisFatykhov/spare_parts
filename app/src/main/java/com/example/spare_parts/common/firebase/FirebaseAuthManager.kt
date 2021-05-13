package com.example.spare_parts.common.firebase

import com.example.spare_parts.common.AuthManager
import com.example.spare_parts.common.toUnit
import com.example.spare_parts.data.firebase.common.auth
import com.google.android.gms.tasks.Task

class FirebaseAuthManager: AuthManager {
    override fun signOut() {
        auth.signOut()
    }

    override fun signIn(email: String, password: String): Task<Unit> =
        auth.signInWithEmailAndPassword(email, password).toUnit()

    override fun sendPasswordResetEmail(email: String): Task<Unit> =
        auth.sendPasswordResetEmail(email).toUnit()
}