package com.example.mymovie.core.data.source.remote.response.exception

import okio.IOException

class UnknownException : IOException() {

    override val message: String?
        get() = "Some Unknown Error Occurred"
}