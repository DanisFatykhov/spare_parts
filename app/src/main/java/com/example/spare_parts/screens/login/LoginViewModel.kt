package com.example.spare_parts.screens.login

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.spare_parts.R
import com.example.spare_parts.common.AuthManager
import com.example.spare_parts.common.SingleLiveEvent
import com.example.spare_parts.screens.common.BaseViewModel
import com.example.spare_parts.screens.common.CommonViewModel
import com.example.spare_parts.screens.common.showToast
import com.google.android.gms.tasks.OnFailureListener

class LoginViewModel(private val authManager: AuthManager,
                     private val app: Application,
                     private val commonViewModel: CommonViewModel,
                     onFailureListener: OnFailureListener
): BaseViewModel(onFailureListener) {
    private val _goToMainScreen = MutableLiveData<Unit>()
    val goToMainScreen: LiveData<Unit> = _goToMainScreen

    private val _goToRegisterScreen = SingleLiveEvent<Unit>()
    val goToRegisterScreen: LiveData<Unit> = _goToRegisterScreen

    fun onLoginClick(email: String, password: String) {
        if (validate(email, password)) {
            authManager.signIn(email, password).addOnCompleteListener {
                _goToMainScreen.value = Unit
            }.addOnFailureListener(onFailureListener)
        } else {
            commonViewModel.setErrorMessage(app.getString(R.string.please_enter_email_and_password))
        }
    }

    fun onRegisterClick() {
        _goToRegisterScreen.call()
    }

    private fun validate(email: String, password: String) =
        email.isNotEmpty() && password.isNotEmpty()

    fun onSendBtnClick(email: String) {
        if (email.isNotEmpty()) {
            authManager.sendPasswordResetEmail(email).addOnSuccessListener {
                app.showToast(app.getString(R.string.email_reset_password_was_send))
                _goToMainScreen.value = Unit
            }.addOnFailureListener(onFailureListener)
        } else {
            app.showToast(app.getString(R.string.please_enter_your_email))
            commonViewModel.setErrorMessage(app.getString(R.string.please_enter_your_email))
        }
    }
}