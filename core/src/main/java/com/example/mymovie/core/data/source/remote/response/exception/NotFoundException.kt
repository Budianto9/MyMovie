package com.example.mymovie.core.data.source.remote.response.exception

import okio.IOException

class NotFoundException : IOException(){

    override val message: String?
        get() = "Not Found"
}