package com.example.spare_parts.screens.editvehicle

import androidx.lifecycle.LiveData
import com.example.spare_parts.data.VehicleRepository
import com.example.spare_parts.models.DataVehicles
import com.example.spare_parts.screens.common.BaseViewModel
import com.google.android.gms.tasks.OnFailureListener

class EditListViewModel(private val vehicleRepository: VehicleRepository, onFailureListener: OnFailureListener): BaseViewModel(onFailureListener) {
    lateinit var list: LiveData<List<DataVehicles>>

    fun initEditList() {
        list = vehicleRepository.getEditList()
    }
}