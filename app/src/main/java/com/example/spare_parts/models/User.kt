package com.example.spare_parts.models

import com.google.firebase.database.Exclude

data class User(
    @Exclude val uid: String = "",
    val username: String = "",
    val email: String = "",
    val phone: String = "",
    val region: String = "",
    val city: String = ""
)

