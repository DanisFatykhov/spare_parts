package com.example.spare_parts.screens.common

import android.app.Activity
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.spare_parts.R

fun Context.showToast(text: String?, duration: Int = Toast.LENGTH_LONG) {
    text?.let { Toast.makeText(this, it, duration).show() }
}

fun coordinateBtnAndInputs(btn: Button, vararg inputs: EditText) {
    btn.isEnabled = false
    val watcher = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            btn.isEnabled = inputs.all { it.text.isNotEmpty() }
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    }
    inputs.forEach { it.addTextChangedListener(watcher) }
}

fun Editable.toStringOrNull(): String? {
    val str = toString()
    return if (str.isEmpty()) null else str
}

fun ImageView.loadVehiclePhoto(image: String?) {
    ifNotDestroyed {
        GlideApp.with(this).load(image).into(this)
    }
}

fun Activity.Close(view: View) {
    view.setOnClickListener {
        finish()
        overridePendingTransition(0, 0)
    }
}

private fun View.ifNotDestroyed(block: () -> Unit) {
    if (!(context as Activity).isDestroyed) {
        block()
    }
}







