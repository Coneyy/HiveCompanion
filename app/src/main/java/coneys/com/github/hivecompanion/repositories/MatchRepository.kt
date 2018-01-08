package coneys.com.github.hivecompanion.repositories

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import coneys.com.github.hivecompanion.api.AuthorizeCaller
import coneys.com.github.hivecompanion.api.HiveApi
import coneys.com.github.hivecompanion.api.RequestWrapper
import coneys.com.github.hivecompanion.base.repository.Repository
import coneys.com.github.hivecompanion.domain.Match
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MatchRepository(val api: HiveApi, private val authorizeCaller: AuthorizeCaller, private val userRepository: UserRepository) : Repository<List<Match>> {

    override fun fetchData(): LiveData<List<Match>> {

        val data = MutableLiveData<List<Match>>()
        val requestWrapper = RequestWrapper(authorizeCaller) {
            api.getMatches(it, userRepository.getCurrentUser()?.id ?: "")
        }

        requestWrapper.enqueue(object : Callback<List<Match>> {
            override fun onResponse(call: Call<List<Match>>?, response: Response<List<Match>>?) {
                response?.let {
                    if (response.isSuccessful) {
                        data.value = response.body()
                    } else {
                        data.value = null

                    }
                }
            }

            override fun onFailure(call: Call<List<Match>>?, t: Throwable?) {
                data.value = null
            }

        })

        return data

    }

}