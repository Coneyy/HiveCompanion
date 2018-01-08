package coneys.com.github.hivecompanion.repositories

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import coneys.com.github.hivecompanion.api.AuthorizeCaller
import coneys.com.github.hivecompanion.api.HiveApi
import coneys.com.github.hivecompanion.api.RequestWrapper
import coneys.com.github.hivecompanion.base.repository.Repository
import coneys.com.github.hivecompanion.domain.UserPoints
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PointsRepository (val api: HiveApi, private val authorizeCaller: AuthorizeCaller) : Repository<List<UserPoints>> {

    override fun fetchData(): LiveData<List<UserPoints>> {

        val data = MutableLiveData<List<UserPoints>>()
        val requestWrapper = RequestWrapper(authorizeCaller) {
            api.getPoints(it)
        }

        requestWrapper.enqueue(object : Callback<List<UserPoints>> {
            override fun onResponse(call: Call<List<UserPoints>>?, response: Response<List<UserPoints>>?) {
                response?.let {
                    if (response.isSuccessful) {
                        data.value = response.body()
                    } else {
                        data.value = null

                    }
                }
            }

            override fun onFailure(call: Call<List<UserPoints>>?, t: Throwable?) {
                data.value = null
            }

        })

        return data

    }

}