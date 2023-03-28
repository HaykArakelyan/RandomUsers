package com.midterm2.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("name")
    val name: Name?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("nat")
    val nat: String?
)
