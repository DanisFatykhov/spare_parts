package com.example.spare_parts.models

import com.google.firebase.database.Exclude
import com.google.firebase.database.ServerValue
import java.util.*

data class DataVehicles(
    val uid: String = "",
    val username: String = "",
    val auto: String = "",
    val image: String? = null,
    val city: String = "",
    val region: String = "",
    val work: String = "",
    val price1: String? = null,
    val price2: String? = null,
    val timestamp: Any = ServerValue.TIMESTAMP,

    val heightArrow: String? = null,
    val massArrow: String? = null,
    val volumeMix: String? = null,
    val volumeMixArrow: String? = null,
    val heightMixArrow: String? = null,
    val widthManipulator: String? = null,
    val lengthManipulator: String? = null,
    val massManipulator: String? = null,
    val lengthArrowManipulator: String? = null,
    val massArrowManipulator: String? = null,
    val volumeDump: String? = null,
    val massDump: String? = null,
    val widthTruck: String? = null,
    val lengthTruck: String? = null,
    val massTruck: String? = null,
    val heightTruck: String? = null,
    val massFrontExcavator: String? = null,
    val heightFrontExcavator: String? = null,
    val volumeExcavator: String? = null,
    val depthExcavator: String? = null,
    val volumeFrontExcavator: String? = null,
    val heightExcavator: String? = null
) {
    fun timestampDate(): Date = Date(timestamp as Long)
}





