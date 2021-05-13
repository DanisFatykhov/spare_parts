package com.example.spare_parts.screens.listvehicle

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.spare_parts.databinding.ActivityListBinding
import com.example.spare_parts.models.DataVehicles
import com.example.spare_parts.screens.vehicle.VehicleActivity
import com.example.spare_parts.screens.addvehicle.AddVehicleAdapter
import com.example.spare_parts.screens.common.BaseActivity
import com.example.spare_parts.screens.common.Close
import com.example.spare_parts.screens.common.setupAuthGuard


class ListConcreteMixers : BaseActivity() {
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
            vehicleRecycler.layoutManager = LinearLayoutManager(this@ListConcreteMixers)
        }

        setupAuthGuard {
            mViewModel = initViewModel()
            mViewModel.initConcreteMixers()
            mViewModel.list.observe(this, Observer{it?.let {
                mAdapter.updateList(it)
            }})
        }
    }

    private fun dataItemClick(dataItem: DataVehicles) {
        val intent = Intent(this, VehicleActivity::class.java).apply {
            putExtra("region", dataItem.region)
            putExtra("username", dataItem.username)
            putExtra("city", dataItem.city)
            putExtra("auto", dataItem.auto)
            putExtra("work", dataItem.work)
            putExtra("price1", dataItem.price1)
            putExtra("price2", dataItem.price2)
            putExtra("image", dataItem.image)
            putExtra("volumeMix", dataItem.volumeMix)
        }
        startActivity(intent)
    }
}

