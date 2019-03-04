package com.example.pritam.kotlinretrofit.actvity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.example.pritam.kotlinretrofit.R
import com.example.pritam.kotlinretrofit.adapter.PersonListAdapter
import com.example.pritam.kotlinretrofit.api.ApiClient
import com.example.pritam.kotlinretrofit.model.PersonModel
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val TAG = ApiClient::class.java!!.simpleName
    private lateinit var personModel: PersonModel
    var layoutManager: RecyclerView.LayoutManager? = null
    var adapter: PersonListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        (layoutManager as LinearLayoutManager).orientation = LinearLayoutManager.VERTICAL

        callApiResponse("")
    }

    private fun callApiResponse(token: String) {
        try {
            ApiClient.singletonApiClient.getApiCall(token, object : Callback<PersonModel> {
                override fun onResponse(call: Call<PersonModel>, response: Response<PersonModel>) {
                    personModel = response.body()!!
                    Log.d(TAG, "PersonModel Data :" + personModel.toString())
                    Toast.makeText(this@MainActivity, personModel.toString(), Toast.LENGTH_LONG).show()

                    adapter = PersonListAdapter(this@MainActivity, personModel.persons!!)
                    recyclerView.adapter = adapter

                }

                override fun onFailure(call: Call<PersonModel>, t: Throwable) {
                    Log.d(TAG, "Fetching Data Error : " + t.stackTrace)
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
