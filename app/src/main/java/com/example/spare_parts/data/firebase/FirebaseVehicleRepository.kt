package com.example.spare_parts.data.firebase

import android.net.Uri
import androidx.lifecycle.LiveData
import com.example.spare_parts.common.task
import com.example.spare_parts.common.toUnit
import com.example.spare_parts.data.VehicleRepository
import com.example.spare_parts.data.common.map
import com.example.spare_parts.data.firebase.common.*
import com.example.spare_parts.models.*
import com.google.android.gms.tasks.Task

class FirebaseVehicleRepository : VehicleRepository {

    override fun uploadVehicleImage(imageUri: Uri): Task<Uri> =
        task { taskSource ->
            val uTask = storage.child("users/${currentUid()!!}/images").child(imageUri.lastPathSegment!!)
            uTask.putFile(imageUri).addOnCompleteListener {
                if (it.isSuccessful) {
                    uTask.putFile(imageUri).continueWithTask {
                        uTask.downloadUrl.addOnSuccessListener { uri->
                            taskSource.setResult(uri)
                        }
                    }
                }
            }
        }

    override fun updateVehicleImage(newImageUri: Uri): Task<Unit> =
        database.child("edit-list/${currentUid()!!}/image").setValue(newImageUri.toString()).toUnit()

    override fun updateVehicle(currentVehicle: DataVehicles, newVehicle: DataVehicles): Task<Unit> {
        val updateMap = mutableMapOf<String, Any?>()
        if (newVehicle.auto != currentVehicle.auto) updateMap["auto"] = newVehicle.auto
        if (newVehicle.work != currentVehicle.work) updateMap["work"] = newVehicle.work
        if (newVehicle.heightArrow != currentVehicle.heightArrow) updateMap["heightArrow"] = newVehicle.heightArrow
        if (newVehicle.massArrow != currentVehicle.massArrow) updateMap["massArrow"] = newVehicle.massArrow
        if (newVehicle.volumeMix != currentVehicle.volumeMix) updateMap["volumeMix"] = newVehicle.volumeMix
        if (newVehicle.volumeMixArrow != currentVehicle.volumeMixArrow) updateMap["volumeMixArrow"] = newVehicle.volumeMixArrow
        if (newVehicle.heightMixArrow != currentVehicle.heightMixArrow) updateMap["heightMixArrow"] = newVehicle.heightMixArrow
        if (newVehicle.widthManipulator != currentVehicle.widthManipulator) updateMap["widthManipulator"] = newVehicle.widthManipulator
        if (newVehicle.lengthManipulator != currentVehicle.lengthManipulator) updateMap["lengthManipulator"] = newVehicle.lengthManipulator
        if (newVehicle.massManipulator != currentVehicle.massManipulator) updateMap["massManipulator"] = newVehicle.massManipulator
        if (newVehicle.lengthArrowManipulator != currentVehicle.lengthArrowManipulator) updateMap["lengthArrowManipulator"] =
            newVehicle.lengthArrowManipulator
        if (newVehicle.massArrowManipulator != currentVehicle.massArrowManipulator) updateMap["massArrowManipulator"] =
            newVehicle.massArrowManipulator
        if (newVehicle.volumeDump != currentVehicle.volumeDump) updateMap["volumeDump"] = newVehicle.volumeDump
        if (newVehicle.massDump != currentVehicle.massDump) updateMap["massDump"] = newVehicle.massDump
        if (newVehicle.widthTruck != currentVehicle.widthTruck) updateMap["widthTruck"] = newVehicle.widthTruck
        if (newVehicle.lengthTruck != currentVehicle.lengthTruck) updateMap["lengthTruck"] = newVehicle.lengthTruck
        if (newVehicle.massTruck != currentVehicle.massTruck) updateMap["massTruck"] = newVehicle.massTruck
        if (newVehicle.heightTruck != currentVehicle.heightTruck) updateMap["heightTruck"] = newVehicle.heightTruck
        if (newVehicle.massFrontExcavator != currentVehicle.massFrontExcavator) updateMap["massFrontExcavator"] = newVehicle.massFrontExcavator
        if (newVehicle.heightFrontExcavator != currentVehicle.heightFrontExcavator) updateMap["heightFrontExcavator"] =
            newVehicle.heightFrontExcavator
        if (newVehicle.volumeExcavator != currentVehicle.volumeExcavator) updateMap["volumeExcavator"] = newVehicle.volumeExcavator
        if (newVehicle.depthExcavator != currentVehicle.depthExcavator) updateMap["depthExcavator"] = newVehicle.depthExcavator
        if (newVehicle.volumeFrontExcavator != currentVehicle.volumeFrontExcavator) updateMap["volumeFrontExcavator"] =
            newVehicle.volumeFrontExcavator
        if (newVehicle.heightExcavator != currentVehicle.heightExcavator) updateMap["heightExcavator"] = newVehicle.heightExcavator

        return database.child("edit-list").child(currentUid()!!).updateChildren(updateMap).toUnit()
    }




    override fun createEditList(mkEditList: DataVehicles): Task<Unit> =
        database.child("edit-list").child(currentUid()!!).push().setValue(mkEditList).toUnit()

    override fun createAutocrane(mkAutocrane: DataVehicles): Task<Unit> =
        database.child("list-vehicle").child("autocranes").push().setValue(mkAutocrane).toUnit()

    override fun createConcreteMixer(mkConcreteMixer: DataVehicles): Task<Unit> =
        database.child("list-vehicle").child("concretemixers").push().setValue(mkConcreteMixer).toUnit()

    override fun createConcretePump(mkConcretePump: DataVehicles): Task<Unit> =
        database.child("list-vehicle").child("concretepumps").push().setValue(mkConcretePump).toUnit()

    override fun createBoardManipulator(mkBoardManipulator: DataVehicles): Task<Unit> =
        database.child("list-vehicle").child("boardmanipulators").push().setValue(mkBoardManipulator).toUnit()

    override fun createListDump(mkListDump: DataVehicles): Task<Unit> =
        database.child("list-vehicle").child("dumps").push().setValue(mkListDump).toUnit()

    override fun createListTruck(mkListTruck: DataVehicles): Task<Unit> =
        database.child("list-vehicle").child("trucks").push().setValue(mkListTruck).toUnit()

    override fun createBackhoeLoader(mkBackhoeLoader: DataVehicles): Task<Unit> =
        database.child("list-vehicle").child("backhoeloaders").push().setValue(mkBackhoeLoader).toUnit()



    override fun getEditList(): LiveData<List<DataVehicles>> =
        FirebaseLiveData(database.child("edit-list").child(currentUid()!!)).map {
            it.children.map { it.getValue(DataVehicles::class.java)!! }
        }

    override fun getListAutocrane(): LiveData<List<DataVehicles>> =
        FirebaseLiveData(database.child("list-vehicle").child("autocranes")).map {
            it.children.map { it.getValue(DataVehicles::class.java)!! }
        }

    override fun getListConcreteMixer(): LiveData<List<DataVehicles>> =
        FirebaseLiveData(database.child("list-vehicle").child("concretemixers")).map {
            it.children.map { it.getValue(DataVehicles::class.java)!! }
        }

    override fun getListConcretePump(): LiveData<List<DataVehicles>> =
        FirebaseLiveData(database.child("list-vehicle").child("concretepumps")).map {
            it.children.map { it.getValue(DataVehicles::class.java)!! }
        }

    override fun getListBoardManipulator(): LiveData<List<DataVehicles>> =
        FirebaseLiveData(database.child("list-vehicle").child("boardmanipulators")).map {
            it.children.map { it.getValue(DataVehicles::class.java)!! }
        }

    override fun getListListDump(): LiveData<List<DataVehicles>> =
        FirebaseLiveData(database.child("list-vehicle").child("dumps")).map {
            it.children.map { it.getValue(DataVehicles::class.java)!! }
        }

    override fun getListListTruck(): LiveData<List<DataVehicles>> =
        FirebaseLiveData(database.child("list-vehicle").child("trucks")).map {
            it.children.map { it.getValue(DataVehicles::class.java)!! }
        }

    override fun getListBackhoeLoader(): LiveData<List<DataVehicles>> =
        FirebaseLiveData(database.child("list-vehicle").child("backhoeloaders")).map {
            it.children.map { it.getValue(DataVehicles::class.java)!! }
        }




    override fun getUser(): LiveData<User> =
        database.child("users").child(currentUid()!!).liveData().map {
            it.asUser()!!
        }

    override fun getVehicle(): LiveData<DataVehicles> =
        database.child("edit-list").child(currentUid()!!).liveData().map {
            it.asVehicles()!!
        }

    override fun createApplication(mkListApplication: DataApplication): Task<Unit> =
        database.child("list-app").push().setValue(mkListApplication).toUnit()


    override fun getListApplication(): LiveData<List<DataApplication>> =
        FirebaseLiveData(database.child("list-app")).map {
            it.children.map { it.getValue(DataApplication::class.java)!! }
        }
}




