package com.example.spare_parts.screens.vehicle

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.lifecycle.Observer
import com.example.spare_parts.R
import com.example.spare_parts.databinding.ActivityVehicleBinding
import com.example.spare_parts.screens.FragmentCloseInterface
import com.example.spare_parts.screens.FragmentDescription
import com.example.spare_parts.screens.common.BaseActivity
import com.example.spare_parts.screens.common.Close
import com.example.spare_parts.screens.common.setupAuthGuard
import com.squareup.picasso.Picasso


class VehicleActivity : BaseActivity(),
    FragmentCloseInterface {
    private lateinit var mViewModel: VehicleViewModel
    private lateinit var listView: ListView
    private lateinit var rootElement : ActivityVehicleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootElement = ActivityVehicleBinding.inflate(layoutInflater)
        val view = rootElement.root
        setContentView(view)

        Close(rootElement.imageBack)

        setupAuthGuard {
            mViewModel = initViewModel()
            mViewModel.user.observe(this, Observer {
                it?.let {
                }
            })

            with(rootElement) {
                val city = intent.getStringExtra("city")
                town.text = city?.capitalize()

                val region = intent.getStringExtra("region")
                area.text = region?.capitalize()

                val username = intent.getStringExtra("username")
                name.text = username?.capitalize()

                val auto = intent.getStringExtra("auto")
                nameVehicle.text = auto?.capitalize()

                val work = intent.getStringExtra("work")
                if (work != null) {
                    if (work.length > 150) {
                        val textCon = work.substring(0, 150) + "..."
                        workDescription.text = textCon.capitalize()
                    } else {
                        workDescription.text = work.capitalize()
                    }
                }
                val price1 = intent.getStringExtra("price1")
                cash1.text = if (price1 == null) null else "$price1/??????"

                val price2 = intent.getStringExtra("price2")
                cash2.text = if (price2 == null) null else "$price2/??????????"

                if (intent.hasExtra("image")) {
                    Picasso.get().load(intent?.extras?.getString("image") ?: "").into(autoImage)
                }
            }

            listView = findViewById(R.id.list_ttx_vehicle)
            val ttx: ArrayList<String> = arrayListOf()

            if (intent.getStringExtra("heightArrow") != null) {
                val heightArrow = intent.getStringExtra("heightArrow")
                ttx.add("???????????? ????????????, $heightArrow ??")
            }
            if (intent.getStringExtra("massArrow") != null) {
                val massArrow = intent.getStringExtra("massArrow")
                ttx.add("???????????????????????????????? ??????????, $massArrow ??")
            }

            if (intent.getStringExtra("volumeMix") != null) {
                val volumeMix = intent.getStringExtra("volumeMix")
                ttx.add("?????????? ??????????????, $volumeMix ??????.??")
            }

            if (intent.getStringExtra("volumeMixArrow") != null) {
                val volumeMixArrow = intent.getStringExtra("volumeMixArrow")
                ttx.add("?????????? ??????????????, $volumeMixArrow ??????.??")
            }
            if (intent.getStringExtra("heightMixArrow") != null) {
                val heightMixArrow = intent.getStringExtra("heightMixArrow")
                ttx.add("???????????? ????????????, $heightMixArrow ??")
            }

            if (intent.getStringExtra("widthManipulator") != null) {
                val widthManipulator = intent.getStringExtra("widthManipulator")
                ttx.add("???????????? ????????????, $widthManipulator ??")
            }

            if(intent.getStringExtra("lengthManipulator") != null) {
                val lengthManipulator = intent.getStringExtra("lengthManipulator")
                ttx.add("?????????? ????????????, $lengthManipulator ??")
            }
            if(intent.getStringExtra("massManipulator") != null) {
                val massManipulator = intent.getStringExtra("massManipulator")
                ttx.add("???????????????????????????????? ????????????, $massManipulator ??")
            }
            if(intent.getStringExtra("lengthArrowManipulator") != null) {
                val lengthArrowManipulator = intent.getStringExtra("lengthArrowManipulator")
                ttx.add("?????????? ????????????, $lengthArrowManipulator ??")
            }
            if(intent.getStringExtra("massArrowManipulator") != null) {
                val massArrowManipulator = intent.getStringExtra("massArrowManipulator")
                ttx.add("???????????????????????????????? ????????????, $massArrowManipulator ??")
            }

            if (intent.getStringExtra("volumeDump") != null) {
                val volumeDump = intent.getStringExtra("volumeDump")
                ttx.add("?????????? ????????????, $volumeDump ??????.??")
            }
            if (intent.getStringExtra("massDump") != null) {
                val massDump = intent.getStringExtra("massDump")
                ttx.add("???????????????????????????????? ????????????, $massDump ??")
            }

            if (intent.getStringExtra("widthTruck") != null) {
                val widthTruck = intent.getStringExtra("widthTruck")
                ttx.add("???????????? ????????????????, $widthTruck ??")
            }
            if (intent.getStringExtra("lengthTruck") != null) {
                val lengthTruck = intent.getStringExtra("lengthTruck")
                ttx.add("?????????? ????????????????, $lengthTruck ??")
            }
            if (intent.getStringExtra("lengthTruck") != null) {
                val lengthTruck = intent.getStringExtra("lengthTruck")
                ttx.add("?????????? ????????????????, $lengthTruck ??")
            }
            if (intent.getStringExtra("massTruck") != null) {
                val massTruck = intent.getStringExtra("massTruck")
                ttx.add("????????????????????????????????, $massTruck ??")
            }
            if (intent.getStringExtra("heightTruck") != null) {
                val heightTruck = intent.getStringExtra("heightTruck")
                ttx.add("?????????????????????? ????????????, $heightTruck ??")
            }

            if (intent.getStringExtra("massExcavator") != null) {
                val massExcavator = intent.getStringExtra("massExcavator")
                ttx.add("???????????????????????????????? ????????????????????, $massExcavator ??")
            }
            if (intent.getStringExtra("heightFrontExcavator") != null) {
                val heightFrontExcavator = intent.getStringExtra("heightFrontExcavator")
                ttx.add("???????????? ?????????????????? ????????????????????, $heightFrontExcavator ??")
            }
            if (intent.getStringExtra("volumeExcavator") != null) {
                val volumeExcavator = intent.getStringExtra("volumeExcavator")
                ttx.add("?????????? ?????????????? ??????????, $volumeExcavator ??????.??")
            }
            if (intent.getStringExtra("depthExcavator") != null) {
                val depthExcavator = intent.getStringExtra("depthExcavator")
                ttx.add("?????????????? ??????????????, $depthExcavator ??")
            }
            if (intent.getStringExtra("volumeFrontExcavator") != null) {
                val volumeFrontExcavator = intent.getStringExtra("volumeFrontExcavator")
                ttx.add("?????????? ???????????????????????? ??????????, $volumeFrontExcavator ??????.??")
            }
            if (intent.getStringExtra("heightExcavator") != null) {
                val heightExcavator = intent.getStringExtra("heightExcavator")
                ttx.add("???????????? ???????????????? ??????????????????????, $heightExcavator ??")
            }

            val adapter = ArrayAdapter<String>(this, R.layout.list_ttx_item, ttx)
            listView.adapter = adapter
            rootElement.workDescription.setOnClickListener {
                rootElement.toolbar.visibility = View.GONE
                rootElement.contentMain.visibility = View.GONE
                rootElement.callNumber.visibility = View.GONE
                val fragment = supportFragmentManager.beginTransaction()
                fragment.replace(R.id.vehicle_content,
                    FragmentDescription(
                        this,
                        work = intent.getStringExtra("work")
                    )
                )
                fragment.commit()
            }
        }
    }

    override fun onFragmentClose() {
        rootElement.toolbar.visibility = View.VISIBLE
        rootElement.contentMain.visibility = View.VISIBLE
        rootElement.callNumber.visibility = View.VISIBLE
    }
}

