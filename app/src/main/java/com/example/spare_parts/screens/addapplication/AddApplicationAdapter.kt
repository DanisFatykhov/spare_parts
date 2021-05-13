package com.example.spare_parts.screens.addapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.spare_parts.R
import com.example.spare_parts.models.DataApplication
import com.example.spare_parts.screens.common.SimpleCallback

class AddApplicationAdapter(private val onClick: (DataApplication) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var dataItemList = listOf<DataApplication>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(data: DataApplication, onClick: (DataApplication) -> Unit) {
            with(itemView) {
                val application = findViewById<View>(R.id.application)
                application.setOnClickListener { onClick(data) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_app_item, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount() = dataItemList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val list = dataItemList[position]
        with(holder.itemView) {
            val title = findViewById<TextView>(R.id.title_app)
            val location = findViewById<TextView>(R.id.address_app)
            val description = findViewById<TextView>(R.id.description_app)
            title.text = list.title_app.capitalize()
            location.text = list.address.capitalize()
            description.text = list.description.capitalize()
        }
        (holder as ViewHolder).bind(dataItemList[position], onClick)
    }

    fun updateList(newList: List<DataApplication>) {
        val diffResult = DiffUtil.calculateDiff(SimpleCallback(this.dataItemList, newList) { it.uid } )
        this.dataItemList = newList
        diffResult.dispatchUpdatesTo(this)
    }
}
