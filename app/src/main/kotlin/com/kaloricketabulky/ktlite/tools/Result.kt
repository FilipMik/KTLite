package com.kaloricketabulky.ktlite.tools

sealed class Result<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Result<T>(data)
    class Loading<T>(data: T? = null) : Result<T>(data)
    class Error<T>(message: String, data: T? = null) : Result<T>(data, message)
}
