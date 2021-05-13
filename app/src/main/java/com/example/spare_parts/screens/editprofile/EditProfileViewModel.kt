package com.example.spare_parts.screens.editprofile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.spare_parts.data.UsersRepository
import com.example.spare_parts.data.VehicleRepository
import com.example.spare_parts.models.DataVehicles
import com.example.spare_parts.models.User
import com.example.spare_parts.screens.common.BaseViewModel
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.Task

class EditProfileViewModel(onFailureListener: OnFailureListener,
                           private val usersRepository: UsersRepository) : BaseViewModel(onFailureListener) {
    val user: LiveData<User> = usersRepository.getUser()

    fun updateEmail(currentEmail: String, newEmail: String, password: String): Task<Unit> =
        usersRepository.updateEmail(currentEmail = currentEmail, newEmail = newEmail, password = password)
            .addOnFailureListener(onFailureListener)

    fun updateUserProfile(currentUser: User, newUser: User): Task<Unit> =
        usersRepository.updateUserProfile(currentUser = currentUser, newUser = newUser)
            .addOnFailureListener(onFailureListener)
}