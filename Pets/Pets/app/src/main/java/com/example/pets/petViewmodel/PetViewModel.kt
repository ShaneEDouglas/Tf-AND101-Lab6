package com.example.pets.petViewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestHeaders
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import kotlin.math.log

class PetViewModel: ViewModel() {

    val dog_img_url = MutableLiveData<List<String>>()
    val dogImageUrl: LiveData<List<String>> get() = dog_img_url



    fun GetDogImageURl() {

        val client = AsyncHttpClient()
        val params = RequestParams()
        val headers = RequestHeaders()

        headers["Accept"] = "application/json"

        params["Apikey"] = "123456777"
        params["format"] = "json"

        client.get("https://dog.ceo/api/breeds/image/random/20",object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e("Dog Image", "Failed to get dog image: $throwable")
            }

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON?) {
                json.let {
                    val jsonarray = it?.jsonObject?.getJSONArray("message")
                    val urilist = mutableListOf<String>()
                    for (i in 0 until jsonarray!!.length()) {
                        jsonarray?.getString(i)?.let { it1 -> urilist.add(it1) }
                    }

                    dog_img_url.postValue(urilist)

                }
                Log.i("Dog Image", "Success: $json")

            }

        })

    }
}