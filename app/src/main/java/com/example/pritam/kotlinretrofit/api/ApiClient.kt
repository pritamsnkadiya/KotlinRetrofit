package com.example.pritam.kotlinretrofit.api

import android.content.Context
import android.util.Log
import com.example.pritam.kotlinretrofit.model.PersonModel
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {
    private var context: Context? = null

    companion object {
        private val TAG = ApiClient::class.java!!.simpleName
        private val BASE_URL = "http://google.com/aryaoptic/api/"
        private val mLock = Any()
        private var retrofit: Retrofit? = null
        private var apiClient: ApiClient? = null
        val singletonApiClient:ApiClient
            get() {
                synchronized (mLock) {
                    if (apiClient == null)
                        apiClient = ApiClient()
                    return apiClient as ApiClient
                }
            }

        val client: Retrofit
            get() {
                if (retrofit == null)
                {
                    val okHttpClient = OkHttpClient().newBuilder()
                        .connectTimeout(60 * 5, TimeUnit.SECONDS)
                        .readTimeout(60 * 5, TimeUnit.SECONDS)
                        .writeTimeout(60 * 5, TimeUnit.SECONDS)
                    val gsonConverterFactory = GsonConverterFactory.create()
                    retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(okHttpClient.build())
                        .addConverterFactory(gsonConverterFactory)
                        .build()
                    val gson = GsonBuilder()
                        .setLenient()
                        .create()
                    retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(okHttpClient.build())
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build()
                }
                return this!!.retrofit!!
            }
    }
    fun getApiCall(token:String, callback: Callback<PersonModel>) {
        var call: Call<PersonModel>? = null
        try
        {
            val apiService = ApiClient.client.create(ApiInterface::class.java)
            Log.d(TAG, "Call Request URL :" +  Constant.API.API)
            call = apiService.getApiCall(Constant.API.API, token)
            call.enqueue(callback)
        }
        catch (e:Exception) {
            Log.e(TAG, e.toString(), e)
            callback.onFailure(call, e)
        }
    }

    /*fun CallPostApi(request:RequestModel, callback:Callback<ResponseModel>) {
        var call:Call<ResponseModel>? = null
        try
        {
            val apiService = ApiClient.getClient().create(ApiInterface::class.java)
            Log.d(TAG, "FinalPlaceOrderResponseModel Request URL : " + BASE_URL)
            call = apiService.getFinalPayDetails(request)
            call.enqueue(callback)
        }
        catch (e:Exception) {
            Log.e(TAG, e.toString(), e)
            callback.onFailure(call, e)
        }
    }*/
}