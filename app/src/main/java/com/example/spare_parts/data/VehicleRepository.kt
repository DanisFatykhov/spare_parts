package com.example.spare_parts.data

import android.net.Uri
import androidx.lifecycle.LiveData
import com.example.spare_parts.models.*
import com.google.android.gms.tasks.Task

interface VehicleRepository {
    fun getUser(): LiveData<User>
    fun getVehicle(): LiveData<DataVehicles>

    fun getListAutocrane(): LiveData<List<DataVehicles>>
    fun getListConcreteMixer(): LiveData<List<DataVehicles>>
    fun getListConcretePump(): LiveData<List<DataVehicles>>
    fun getListBoardManipulator(): LiveData<List<DataVehicles>>
    fun getListListDump(): LiveData<List<DataVehicles>>
    fun getListListTruck(): LiveData<List<DataVehicles>>
    fun getListBackhoeLoader(): LiveData<List<DataVehicles>>

    fun uploadVehicleImage(imageUri: Uri): Task<Uri>

    fun updateVehicleImage(newImageUri: Uri): Task<Unit>

    fun createAutocrane(mkAutocrane: DataVehicles): Task<Unit>
    fun createConcreteMixer(mkConcreteMixer: DataVehicles): Task<Unit>
    fun createConcretePump(mkConcretePump: DataVehicles): Task<Unit>
    fun createBoardManipulator(mkBoardManipulator: DataVehicles): Task<Unit>
    fun createListDump(mkListDump: DataVehicles): Task<Unit>
    fun createListTruck(mkListTruck: DataVehicles): Task<Unit>
    fun createBackhoeLoader(mkBackhoeLoader: DataVehicles): Task<Unit>

    fun createEditList(mkEditList: DataVehicles): Task<Unit>
    fun getEditList(): LiveData<List<DataVehicles>>
    fun updateVehicle(currentVehicle: DataVehicles, newVehicle: DataVehicles): Task<Unit>

    fun createApplication(mkListApplication: DataApplication): Task<Unit>

    fun getListApplication(): LiveData<List<DataApplication>>
}