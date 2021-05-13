package com.example.spare_parts.screens.listvehicle

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.spare_parts.data.VehicleRepository
import com.example.spare_parts.models.DataVehicles

class ListViewModel(private val vehicleRepository: VehicleRepository): ViewModel() {

    lateinit var list: LiveData<List<DataVehicles>>

    fun initAutocranes() {
        list = vehicleRepository.getListAutocrane()
    }

    fun initConcreteMixers() {
        list = vehicleRepository.getListConcreteMixer()
    }

    fun initConcretePumps() {
        list = vehicleRepository.getListConcretePump()
    }

    fun initBoardManipulators() {
        list = vehicleRepository.getListBoardManipulator()
    }

    fun initDumps() {
        list = vehicleRepository.getListListDump()
    }

    fun initTrawls() {
        list = vehicleRepository.getListListTruck()
    }

    fun initBackhoeLoaders() {
        list = vehicleRepository.getListBackhoeLoader()
    }
}