package com.example.spare_parts.screens.editvehicle

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spare_parts.databinding.ActivityEditListBinding
import com.example.spare_parts.screens.addvehicle.AddVehicleAdapter
import com.example.spare_parts.screens.common.BaseActivity
import com.example.spare_parts.screens.common.Close
import com.example.spare_parts.screens.common.setupAuthGuard


class EditListActivity: BaseActivity() {

    private lateinit var mViewModel: EditListViewModel
    private lateinit var mAdapter: AddVehicleAdapter
    private lateinit var rootElement: ActivityEditListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootElement = ActivityEditListBinding.inflate(layoutInflater)
        val view = rootElement.root
        setContentView(view)

        Close(rootElement.imageBack)

        mAdapter = AddVehicleAdapter {
            dataItemClick()
        }
        with(rootElement) {
            editVehicleRecycler.adapter = mAdapter
            editVehicleRecycler.layoutManager = LinearLayoutManager(this@EditListActivity)
        }

        setupAuthGuard {
            mViewModel = initViewModel()
            mViewModel.initEditList()
            mViewModel.list.observe(this, Observer{it?.let {
                mAdapter.updateList(it)
            }})
        }
    }

    private fun dataItemClick() {
        val intent = Intent(this, EditVehicleActivity::class.java)
        startActivity(intent)
    }
}
