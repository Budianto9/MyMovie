package com.example.mymovie.core.data.source.remote.response.exception

import okio.IOException

class UnAuthorizedException : IOException() {

    override val message: String
        get() = "User Unauthorized"
}