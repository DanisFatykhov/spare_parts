package com.example.spare_parts.screens.rent

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.spare_parts.R
import com.example.spare_parts.models.DataRent
import com.example.spare_parts.screens.listvehicle.*

@Suppress("DEPRECATION")
class RentAdapter(listArray: ArrayList<DataRent>, context: Context) : RecyclerView.Adapter<RentAdapter.ViewHolder>() {

    private var listDataItem = listArray
    private var contextR = context

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById<TextView>(R.id.title_group)
        val description = view.findViewById<TextView>(R.id.description_group)
        val img = view.findViewById<ImageView>(R.id.image_group)

        fun bind(listItem: DataRent, context: Context) {
            title.text = listItem.title_group
            description.text = listItem.discription_group
            img.setImageResource(listItem.image_group)

            itemView.setOnClickListener {

                if (position == 0) {
                    val intent = Intent(context, ListBackhoeLoaders::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
                    context.startActivity(intent)
                }

                if (position == 1) {
                    val intent = Intent(context, ListDumps::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
                    context.startActivity(intent)
                }

                if (position == 2) {
                    val intent = Intent(context, ListTrucks::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
                    context.startActivity(intent)
                }

                if (position == 3) {
                    val intent = Intent(context, ListBoardManipulators::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
                    context.startActivity(intent)
                }

                if (position == 4) {
                    val intent = Intent(context, ListConcreteMixers::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
                    context.startActivity(intent)
                }

                if (position == 5) {
                    val intent = Intent(context, ListAutocranes::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
                    context.startActivity(intent)
                }

                if (position == 6) {
                    val intent = Intent(context, ListConcretePumps::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(contextR)
        return ViewHolder(
            inflater.inflate(R.layout.list_rent_item, parent, false)
        )
    }

    override fun getItemCount() = listDataItem.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listItem = listDataItem[position]
        holder.bind(listItem, contextR)
    }

}