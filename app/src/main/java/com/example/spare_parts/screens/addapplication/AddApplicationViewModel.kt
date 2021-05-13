package com.example.spare_parts.screens.addapplication

import com.example.spare_parts.common.SingleLiveEvent
import com.example.spare_parts.data.VehicleRepository
import com.example.spare_parts.models.DataApplication
import com.example.spare_parts.models.User
import com.example.spare_parts.screens.common.BaseViewModel
import com.google.android.gms.tasks.OnFailureListener

class AddApplicationViewModel(private val vehicleRepository: VehicleRepository,
                              onFailureListener: OnFailureListener): BaseViewModel(onFailureListener) {
    private val _mkApplicationCompletedEvent = SingleLiveEvent<Unit>()
    val mkApplicationCompletedEvent = _mkApplicationCompletedEvent
    val user = vehicleRepository.getUser()

    fun mkApplication(user: User, title: String, description: String, address: String, name: String, phone: String) {
        vehicleRepository.createApplication(mkListApplication(user, title, description, address, name, phone)).addOnCompleteListener {
            _mkApplicationCompletedEvent.call()
        }.addOnFailureListener(onFailureListener)
    }

    private fun mkListApplication(user: User, title: String, description: String, address: String, name: String, phone: String): DataApplication {
        return DataApplication(
            uid = user.uid,
            title_app = title,
            description = description,
            address = address,
            name = name,
            phone = phone
        )
    }

}
