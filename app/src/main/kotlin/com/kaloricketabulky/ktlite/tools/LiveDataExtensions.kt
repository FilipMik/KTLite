package com.kaloricketabulky.ktlite.tools

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observeNonNull(lifecycleOwner: LifecycleOwner, callback: (T) -> Unit) {
    observe(lifecycleOwner, Observer { it?.let(callback) })
}
