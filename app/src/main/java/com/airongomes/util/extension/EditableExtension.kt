package com.airongomes.util.extension

import android.text.Editable
import android.util.Patterns

fun Editable?.hasValidField() = !isNullOrBlank()

fun Editable?.hasValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()