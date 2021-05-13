package com.example.spare_parts.data.firebase.common

import androidx.lifecycle.LiveData
import com.example.spare_parts.models.DataVehicles
import com.example.spare_parts.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

fun DataSnapshot.asUser(): User? =
    getValue(User::class.java)?.copy(uid = key!!)

fun DataSnapshot.asVehicles(): DataVehicles? =
    getValue(DataVehicles::class.java)?.copy(uid = key!!)

val auth: FirebaseAuth = FirebaseAuth.getInstance()

val database: DatabaseReference = FirebaseDatabase.getInstance().reference

val storage: StorageReference = FirebaseStorage.getInstance().reference

fun currentUid(): String? = auth.currentUser?.uid
fun DatabaseReference.liveData() : LiveData<DataSnapshot> =
    FirebaseLiveData(this)