package com.example.spare_parts.screens.application

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.spare_parts.data.UsersRepository
import com.example.spare_parts.models.User

class ApplicationViewModel(usersRepository: UsersRepository): ViewModel() {

    val user: LiveData<User> = usersRepository.getUser()
}
