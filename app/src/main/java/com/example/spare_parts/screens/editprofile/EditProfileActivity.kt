package com.example.spare_parts.screens.editprofile

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.spare_parts.databinding.ActivityEditProfileBinding
import com.example.spare_parts.models.User
import com.example.spare_parts.screens.common.BaseActivity
import com.example.spare_parts.screens.common.Close
import com.example.spare_parts.screens.common.PasswordDialog
import com.example.spare_parts.screens.common.showToast
import com.example.spare_parts.screens.main.MainActivity


class EditProfileActivity : BaseActivity(), PasswordDialog.Listener {

    private lateinit var mUser: User
    private lateinit var mPendingUser: User
    private lateinit var mViewModel: EditProfileViewModel
    private lateinit var rootElement: ActivityEditProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootElement = ActivityEditProfileBinding.inflate(layoutInflater)
        val view = rootElement.root
        setContentView(view)

        Close(rootElement.imageBack)

        rootElement.imageCheck.setOnClickListener { updateProfile() }

        mViewModel = initViewModel()

        mViewModel.user.observe(this, Observer { it ->
            it?.let {
                mUser = it
                with(rootElement) {
                    usernameInput.setText(mUser.username)
                    phoneInput.setText(mUser.phone)
                    emailInput.setText(mUser.email)
                    regionInput.setText(mUser.region)
                    cityInput.setText(mUser.city)
                }
            }
        })
    }

    private fun updateProfile() {
        mPendingUser = readUserInputs()
        val error = validate(mPendingUser)
        if (error == null) {
            if (mPendingUser.email == mUser.email) {
                updateUser(mPendingUser)
            } else {
                PasswordDialog().show(supportFragmentManager, "password_dialog")
            }
        } else {
            showToast(error)
        }
    }

    private fun readUserInputs(): User {
        return User(
            username = rootElement.usernameInput.text.toString(),
            email = rootElement.emailInput.text.toString(),
            phone = rootElement.phoneInput.text.toString(),
            region = rootElement.regionInput.text.toString(),
            city = rootElement.cityInput.text.toString()
        )
    }

    override fun onPasswordConfirm(password: String) {
        if (password.isNotEmpty()) {
            mViewModel.updateEmail(
                currentEmail = mUser.email,
                newEmail = mPendingUser.email,
                password = password
            )
                .addOnSuccessListener { updateUser(mPendingUser) }
        } else {
            showToast("Вы должны ввести свой пароль")
        }
    }

    private fun updateUser(user: User) {
        mViewModel.updateUserProfile(currentUser = mUser, newUser = user)
            .addOnSuccessListener {
                showToast("Профиль сохранен")
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
                startActivity(intent)
                overridePendingTransition(0, 0)
            }
    }

    private fun validate(user: User): String? =
        when {
            user.username.isEmpty() -> "Пожалуйста, введите имя"
            user.email.isEmpty() -> "Пожалуйста, введите email"
            else -> null
        }
}
