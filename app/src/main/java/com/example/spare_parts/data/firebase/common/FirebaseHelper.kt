package com.example.spare_parts.data.firebase.common

import android.app.Activity
import android.net.Uri
import com.example.spare_parts.models.DataVehicles
import com.example.spare_parts.models.User
import com.example.spare_parts.screens.common.showToast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask

class FirebaseHelper(private val activity: Activity) {
    private lateinit var mUser: User

    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val database: DatabaseReference = FirebaseDatabase.getInstance().reference
    val storage: StorageReference = FirebaseStorage.getInstance().reference

    fun currentVehicleReference(): DatabaseReference =
        database.child("edit-list").child(auth.currentUser!!.uid)

    fun updateVehiclePhoto(uid: String, photoUrl: String, onSuccess: () -> Unit) {
        database.child("users/$uid/photo").setValue(photoUrl).addOnCompleteListener {
            if (it.isSuccessful) {
                onSuccess()
            } else {
                activity.showToast(it.exception!!.message!!)
            }
        }
    }

    fun updateMyAuto(uid: String, update: Map<String, Any?>, onSuccess: () -> Unit) {
        database.child("users").child(uid).updateChildren(update)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    onSuccess()
                } else {
                    activity.showToast(it.exception!!.message!!)
                }
            }
    }
}

