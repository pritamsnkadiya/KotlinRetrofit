package com.example.pritam.kotlinretrofit.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PersonModel {

    @SerializedName("Persons")
    @Expose
    var persons:List<Person>? = null

    override fun toString(): String {
        return "PersonModel(persons=$persons)"
    }

}