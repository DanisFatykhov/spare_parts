package com.example.spare_parts.screens.vehicle

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.spare_parts.data.UsersRepository
import com.example.spare_parts.models.User


class VehicleViewModel(usersRepository: UsersRepository): ViewModel() {

    val user: LiveData<User> = usersRepository.getUser()
}