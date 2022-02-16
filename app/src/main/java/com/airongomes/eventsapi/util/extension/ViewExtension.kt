package com.airongomes.eventsapi.util.extension

import android.view.View
import android.widget.Toast

fun View.showMessage(text: String) {
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}