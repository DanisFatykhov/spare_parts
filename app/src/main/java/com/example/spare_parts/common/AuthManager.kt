package com.example.spare_parts.common

import com.google.android.gms.tasks.Task

interface AuthManager {
    fun signOut()
    fun signIn(email: String, password: String): Task<Unit>
    fun sendPasswordResetEmail(email: String): Task<Unit>
}