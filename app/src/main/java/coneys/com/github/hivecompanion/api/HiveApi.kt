package coneys.com.github.hivecompanion.api

import coneys.com.github.hivecompanion.domain.Match
import coneys.com.github.hivecompanion.domain.User
import coneys.com.github.hivecompanion.domain.UserPoints
import retrofit2.Call
import retrofit2.http.*


interface HiveApi {

    @GET("matches/history/{id}")
    fun getMatches(@Header("Authorization") token:String,@Path("id") id:String): Call<List<Match>>

    @GET("matches/highscores")
    fun getPoints(@Header("Authorization") token:String): Call<List<UserPoints>>

    @POST("user/signin")
    fun login(@Body item: User): Call<UserSuccessResponse>

    @POST("user/create")
    fun register(@Body item: User):Call<UserSuccessResponse>

}