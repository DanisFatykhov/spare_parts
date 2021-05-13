package com.example.spare_parts.screens.addapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.spare_parts.databinding.ActivityAddAplicationBinding
import com.example.spare_parts.models.User
import com.example.spare_parts.screens.application.ListApplicationActivity
import com.example.spare_parts.screens.common.BaseActivity
import com.example.spare_parts.screens.common.Close
import com.example.spare_parts.screens.common.setupAuthGuard

class AddApplicationActivity : BaseActivity() {

    private lateinit var rootElement: ActivityAddAplicationBinding
    private lateinit var mViewModel: AddApplicationViewModel
    private lateinit var mUser: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootElement = ActivityAddAplicationBinding.inflate(layoutInflater)
        val view = rootElement.root
        setContentView(view)

        Close(rootElement.imageBack)

        setupAuthGuard {
            mViewModel = initViewModel()

            mViewModel.user.observe(this, Observer {
                it?.let {
                    mUser = it
                    with(rootElement) {
                        enterName.setText(mUser.username)
                        enterPhone.setText(mUser.phone)
                    }
                }
            })

            rootElement.imageCheck.setOnClickListener {
                cpv()
                mkApplication()
                mViewModel.mkApplicationCompletedEvent.observe(this@AddApplicationActivity, Observer {
                    startActivity(Intent(this@AddApplicationActivity, ListApplicationActivity::class.java))
                    overridePendingTransition(0, 0)
                    finish()
                })
            }
        }
    }

    private fun mkApplication() {
        with(rootElement) {
            mViewModel.mkApplication(
                mUser,
                enterTitleApp.text.toString(),
                enterDescriptionApp.text.toString(),
                enterAddress.text.toString(),
                enterName.text.toString(),
                enterPhone.text.toString()
            )
        }
    }

    private fun cpv() {
        rootElement.cpvAddApplication.visibility = View.VISIBLE
    }
}