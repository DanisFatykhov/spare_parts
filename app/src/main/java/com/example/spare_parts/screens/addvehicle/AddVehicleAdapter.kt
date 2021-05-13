package com.example.spare_parts.screens.addvehicle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.spare_parts.R
import com.example.spare_parts.models.DataVehicles
import com.example.spare_parts.screens.common.GlideApp
import com.example.spare_parts.screens.common.SimpleCallback

class AddVehicleAdapter(private val onClick: (DataVehicles) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var dataItemList = ArrayList<DataVehicles>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return DataViewHolder(view)
    }

    override fun getItemCount() = dataItemList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val list = dataItemList[position]
        (holder as DataViewHolder).bind(list, onClick)
        with(holder.itemView) {
            val image = findViewById<ImageView>(R.id.vehicle_image)
            val name = findViewById<TextView>(R.id.vehicle_name)
            val price = findViewById<TextView>(R.id.price)
            val city = findViewById<TextView>(R.id.city)
            image.loadImage(list.image!!)
            name.text = list.auto.capitalize()
            if (list.price1 == null && list.price2 == null) price.text = null else
                if (list.price1 != null) price.text = "${list.price1}/час" else
                    if (list.price2 != null) price.text = "${list.price2}/смена"
            city.text = list.city.capitalize()
        }

    }

    class DataViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: DataVehicles, onClick: (DataVehicles) -> Unit) {
            with(itemView) {
                val vehicle = findViewById<View>(R.id.vehicle)
                vehicle.setOnClickListener { onClick(data) }
            }
        }
    }

    private fun ImageView.loadImage(image: String) {
        GlideApp.with(this).load(image).centerCrop().fallback(R.drawable.photo).into(this)
    }

    fun updateList(newList: List<DataVehicles>) {
        dataItemList.clear()
        dataItemList.addAll(newList)
        notifyDataSetChanged()
//        val diffResult = DiffUtil.calculateDiff(SimpleCallback(this.dataItemList, newList) { it.uid } )
//        this.dataItemList = newList
//        diffResult.dispatchUpdatesTo(this)
    }
}