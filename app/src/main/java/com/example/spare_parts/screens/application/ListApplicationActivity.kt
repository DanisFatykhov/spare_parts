package com.example.spare_parts.screens.application

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spare_parts.databinding.ActivityListApplicationBinding
import com.example.spare_parts.models.DataApplication
import com.example.spare_parts.screens.addapplication.AddApplicationActivity
import com.example.spare_parts.screens.addapplication.AddApplicationAdapter
import com.example.spare_parts.screens.common.BaseActivity
import com.example.spare_parts.screens.common.Close
import com.example.spare_parts.screens.common.setupAuthGuard

class ListApplicationActivity : BaseActivity() {

    private lateinit var mAdapter: AddApplicationAdapter
    private lateinit var mViewModelList: ListApplicationViewModel
    private lateinit var rootElement: ActivityListApplicationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootElement = ActivityListApplicationBinding.inflate(layoutInflater)
        setContentView(rootElement.root)

        Close(rootElement.imageBack)

        mAdapter = AddApplicationAdapter { dataItem: DataApplication ->
            dataItemClick(dataItem)
        }
        rootElement.applicationRecycler.adapter = mAdapter
        rootElement.applicationRecycler.layoutManager = LinearLayoutManager(this@ListApplicationActivity)

        setupAuthGuard {
            mViewModelList = initViewModel()
            mViewModelList.initApplication()
            mViewModelList.list.observe(this, Observer { it?.let {
                mAdapter.updateList(it)
            } })

            rootElement.imageAdd.setOnClickListener {
                startActivity(Intent(this, AddApplicationActivity::class.java))
                overridePendingTransition(0, 0)
                finish()
            }
        }
    }

    private fun dataItemClick(dataItem: DataApplication) {
        val intent = Intent(this, ApplicationActivity::class.java)
        intent.putExtra("title", dataItem.title_app)
        intent.putExtra("description", dataItem.description)
        intent.putExtra("address", dataItem.address)
        intent.putExtra("name", dataItem.name)
        intent.putExtra("phone", dataItem.phone)
        startActivity(intent)
    }
}
