package com.example.spare_parts.screens.addvehicle

import android.net.Uri
import com.example.spare_parts.common.SingleLiveEvent
import com.example.spare_parts.data.VehicleRepository
import com.example.spare_parts.models.*
import com.example.spare_parts.screens.common.BaseViewModel
import com.google.android.gms.tasks.OnFailureListener

class AddVehicleViewModel(private val vehicleRepository: VehicleRepository,
                          onFailureListener: OnFailureListener): BaseViewModel(onFailureListener) {
    private val _mkVehicleCompletedEvent = SingleLiveEvent<Unit>()
    val mkVehicleCompletedEvent = _mkVehicleCompletedEvent
    val user = vehicleRepository.getUser()

    fun mkAutocrane(user: User, imageUri: Uri?, auto: String, work: String, price1: String?, price2: String?, massArrow: String?,
                    heightArrow: String?) {
        if (imageUri != null) {
            vehicleRepository.uploadVehicleImage(imageUri).addOnCompleteListener {
                vehicleRepository.createAutocrane(mkListAutocrane(user, auto, work, massArrow, heightArrow,
                    price1, price2, imageUri.toString()))
                vehicleRepository.createEditList(mkEditList(user, auto, work, price1, price2, imageUri.toString()))
            }.addOnCompleteListener {
                _mkVehicleCompletedEvent.call()
            }.addOnFailureListener(onFailureListener)
        }
    }

    fun mkConcreteMixer(user: User, imageUri: Uri?, auto: String, work: String, price1: String?, price2: String?, volumeMix: String?) {
        if (imageUri != null) {
            vehicleRepository.uploadVehicleImage(imageUri).addOnCompleteListener {
                vehicleRepository.createConcreteMixer(mkListConcreteMixer(user, auto, work, price1, price2, volumeMix,
                    imageUri.toString()))
                vehicleRepository.createEditList(mkEditList(user, auto, work, price1, price2, imageUri.toString()))
            }.addOnCompleteListener {
                _mkVehicleCompletedEvent.call()
            }.addOnFailureListener(onFailureListener)
        }
    }

    fun mkConcretePump(user: User, imageUri: Uri?, auto: String, work: String, price1: String?, price2: String?, volumeMixArrow: String?,
                       heightMixArrow: String?) {
        if (imageUri != null) {
            vehicleRepository.uploadVehicleImage(imageUri).addOnCompleteListener {
                vehicleRepository.createConcretePump(mkListConcretePump(user, auto, work, price1, price2, volumeMixArrow,
                    heightMixArrow, imageUri.toString()))
                vehicleRepository.createEditList(mkEditList(user, auto, work, price1, price2, imageUri.toString()))
            }.addOnCompleteListener {
                _mkVehicleCompletedEvent.call()
            }.addOnFailureListener(onFailureListener)
        }
    }

    fun mkBoardManipulator(user: User, imageUri: Uri?, auto: String, work: String, price1: String?, price2: String?,
                           widthManipulator: String?, lengthManipulator: String?, massManipulator: String?,
                           lengthArrowManipulator: String?, massArrowManipulator: String?) {
        if (imageUri != null) {
            vehicleRepository.uploadVehicleImage(imageUri).addOnCompleteListener {
                vehicleRepository.createBoardManipulator(mkListBoardManipulators(user, auto, work, price1, price2, widthManipulator,
                    lengthManipulator, massManipulator, lengthArrowManipulator, massArrowManipulator,
                    imageUri.toString()))
                vehicleRepository.createEditList(mkEditList(user, auto, work, price1, price2, imageUri.toString()))
            }.addOnCompleteListener {
                _mkVehicleCompletedEvent.call()
            }.addOnFailureListener(onFailureListener)
        }
    }

    fun mkListDump(user: User, imageUri: Uri?, auto: String, work: String, price1: String?, price2: String?, volumeDump: String?,
                   massDump: String?) {
        if (imageUri != null) {
            vehicleRepository.uploadVehicleImage(imageUri).addOnCompleteListener {
                vehicleRepository.createListDump(mkListDump(user, auto, work, price1, price2, volumeDump, massDump,
                        imageUri.toString()))
                vehicleRepository.createEditList(mkEditList(user, auto, work, price1, price2, imageUri.toString()))
            }.addOnCompleteListener {
                _mkVehicleCompletedEvent.call()
            }.addOnFailureListener(onFailureListener)
        }
    }

    fun mkListTruck(user: User, imageUri: Uri?, auto: String, work: String, price1: String?, price2: String?, widthTruck: String?,
                    lengthTruck: String?, massTruck: String?, heightTruck: String?) {
        if (imageUri != null) {
            vehicleRepository.uploadVehicleImage(imageUri).addOnCompleteListener {
                vehicleRepository.createListTruck(mkListTruck(user, auto, work, price1, price2, widthTruck,
                        lengthTruck, massTruck, heightTruck, imageUri.toString()))
                vehicleRepository.createEditList(mkEditList(user, auto, work, price1, price2, imageUri.toString()))
            }.addOnCompleteListener {
                _mkVehicleCompletedEvent.call()
            }.addOnFailureListener(onFailureListener)
        }
    }

    fun mkListBackhoeLoader(user: User, imageUri: Uri?, auto: String, work: String, price1: String?, price2: String?,
                            massExcavator: String?, heightFrontExcavator: String?, volumeExcavator: String?,
                            depthExcavator: String?, volumeFrontExcavator: String?, heightExcavator: String?) {
        if (imageUri != null) {
            vehicleRepository.uploadVehicleImage(imageUri).addOnCompleteListener {
                vehicleRepository.createBackhoeLoader(mkListBackhoeLoader(user, auto, work, price1, price2, massExcavator,
                        heightFrontExcavator, volumeExcavator, depthExcavator, volumeFrontExcavator, heightExcavator,
                        imageUri.toString()))
                vehicleRepository.createEditList(mkEditList(user, auto, work, price1, price2, imageUri.toString()))
            }.addOnCompleteListener {
                _mkVehicleCompletedEvent.call()
            }.addOnFailureListener(onFailureListener)
        }
    }

    private fun mkListAutocrane(user: User, auto: String, work: String, heightArrow: String?, massArrow: String?,
                                price1: String?, price2: String?, imageUri: String): DataVehicles {
        return DataVehicles(
            uid = user.uid,
            image = imageUri,
            auto = auto,
            work = work,
            price1 = price1,
            price2 = price2,
            heightArrow = heightArrow,
            massArrow = massArrow,
            username = user.username,
            city = user.city,
            region = user.region
        )
    }

    private fun mkListConcreteMixer(user: User, auto: String, work: String, price1: String?, price2: String?, volumeMix: String?,
                                    imageUri: String): DataVehicles {
        return DataVehicles(
            uid = user.uid,
            image = imageUri,
            auto = auto,
            work = work,
            price1 = price1,
            price2 = price2,
            volumeMix = volumeMix,
            username = user.username,
            city = user.city,
            region = user.region
        )
    }

    private fun mkListConcretePump(user: User, auto: String, work: String, price1: String?, price2: String?, volumeMixArrow: String?,
                                   heightMixArrow: String?, imageUri: String): DataVehicles {
        return DataVehicles(
            uid = user.uid,
            image = imageUri,
            auto = auto,
            work = work,
            price1 = price1,
            price2 = price2,
            volumeMixArrow = volumeMixArrow,
            heightMixArrow = heightMixArrow,
            username = user.username,
            city = user.city,
            region = user.region
        )
    }

    private fun mkListBoardManipulators(user: User, auto: String, work: String, price1: String?, price2: String?, widthManipulator: String?,
                                        lengthManipulator: String?, massManipulator: String?, lengthArrowManipulator: String?,
                                        massArrowManipulator: String?, imageUri: String): DataVehicles {
        return DataVehicles(
            uid = user.uid,
            image = imageUri,
            auto = auto,
            work = work,
            price1 = price1,
            price2 = price2,
            widthManipulator = widthManipulator,
            lengthManipulator = lengthManipulator,
            massManipulator = massManipulator,
            lengthArrowManipulator = lengthArrowManipulator,
            massArrowManipulator = massArrowManipulator,
            username = user.username,
            city = user.city,
            region = user.region
        )
    }

    private fun mkListDump(user: User, auto: String, work: String, price1: String?, price2: String?, volumeDump: String?,
                           massDump: String?, imageUri: String): DataVehicles {
        return DataVehicles(
            uid = user.uid,
            image = imageUri,
            auto = auto,
            work = work,
            price1 = price1,
            price2 = price2,
            volumeDump = volumeDump,
            massDump = massDump,
            username = user.username,
            city = user.city,
            region = user.region
        )
    }

    private fun mkListTruck(user: User, auto: String, work: String, price1: String?, price2: String?, widthTruck: String?,
                            lengthTruck: String?, massTruck: String?, heightTruck: String?,
                            imageUri: String): DataVehicles {
        return DataVehicles(
            uid = user.uid,
            image = imageUri,
            auto = auto,
            work = work,
            price1 = price1,
            price2 = price2,
            widthTruck = widthTruck,
            lengthTruck = lengthTruck,
            massTruck = massTruck,
            heightTruck = heightTruck,
            username = user.username,
            city = user.city,
            region = user.region
        )
    }

    private fun mkListBackhoeLoader(user: User, auto: String, work: String, price1: String?, price2: String?, massFrontExcavator: String?,
                                    heightFrontExcavator: String?, volumeExcavator: String?, depthExcavator: String?,
                                    volumeFrontExcavator: String?, heightExcavator: String?,
                                    imageUri: String): DataVehicles {
        return DataVehicles(
            uid = user.uid,
            image = imageUri,
            auto = auto,
            work = work,
            price1 = price1,
            price2 = price2,
            massFrontExcavator = massFrontExcavator,
            heightFrontExcavator = heightFrontExcavator,
            volumeExcavator = volumeExcavator,
            depthExcavator = depthExcavator,
            volumeFrontExcavator = volumeFrontExcavator,
            heightExcavator = heightExcavator,
            username = user.username,
            city = user.city,
            region = user.region
        )
    }

    private fun mkEditList(user: User, auto: String, work: String, price1: String?, price2: String?,
                           imageUri: String): DataVehicles {
        return DataVehicles(
            uid = user.uid,
            image = imageUri,
            auto = auto,
            work = work,
            price1 = price1,
            price2 = price2,
            username = user.username,
            city = user.city,
            region = user.region
        )
    }
}

