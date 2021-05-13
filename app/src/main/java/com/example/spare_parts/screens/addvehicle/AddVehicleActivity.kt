package com.example.spare_parts.screens.addvehicle

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.Observer
import com.example.spare_parts.R
import com.example.spare_parts.databinding.ActivityAddVehicleBinding
import com.example.spare_parts.models.DataVehicles
import com.example.spare_parts.models.User
import com.example.spare_parts.screens.common.*
import com.example.spare_parts.screens.listvehicle.*

class AddVehicleActivity : BaseActivity() {

    private lateinit var mCamera: CameraHelper
    private lateinit var mUser: User
    private lateinit var mViewModel: AddVehicleViewModel
    private lateinit var rootElement: ActivityAddVehicleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootElement = ActivityAddVehicleBinding.inflate(layoutInflater)
        val view = rootElement.root
        setContentView(view)

        setupAuthGuard {
            mViewModel = initViewModel()

            with(rootElement) {
                Close(rootElement.imageBack)
                addPhoto.setOnClickListener { mCamera.takeCameraPicture() }
            }

            mCamera = CameraHelper(this)
            mViewModel.user.observe(this, Observer {
                it?.let {
                    mUser = it
                }
            })
            spinner()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == mCamera.REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                rootElement.imageVehicle.loadVehiclePhoto(mCamera.imageUri?.toString())
            } else {
                finish()
            }
        }
    }

    private fun spinner() {
        val spinner = findViewById<Spinner>(R.id.categories_spinner)
        val vehicle = arrayOf(
            "Автокраны",
            "Бетономешалки",
            "Бетононасосы",
            "Бортовые манипуляторы",
            "Самосвалы",
            "Траки",
            "Экскаватор-погрузчики"
        )
        val adapter = ArrayAdapter<String>(this, R.layout.custom_spinner, vehicle)

        adapter.setDropDownViewResource(R.layout.custom_spinner)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

            with(rootElement) {
                if (position == 0) {
                    visibility()
                    ttxConstraint0.visibility = View.VISIBLE
                    imageCheck.setOnClickListener {
                        if (mCamera.imageUri != null) {
                            cpv()
                            mkVehicleAutocrane()
                            mViewModel.mkVehicleCompletedEvent.observe(this@AddVehicleActivity, Observer {
                                startActivity(Intent(this@AddVehicleActivity, ListAutocranes::class.java))
                                overridePendingTransition(0, 0)
                                finish()
                            })
                        }else {
                            showToast(getString(R.string.please_enter_your_image))
                        }
                    }
                }

                if (position == 1) {
                    visibility()
                    ttxConstraint1.visibility = View.VISIBLE
                    imageCheck.setOnClickListener {
                        if (mCamera.imageUri != null) {
                            cpv()
                            mkConcreteMixer()
                            mViewModel.mkVehicleCompletedEvent.observe(this@AddVehicleActivity, Observer {
                                startActivity(Intent(this@AddVehicleActivity, ListConcreteMixers::class.java))
                                overridePendingTransition(0, 0)
                                finish()
                            })
                        }else {
                            showToast(getString(R.string.please_enter_your_image))
                        }
                    }
                }

                if (position == 2) {
                    visibility()
                    ttxConstraint2.visibility = View.VISIBLE
                    imageCheck.setOnClickListener {
                        if (mCamera.imageUri != null) {
                            cpv()
                            mkConcretePump()
                            mViewModel.mkVehicleCompletedEvent.observe(this@AddVehicleActivity, Observer {
                                startActivity(Intent(this@AddVehicleActivity, ListConcretePumps::class.java))
                                overridePendingTransition(0, 0)
                                finish()
                            })
                        }else {
                            showToast(getString(R.string.please_enter_your_image))
                        }
                    }
                }

                if (position == 3) {
                    visibility()
                    ttxConstraint3.visibility = View.VISIBLE
                    imageCheck.setOnClickListener {
                        if (mCamera.imageUri != null) {
                            cpv()
                            mkBoardManipulator()
                            mViewModel.mkVehicleCompletedEvent.observe(this@AddVehicleActivity, Observer {
                                startActivity(Intent(this@AddVehicleActivity, ListBoardManipulators::class.java))
                                overridePendingTransition(0, 0)
                                finish()
                            })
                        }else {
                            showToast(getString(R.string.please_enter_your_image))
                        }
                    }
                }

                if (position == 4) {
                    visibility()
                    ttxConstraint4.visibility = View.VISIBLE
                    imageCheck.setOnClickListener {
                        if (mCamera.imageUri != null) {
                            cpv()
                            mkListDump()
                            mViewModel.mkVehicleCompletedEvent.observe(this@AddVehicleActivity, Observer {
                                startActivity(Intent(this@AddVehicleActivity, ListDumps::class.java))
                                overridePendingTransition(0, 0)
                                finish()
                            })
                        }else {
                            showToast(getString(R.string.please_enter_your_image))
                        }
                    }
                }

                if (position == 5) {
                    visibility()
                    ttxConstraint5.visibility = View.VISIBLE
                    imageCheck.setOnClickListener {
                        if (mCamera.imageUri != null) {
                            cpv()
                            mkListTruck()
                            mViewModel.mkVehicleCompletedEvent.observe(this@AddVehicleActivity, Observer {
                                startActivity(Intent(this@AddVehicleActivity, ListTrucks::class.java))
                                overridePendingTransition(0, 0)
                                finish()
                            })
                        }else {
                            showToast(getString(R.string.please_enter_your_image))
                        }
                    }
                }

                if (position == 6) {
                    visibility()
                    ttxConstraint6.visibility = View.VISIBLE
                    imageCheck.setOnClickListener {
                        if (mCamera.imageUri != null) {
                            cpv()
                            mkListBackhoeLoader()
                            mViewModel.mkVehicleCompletedEvent.observe(this@AddVehicleActivity, Observer {
                                startActivity(Intent(this@AddVehicleActivity, ListBackhoeLoaders::class.java))
                                overridePendingTransition(0, 0)
                                finish()
                            })
                        } else {
                            showToast(getString(R.string.please_enter_your_image))
                        }
                    }
                }
            }

            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    private fun cpv() {
        rootElement.cpvAddVehicle.visibility = View.VISIBLE
    }

    private fun mkVehicleAutocrane() {
        with(rootElement) {
            mViewModel.mkAutocrane(
                mUser, mCamera.imageUri,
                autoInput.text.toString(),
                workInput.text.toString(),
                priceInput1.text.toStringOrNull(),
                priceInput2.text.toStringOrNull(),
                heightArrow.text.toStringOrNull(),
                massArrow.text.toStringOrNull()
            )
        }
    }

    private fun mkConcreteMixer() {
         with(rootElement) {
             mViewModel.mkConcreteMixer(
                 mUser, mCamera.imageUri,
                 autoInput.text.toString(),
                 workInput.text.toString(),
                 priceInput1.text.toStringOrNull(),
                 priceInput2.text.toStringOrNull(),
                 volumeMix.text.toStringOrNull()
             )
         }
    }

    private fun mkConcretePump() {
         with(rootElement) {
             mViewModel.mkConcretePump(
                 mUser, mCamera.imageUri,
                 autoInput.text.toString(),
                 workInput.text.toString(),
                 priceInput1.text.toStringOrNull(),
                 priceInput2.text.toStringOrNull(),
                 volumeMixArrow.text.toStringOrNull(),
                 heightMixArrow.text.toStringOrNull()
             )
         }
    }

    private fun mkBoardManipulator() {
         with(rootElement) {
             mViewModel.mkBoardManipulator(
                 mUser, mCamera.imageUri,
                 autoInput.text.toString(),
                 workInput.text.toString(),
                 priceInput1.text.toStringOrNull(),
                 priceInput2.text.toStringOrNull(),
                 widthManipulator.text.toStringOrNull(),
                 lengthManipulator.text.toStringOrNull(),
                 massManipulator.text.toStringOrNull(),
                 lengthArrowManipulator.text.toStringOrNull(),
                 massArrowManipulator.text.toStringOrNull()
             )
         }
    }

    private fun mkListDump() {
         with(rootElement) {
             mViewModel.mkListDump(
                 mUser, mCamera.imageUri,
                 autoInput.text.toString(),
                 workInput.text.toString(),
                 priceInput1.text.toStringOrNull(),
                 priceInput2.text.toStringOrNull(),
                 volumeDump.text.toStringOrNull(),
                 massDump.text.toStringOrNull()
             )
         }
    }

    private fun mkListTruck() {
         with(rootElement) {
             mViewModel.mkListTruck(
                 mUser, mCamera.imageUri,
                 autoInput.text.toString(),
                 workInput.text.toString(),
                 priceInput1.text.toStringOrNull(),
                 priceInput2.text.toStringOrNull(),
                 widthTruck.text.toStringOrNull(),
                 lengthTruck.text.toStringOrNull(),
                 massTruck.text.toStringOrNull(),
                 heightTruck.text.toStringOrNull()
             )
         }
    }

    private fun mkListBackhoeLoader() {
        with(rootElement) {
            mViewModel.mkListBackhoeLoader(
                mUser, mCamera.imageUri,
                autoInput.text.toString(),
                workInput.text.toString(),
                priceInput1.text.toStringOrNull(),
                priceInput2.text.toStringOrNull(),
                massFrontExcavator.text.toStringOrNull(),
                heightFrontExcavator.text.toStringOrNull(),
                volumeExcavator.text.toStringOrNull(),
                depthExcavator.text.toStringOrNull(),
                volumeFrontExcavator.text.toStringOrNull(),
                heightExcavator.text.toStringOrNull()
            )
        }
    }

    fun visibility() {
        with(rootElement) {
            ttxConstraint0.visibility = View.GONE
            ttxConstraint1.visibility = View.GONE
            ttxConstraint2.visibility = View.GONE
            ttxConstraint3.visibility = View.GONE
            ttxConstraint4.visibility = View.GONE
            ttxConstraint5.visibility = View.GONE
            ttxConstraint6.visibility = View.GONE
        }
    }
}