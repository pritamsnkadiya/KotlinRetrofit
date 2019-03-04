package com.example.pritam.kotlinretrofit.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Person {
    @SerializedName("name")
    @Expose
    var name:String? = null
    @SerializedName("email")
    @Expose
    var email:String? = null
    @SerializedName("contact")
    @Expose
    var contact:String? = null
    @SerializedName("age")
    @Expose
    var age:String? = null

    override fun toString(): String {
        return "Person(name=$name, email=$email, contact=$contact, age=$age)"
    }

}