package com.example.pritam.kotlinretrofit.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserModel {

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("designation")
    @Expose
    var designation: String? = null

    @SerializedName("age")
    @Expose
    var age: String? = null

    @SerializedName("city")
    @Expose
    var city: String? = null
    @SerializedName("hobby")
    @Expose
    var hobby: String? = null

    @SerializedName("dream")
    @Expose
    var dream: String? = null

    override fun toString(): String {
        return "UserModel(name='$name', designation='$designation', age='$age', city='$city', hobby='$hobby', dream='$dream')"
    }
}