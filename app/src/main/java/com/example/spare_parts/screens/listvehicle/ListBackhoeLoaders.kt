package com.example.spare_parts.screens.listvehicle

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spare_parts.databinding.ActivityListBinding
import com.example.spare_parts.models.DataVehicles
import com.example.spare_parts.screens.vehicle.VehicleActivity
import com.example.spare_parts.screens.addvehicle.AddVehicleAdapter
import com.example.spare_parts.screens.common.BaseActivity
import com.example.spare_parts.screens.common.Close
import com.example.spare_parts.screens.common.setupAuthGuard


class ListBackhoeLoaders : BaseActivity() {
    private lateinit var mAdapter: AddVehicleAdapter
    private lateinit var mViewModel: ListViewModel
    private lateinit var rootElement: ActivityListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootElement = ActivityListBinding.inflate(layoutInflater)
        val view = rootElement.root
        setContentView(view)

        Close(rootElement.imageBack)

        mAdapter = AddVehicleAdapter { dataItem: DataVehicles ->
            dataItemClick(dataItem)
        }
        with(rootElement) {
            vehicleRecycler.adapter = mAdapter
            vehicleRecycler.layoutManager = LinearLayoutManager(this@ListBackhoeLoaders)

        }

        setupAuthGuard {
            mViewModel = initViewModel()
            mViewModel.initBackhoeLoaders()
            mViewModel.list.observe(this, Observer{it?.let {
                mAdapter.updateList(it)
            }})
        }
    }

    private fun dataItemClick(dataItem: DataVehicles) {
        val intent = Intent(this, VehicleActivity::class.java).apply {
            putExtra("username", dataItem.username)
            putExtra("city", dataItem.city)
            putExtra("region", dataItem.region)
            putExtra("auto", dataItem.auto)
            putExtra("work", dataItem.work)
            putExtra("price1", dataItem.price1)
            putExtra("price2", dataItem.price2)
            putExtra("image", dataItem.image)
            putExtra("massFrontExcavator", dataItem.massFrontExcavator)
            putExtra("heightFrontExcavator", dataItem.heightFrontExcavator)
            putExtra("volumeExcavator", dataItem.volumeExcavator)
            putExtra("depthExcavator", dataItem.depthExcavator)
            putExtra("volumeFrontExcavator", dataItem.volumeFrontExcavator)
            putExtra("heightExcavator", dataItem.heightExcavator)
        }
        startActivity(intent)
    }
}

