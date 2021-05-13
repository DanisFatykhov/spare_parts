package com.example.spare_parts.screens.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.spare_parts.screens.addvehicle.AddVehicleViewModel
import com.example.spare_parts.data.firebase.FirebaseVehicleRepository
import com.example.spare_parts.screens.addapplication.AddApplicationViewModel
import com.example.spare_parts.screens.application.ApplicationViewModel
import com.example.spare_parts.screens.application.ListApplicationViewModel
import com.example.spare_parts.screens.editprofile.EditProfileViewModel
import com.example.spare_parts.screens.editvehicle.EditListViewModel
import com.example.spare_parts.screens.editvehicle.EditVehicleViewModel
import com.example.spare_parts.screens.listvehicle.ListViewModel
import com.example.spare_parts.screens.login.LoginViewModel
import com.example.spare_parts.screens.main.MainViewModel
import com.example.spare_parts.screens.register.RegisterViewModel
import com.example.spare_parts.screens.vehicle.VehicleViewModel
import com.google.android.gms.tasks.OnFailureListener

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val app: SparePartsApp,
                       private val commonViewModel: CommonViewModel,
                       private val onFailureListener: OnFailureListener) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val vehicleRepository = app.vehicleRepository
        val authManager = app.authManager
        val usersRepository = app.usersRepository


        if (modelClass.isAssignableFrom((AddVehicleViewModel::class.java))) {
            return AddVehicleViewModel(vehicleRepository, onFailureListener) as T
        } else if (modelClass.isAssignableFrom(EditProfileViewModel::class.java)) {
            return EditProfileViewModel(onFailureListener, usersRepository) as T
        } else if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(authManager, onFailureListener) as T
        } else if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                authManager,
                app,
                commonViewModel,
                onFailureListener
            ) as T
        } else if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel() as T
        } else if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            return ListViewModel(FirebaseVehicleRepository()) as T
        } else if (modelClass.isAssignableFrom(VehicleViewModel::class.java)) {
            return VehicleViewModel(
                usersRepository
            ) as T
        } else if (modelClass.isAssignableFrom(ListApplicationViewModel::class.java)) {
            return ListApplicationViewModel(
                vehicleRepository
            ) as T
        } else if (modelClass.isAssignableFrom(EditListViewModel::class.java)) {
            return EditListViewModel(
                vehicleRepository,
                onFailureListener
            ) as T
        } else if (modelClass.isAssignableFrom(EditVehicleViewModel::class.java)) {
            return EditVehicleViewModel(
                vehicleRepository,
                onFailureListener
            ) as T
        } else if (modelClass.isAssignableFrom(AddApplicationViewModel::class.java)) {
            return AddApplicationViewModel(vehicleRepository, onFailureListener) as T
        } else if (modelClass.isAssignableFrom(ApplicationViewModel::class.java)) {
            return ApplicationViewModel(
                usersRepository
            ) as T
        } else {
            error("Неизвестный view model класс $modelClass")
        }
    }
}
