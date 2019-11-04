package com.odari.android.extensions

import android.content.Context
import android.view.View
import android.widget.Toast

fun Context.displayShortToast(data: Any) {
    Toast.makeText(this, "$data", Toast.LENGTH_SHORT).show()
}

fun Context.displayLongToast(data: Any) {
    Toast.makeText(this, "$data", Toast.LENGTH_LONG).show()
}

fun View.hide() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}