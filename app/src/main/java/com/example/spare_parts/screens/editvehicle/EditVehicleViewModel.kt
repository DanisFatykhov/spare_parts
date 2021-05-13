package com.example.spare_parts.screens.editvehicle

import android.net.Uri
import androidx.lifecycle.LiveData
import com.example.spare_parts.data.VehicleRepository
import com.example.spare_parts.data.firebase.common.currentUid
import com.example.spare_parts.models.DataVehicles
import com.example.spare_parts.models.User
import com.example.spare_parts.screens.common.BaseViewModel
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.Task

class EditVehicleViewModel(private val vehicleRepository: VehicleRepository,
                           onFailureListener: OnFailureListener): BaseViewModel(onFailureListener) {
    lateinit var list: LiveData<List<DataVehicles>>

    fun vehicle(): LiveData<DataVehicles> = vehicleRepository.getVehicle()

    fun uploadAndSetVehiclePhoto(imageUri: Uri): Task<Unit> =
        vehicleRepository.uploadVehicleImage(imageUri).onSuccessTask {newImageUri ->
            vehicleRepository.updateVehicleImage(newImageUri!!)
        }.addOnFailureListener(onFailureListener)

    fun updateVehicle(currentVehicle: DataVehicles, newVehicle: DataVehicles) = vehicleRepository.updateVehicle(currentVehicle = currentVehicle, newVehicle = newVehicle)
        .addOnFailureListener(onFailureListener)

}


