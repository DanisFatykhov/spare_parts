package com.example.spare_parts.screens.application

import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.spare_parts.databinding.ActivityApplicationBinding
import com.example.spare_parts.screens.common.BaseActivity
import com.example.spare_parts.screens.common.Close
import com.example.spare_parts.screens.common.setupAuthGuard

class ApplicationActivity : BaseActivity() {
    private lateinit var mViewModel: ApplicationViewModel
    private lateinit var rootElement: ActivityApplicationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootElement = ActivityApplicationBinding.inflate(layoutInflater)
        val view = rootElement.root
        setContentView(view)

        Close(rootElement.imageBack)

        setupAuthGuard {
            mViewModel = initViewModel()
            mViewModel.user.observe(this, Observer {
                it?.let {
                }
            })

            with(rootElement) {
                val title = intent.getStringExtra("title")
                titleApplication.text = title?.capitalize()

                val description = intent.getStringExtra("description")
                appDescription.text = description?.capitalize()

                val address = intent.getStringExtra("address")
                yourAddress.text = address?.capitalize()

                val name = intent.getStringExtra("name")
                yourName.text = name?.capitalize()

                val phone = intent.getStringExtra("phone")

            }
        }


    }
}