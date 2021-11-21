package com.example.mymovie.core.data.source.remote.response.exception

import okio.IOException

class NoInternetException : IOException() {

    override val message: String?
        get() = "No Internet Connection"
}