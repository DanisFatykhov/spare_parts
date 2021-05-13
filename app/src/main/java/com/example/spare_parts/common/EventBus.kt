package com.example.spare_parts.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.spare_parts.models.DataVehicles

object EventBus {
    private val liveDataBus = MutableLiveData<Event>()

    val events: LiveData<Event> = liveDataBus

    fun publish(event: Event) {
        liveDataBus.value = event
    }
}

sealed class Event {
    data class OpenVehicle(val postId: String, val uid: String) : Event()
}