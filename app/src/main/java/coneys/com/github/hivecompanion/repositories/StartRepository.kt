package coneys.com.github.hivecompanion.repositories

import android.arch.lifecycle.MutableLiveData
import coneys.com.github.hivecompanion.api.*
import coneys.com.github.hivecompanion.domain.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class StartRepository(private val hiveApi: HiveApi) {
    fun login(item: User?): MutableLiveData<UserResponse> {
        val data = MutableLiveData<UserResponse>()
        item?.let {
            val login = hiveApi.login(item)
            login.enqueue(object : Callback<UserSuccessResponse> {
                override fun onResponse(call: Call<UserSuccessResponse>?, response: Response<UserSuccessResponse>?) {
                    response?.let {
                        if (response.isSuccessful) {
                            val user = response.body()?.user
                            val token = response.body()?.token
                            if (user != null && token != null)
                                data.value = UserSuccessResponse(user, token)
                            else
                                data.value = UserUnsuccessResponse(item, response.code(), response.message())
                        } else {
                            data.value = UserUnsuccessResponse(item, response.code(), response.message())
                        }
                    }
                }

                override fun onFailure(call: Call<UserSuccessResponse>?, t: Throwable?) {
                    data.value = UserFailureResponse(item)
                }
            })
        }
        return data

    }

    fun register(item: User?): MutableLiveData<UserResponse> {
        val data = MutableLiveData<UserResponse>()
        item?.let {
            val register = hiveApi.register(item)
            register.enqueue(object : Callback<UserSuccessResponse> {
                override fun onResponse(call: Call<UserSuccessResponse>?, response: Response<UserSuccessResponse>?) {
                    response?.let {
                        if (response.isSuccessful) {
                            val user = response.body()?.user
                            val token = response.body()?.token
                            if (user != null && token != null)
                                data.value = UserSuccessResponse(user, token)
                            else
                                data.value = UserUnsuccessResponse(item, response.code(), response.message())
                        } else {
                            data.value = UserUnsuccessResponse(item, response.code(), response.message())
                        }
                    }
                }

                override fun onFailure(call: Call<UserSuccessResponse>?, t: Throwable?) {
                    data.value = UserFailureResponse(item)
                }
            })
        }
        return data

    }


}