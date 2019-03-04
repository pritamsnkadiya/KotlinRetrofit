package com.example.pritam.kotlinretrofit.api

import com.example.pritam.kotlinretrofit.model.PersonModel
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    companion object {
        val DEFAULT_PAGE_SIZE = 10
    }

    @Headers("Accept: application/json")
    @GET
    fun getApiCall(@Url url:String, @Header("Authorization") tokenId:String): Call<PersonModel>

/*    @Headers("content-type: application/json")
    @POST("/aryaoptic/api/payment")
    fun getFinalPayDetails(@Body request:RequestModel):Call<ResponseModel>*/
}