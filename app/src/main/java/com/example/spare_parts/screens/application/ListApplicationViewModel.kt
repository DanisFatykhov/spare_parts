package com.example.spare_parts.screens.application

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.spare_parts.data.VehicleRepository
import com.example.spare_parts.models.DataApplication
import com.example.spare_parts.models.DataVehicles
import com.example.spare_parts.screens.common.BaseViewModel
import com.google.android.gms.tasks.OnFailureListener

class ListApplicationViewModel(private val vehicleRepository: VehicleRepository): ViewModel() {

    lateinit var list: LiveData<List<DataApplication>>

    fun initApplication() {
        list = vehicleRepository.getListApplication()
    }

}