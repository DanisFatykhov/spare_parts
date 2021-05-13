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

class ListTrucks : BaseActivity() {
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
            vehicleRecycler.layoutManager = LinearLayoutManager(this@ListTrucks)
        }

        setupAuthGuard {
            mViewModel = initViewModel()
            mViewModel.initTrawls()
            mViewModel.list.observe(this, Observer{it?.let {
                mAdapter.updateList(it)
            }})
        }
    }

    private fun dataItemClick(dataItem: DataVehicles) {
        val intent = Intent(this, VehicleActivity::class.java)
        intent.putExtra("username", dataItem.username)
        intent.putExtra("city", dataItem.city)
        intent.putExtra("region", dataItem.region)
        intent.putExtra("auto", dataItem.auto)
        intent.putExtra("work", dataItem.work)
        intent.putExtra("price1", dataItem.price1)
        intent.putExtra("price2", dataItem.price2)
        intent.putExtra("image", dataItem.image)
        intent.putExtra("widthTruck", dataItem.widthTruck)
        intent.putExtra("lengthTruck", dataItem.lengthTruck)
        intent.putExtra("massTruck", dataItem.massTruck)
        intent.putExtra("heightTruck", dataItem.heightTruck)
        startActivity(intent)
    }
}

