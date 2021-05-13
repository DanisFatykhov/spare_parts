package com.example.spare_parts.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.spare_parts.R

class FragmentDescription(private val fragmentClose: FragmentCloseInterface, val work: String?) : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_description, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imgBack = view.findViewById<ImageView>(
            R.id.image_back
        )
        val workDesc = view.findViewById<TextView>(
            R.id.work_description
        )
        workDesc.text =  work?.capitalize()

        imgBack.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
        }
    }

    override fun onDetach() {
        super.onDetach()
        fragmentClose.onFragmentClose()
    }
}