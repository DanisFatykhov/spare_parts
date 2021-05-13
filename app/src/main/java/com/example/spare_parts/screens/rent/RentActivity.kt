package com.example.spare_parts.screens.rent

import android.content.res.TypedArray
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spare_parts.R
import com.example.spare_parts.databinding.ActivityRentBinding
import com.example.spare_parts.models.DataRent


class RentActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mAdapter: RentAdapter
    private lateinit var rootElement: ActivityRentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootElement = ActivityRentBinding.inflate(layoutInflater)
        val view = rootElement.root
        setContentView(view)

        rootElement.imageBack.setOnClickListener(this)

        val list = ArrayList<DataRent>()

        list.addAll(fillArrays(resources.getStringArray(R.array.title_array), resources.getStringArray(R.array.description_array), getImageId(R.array.image_array)))

        mAdapter = RentAdapter(list, this)
        rootElement.rentRecycler.adapter = mAdapter
        rootElement.rentRecycler.layoutManager = LinearLayoutManager(this)
    }

    fun fillArrays(titleArray: Array<String>, descriptionArray: Array<String>, imageArray: IntArray): List<DataRent> {
        val listItemArray = ArrayList<DataRent>()
        for (n in titleArray.indices) {
            val listItem = DataRent(titleArray[n], descriptionArray[n], imageArray[n])
            listItemArray.add(listItem)
        }
        return listItemArray
    }

    fun getImageId(imageArray: Int): IntArray {
        val type: TypedArray = resources.obtainTypedArray(imageArray)
        val count = type.length()
        val ids = IntArray(count)
        for (i in ids.indices) {
            ids[i] = type.getResourceId(i, 0)
        }
        type.recycle()
        return ids
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.image_back -> {
                finish()
                overridePendingTransition(0, 0)
            }
        }
    }
}
