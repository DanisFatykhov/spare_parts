package com.example.spare_parts.screens.editvehicle

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.spare_parts.databinding.ActivityEditVehicleBinding
import com.example.spare_parts.models.DataVehicles
import com.example.spare_parts.screens.common.*


class EditVehicleActivity : BaseActivity() {

    private lateinit var mVehicle: DataVehicles
    private lateinit var mPendingVehicle: DataVehicles
    private lateinit var mViewModel: EditVehicleViewModel
    private lateinit var mCamera: CameraHelper
    private lateinit var rootElement: ActivityEditVehicleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootElement = ActivityEditVehicleBinding.inflate(layoutInflater)
        val view = rootElement.root
        setContentView(view)


        mCamera = CameraHelper(this)

        with(rootElement) {
            Close(imageBack)
            addPhoto.setOnClickListener { mCamera.takeCameraPicture() }
            imageCheck.setOnClickListener { updateVehicle() }
        }

        setupAuthGuard {
            mViewModel = initViewModel()

            mViewModel.vehicle().observe(this, Observer {
                it?.let {
                    mVehicle = it
                    with(rootElement) {
                        autoInput.setText(mVehicle.auto)
                        workInput.setText(mVehicle.work)

                        heightArrow.setText(mVehicle.heightArrow)
                        massArrow.setText(mVehicle.massArrow)
                        volumeMix.setText(mVehicle.volumeMix)
                        volumeMixArrow.setText(mVehicle.volumeMixArrow)
                        heightMixArrow.setText(mVehicle.heightMixArrow)
                        widthManipulator.setText(mVehicle.widthManipulator)
                        lengthManipulator.setText(mVehicle.lengthManipulator)
                        massManipulator.setText(mVehicle.massManipulator)
                        lengthManipulator.setText(mVehicle.lengthArrowManipulator)
                        massArrowManipulator.setText(mVehicle.massArrowManipulator)
                        volumeDump.setText(mVehicle.volumeDump)
                        massDump.setText(mVehicle.massDump)
                        widthTruck.setText(mVehicle.widthTruck)
                        lengthTruck.setText(mVehicle.lengthTruck)
                        massTruck.setText(mVehicle.massTruck)
                        heightTruck.setText(mVehicle.heightTruck)
                        massFrontExcavator.setText(mVehicle.massFrontExcavator)
                        heightFrontExcavator.setText(mVehicle.heightFrontExcavator)
                        volumeExcavator.setText(mVehicle.volumeExcavator)
                        depthExcavator.setText(mVehicle.depthExcavator)
                        volumeFrontExcavator.setText(mVehicle.volumeFrontExcavator)
                        heightExcavator.setText(mVehicle.heightExcavator)

                        imageVehicle.loadVehiclePhoto(mVehicle.image)
                    }
                }
            })


        }

//        val spinner = findViewById<Spinner>(R.id.categories_spinner)
//        val vehicle = arrayOf(
//            "Автокраны",
//            "Бетономешалки",
//            "Бетононасосы",
//            "Бортовые манипуляторы",
//            "Самосвалы",
//            "Тралы",
//            "Экскаватор-погрузчики"
//        )
//        val adapter = ArrayAdapter<String>(this, R.layout.custom_spinner, vehicle)
//
//        adapter.setDropDownViewResource(R.layout.custom_spinner)
//        spinner.adapter = adapter
//
//        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
//                if (position == 0) ttx_constraint_0.visibility = View.VISIBLE else ttx_constraint_0.visibility = View.GONE
//                if (position == 1) ttx_constraint_1.visibility = View.VISIBLE else ttx_constraint_1.visibility = View.GONE
//                if (position == 2) ttx_constraint_2.visibility = View.VISIBLE else ttx_constraint_2.visibility = View.GONE
//                if (position == 3) ttx_constraint_3.visibility = View.VISIBLE else ttx_constraint_3.visibility = View.GONE
//                if (position == 4) ttx_constraint_4.visibility = View.VISIBLE else ttx_constraint_4.visibility = View.GONE
//                if (position == 5) ttx_constraint_5.visibility = View.VISIBLE else ttx_constraint_5.visibility = View.GONE
//                if (position == 6) ttx_constraint_6.visibility = View.VISIBLE else ttx_constraint_6.visibility = View.GONE
//
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>) {
//            }
//        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == mCamera.REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            mViewModel.uploadAndSetVehiclePhoto(mCamera.imageUri!!)
        }
    }

    private fun updateVehicle() {
        mPendingVehicle = readVehicleInputs()
        val error = enter(mPendingVehicle)
        if (error == null) {
            updateAuto(mPendingVehicle)
        } else {
            showToast(error)
        }
    }

    private fun readVehicleInputs(): DataVehicles {
        return DataVehicles(
            auto = rootElement.autoInput.text.toString(),
            work = rootElement.workInput.text.toString(),

            heightArrow = rootElement.heightArrow.text.toStringOrNull(),
            massArrow = rootElement.massArrow.text.toStringOrNull(),
            volumeMix = rootElement.volumeMix.text.toStringOrNull(),
            volumeMixArrow = rootElement.volumeMixArrow.text.toStringOrNull(),
            heightMixArrow = rootElement.heightMixArrow.text.toStringOrNull(),
            widthManipulator = rootElement.widthManipulator.text.toStringOrNull(),
            lengthManipulator = rootElement.lengthManipulator.text.toStringOrNull(),
            massManipulator = rootElement.massManipulator.text.toStringOrNull(),
            lengthArrowManipulator = rootElement.lengthArrowManipulator.text.toStringOrNull(),
            volumeDump = rootElement.volumeDump.text.toStringOrNull(),
            massDump = rootElement.massDump.text.toStringOrNull(),
            widthTruck = rootElement.widthTruck.text.toStringOrNull(),
            lengthTruck = rootElement.lengthTruck.text.toStringOrNull(),
            massTruck = rootElement.massTruck.text.toStringOrNull(),
            heightTruck = rootElement.heightTruck.text.toStringOrNull(),
            massFrontExcavator = rootElement.massFrontExcavator.text.toStringOrNull(),
            heightFrontExcavator = rootElement.heightFrontExcavator.text.toStringOrNull(),
            volumeExcavator = rootElement.volumeExcavator.text.toStringOrNull(),
            depthExcavator = rootElement.depthExcavator.text.toStringOrNull(),
            volumeFrontExcavator = rootElement.volumeFrontExcavator.text.toStringOrNull(),
            heightExcavator = rootElement.heightExcavator.text.toStringOrNull()
        )
    }

    private fun enter(vehicle: DataVehicles): String? =
        when {
            vehicle.auto.isEmpty() -> "Пожалуйста, введите название техники"
            vehicle.work.isEmpty() -> "Пожалуйста, укажите выполняемые работы"
            else -> null
        }

    private fun updateAuto(vehicle: DataVehicles) {
        mViewModel.updateVehicle(currentVehicle = mVehicle, newVehicle = vehicle)
            .addOnSuccessListener {
                showToast("Техника сохранена")
                finish()
            }
    }
}


