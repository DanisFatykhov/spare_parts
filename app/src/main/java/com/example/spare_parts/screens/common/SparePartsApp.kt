package com.example.spare_parts.screens.common

import android.app.Application
import com.example.spare_parts.common.firebase.FirebaseAuthManager
import com.example.spare_parts.data.firebase.FirebaseUsersRepository
import com.example.spare_parts.data.firebase.FirebaseVehicleRepository

class SparePartsApp: Application() {
    val vehicleRepository by lazy { FirebaseVehicleRepository() }
    val authManager by lazy { FirebaseAuthManager() }
    val usersRepository by lazy { FirebaseUsersRepository() }
}