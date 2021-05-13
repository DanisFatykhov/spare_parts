package com.example.spare_parts.data.firebase

import androidx.lifecycle.LiveData
import com.example.spare_parts.common.toUnit
import com.example.spare_parts.data.UsersRepository
import com.example.spare_parts.data.common.map
import com.example.spare_parts.data.firebase.common.*
import com.example.spare_parts.models.DataVehicles
import com.example.spare_parts.models.User
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.EmailAuthProvider

class FirebaseUsersRepository : UsersRepository {

    /*override fun createUser(user: User, password: String): Task<Unit> =
        auth.createUserWithEmailAndPassword(user.email, password).onSuccessTask {
            database.child("users").child(it!!.user!!.uid).setValue(user)
        }.toUnit()*/

    override fun isUserExistsForEmail(email: String): Task<Boolean> =
        auth.fetchSignInMethodsForEmail(email).onSuccessTask {
            val signInMethods = it?.signInMethods ?: emptyList<String>()
            Tasks.forResult(signInMethods.isNotEmpty())
        }

    override fun getUser(): LiveData<User> =
        database.child("users").child(currentUid()!!).liveData().map {
            it.asUser()!!
        }

    override fun updateEmail(currentEmail: String, newEmail: String, password: String): Task<Unit> {
        val currentUser = auth.currentUser
        return if (currentUser != null) {
            val credential = EmailAuthProvider.getCredential(currentEmail, password)
            currentUser.reauthenticate(credential).onSuccessTask {
                currentUser.updateEmail(newEmail)
            }.toUnit()
        } else {
            Tasks.forException(IllegalStateException("Пользователь не аутентифицировался"))
        }
    }

    override fun updateUserProfile(currentUser: User, newUser: User): Task<Unit> {
        val updatesMap = mutableMapOf<String, Any?>()
        if (newUser.username != currentUser.username) updatesMap["username"] = newUser.username
        if (newUser.phone != currentUser.phone) updatesMap["phone"] = newUser.phone
        //if (newUser.email != currentUser.email) updatesMap["email"] = newUser.email
        if (newUser.region != currentUser.region) updatesMap["region"] = newUser.region
        if (newUser.city != currentUser.city) updatesMap["city"] = newUser.city

        return database.child("users").child(currentUid()!!).updateChildren(updatesMap).toUnit()
    }
}
