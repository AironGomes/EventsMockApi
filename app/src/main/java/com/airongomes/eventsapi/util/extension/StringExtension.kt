package com.airongomes.eventsapi.util.extension

import android.util.Patterns

fun Any?.toStringOrEmpty() = this?.toString().orEmpty()

fun String.hasValidField() = isNotBlank()

fun String.hasValidEmail() = isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()


