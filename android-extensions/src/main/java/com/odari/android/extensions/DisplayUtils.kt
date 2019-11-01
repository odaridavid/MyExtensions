package com.odari.android.extensions

import android.content.Context
import android.widget.Toast

fun Context.displayShortToast(data: Any) {
    Toast.makeText(this, "$data", Toast.LENGTH_SHORT).show()
}

fun Context.displayLongToast(data: Any) {
    Toast.makeText(this, "$data", Toast.LENGTH_LONG).show()
}