package com.guko.assigment_8.crud

import com.google.gson.annotations.SerializedName

data class UserData(
    @SerializedName("email")
    val email: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("username")
    val username: String
)
