package com.example.pritam.kotlinretrofit.actvity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.example.pritam.kotlinretrofit.R
import com.example.pritam.kotlinretrofit.api.ApiClient
import com.example.pritam.kotlinretrofit.model.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val TAG = ApiClient::class.java!!.simpleName
    private lateinit var userModel: UserModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        callApiResponse("")
    }

    private fun callApiResponse(token: String) {
        try {
            ApiClient.singletonApiClient.getApiCall(token, object : Callback<UserModel> {
                override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                    userModel = response.body()!!
                    Log.d(TAG, "UserModel Data :" + userModel.toString())
                    Toast.makeText(this@MainActivity, userModel.toString(), Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<UserModel>, t: Throwable) {
                    Log.d(TAG, "Fetching Data Error : " + t.getStackTrace())
                    //Toast.makeText (getApplicationContext (), "Fetching Data Error" + t.getMessage (), Toast.LENGTH_LONG).show ();
                }
            })
        } catch (e: Exception) {
            Log.d(TAG, "Error msg : " + e.message)
        }
    }

    /*private fun apiServiceCallOnTripTruck() {
        request = RequestModel()
        request.setFname(Fname.getText().toString())
        Log.d(TAG, "Peeru API UserRegistration Request :" + request.toString())
        try
        {
            ApiClient.singletonApiClient.CallPostApi(request, object:Callback<ResponseModel> {
                override fun onResponse(call:Call<ResponseModel>, response:Response<ResponseModel>) {
                    signUpResponseModel = response.body()
                    Log.d(TAG, "Peeru Response : " + response.body())
                    if (!response.isSuccessful())
                    {
                        Log.d(TAG, "Peeru No Response")
                    }
                    else
                    {
                        Log.d(TAG, "Peeru signUpResponseModel Data " + signUpResponseModel.toString())
                        if (response.isSuccessful())
                        {
                        }
                        else
                            Toast.makeText(getApplicationContext(), "API No Response", Toast.LENGTH_LONG).show()
                    }
                }
                override fun onFailure(call:Call<ResponseModel>, t:Throwable) {
                    Toast.makeText(this@MainActivity, "API Not Response", Toast.LENGTH_SHORT).show()
                    Log.d("Registration Request", "Peeru Failed to process request : " + t.message.toString())
                    t.printStackTrace()
                }
            })
        }
        catch (e:Exception) {
            Log.d(TAG, "Peeru Error Notifications :" + e.message)
        }
    }*/
}
