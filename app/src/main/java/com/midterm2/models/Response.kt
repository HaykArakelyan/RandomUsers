package com.midterm2.models

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("results")
    val results: List<User>?
)
