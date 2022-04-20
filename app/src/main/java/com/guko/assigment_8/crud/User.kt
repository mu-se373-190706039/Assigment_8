package com.guko.assigment_8.crud

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("user")
    val user: UserData
){

}
