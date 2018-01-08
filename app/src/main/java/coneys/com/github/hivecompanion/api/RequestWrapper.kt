package coneys.com.github.hivecompanion.api

import coneys.com.github.hivecompanion.App
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RequestWrapper<T>(private val authorizeCaller: AuthorizeCaller, private val callCreator: (token: String) -> Call<T>) {

    fun enqueue(callback: Callback<T>, token: String = App.token) {
        callCreator.invoke(token).enqueue(object : Callback<T> {
            override fun onFailure(call: Call<T>?, t: Throwable?) {
                callback.onFailure(call, t)
            }

            override fun onResponse(call: Call<T>?, response: Response<T>?) {
                if (response?.code() == 401) {
                    getAuthorization(callback, call, response)
                } else {
                    callback.onResponse(call, response)
                }
            }

        })

    }

    private fun getAuthorization(callback: Callback<T>, call: Call<T>?, response: Response<T>?) {
        authorizeCaller.getAuthorization(failureCallback = { callback.onResponse(call, response) }, successCallback = { enqueue(callback, it) })
    }

}