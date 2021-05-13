package com.example.spare_parts.data

import androidx.lifecycle.LiveData
import com.example.spare_parts.models.DataVehicles
import com.example.spare_parts.models.User
import com.google.android.gms.tasks.Task

interface UsersRepository {
    fun getUser(): LiveData<User>
    fun updateEmail(currentEmail: String, newEmail: String, password: String): Task<Unit>
    fun updateUserProfile(currentUser: User, newUser: User): Task<Unit>
    fun isUserExistsForEmail(email: String): Task<Boolean>
    //fun createUser(user: User, password: String): Task<Unit>
}