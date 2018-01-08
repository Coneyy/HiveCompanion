package coneys.com.github.hivecompanion.dependencyInjection.api

import coneys.com.github.hivecompanion.api.AuthorizeCaller
import coneys.com.github.hivecompanion.api.HiveApi
import coneys.com.github.hivecompanion.repositories.StartRepository
import coneys.com.github.hivecompanion.repositories.UserRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


@Module
class ApiModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()


        return Retrofit.Builder().baseUrl("http://hivegameapi.azurewebsites.net/api/")
                .addConverterFactory(MoshiConverterFactory.create())
                .client(client)
                .build()
    }


    @Provides
    fun provideHiveApi(retrofit: Retrofit) = retrofit.create(HiveApi::class.java)


    @Provides
    fun provideStartRepository(api: HiveApi): StartRepository {
        return StartRepository(api)
    }


    @Provides
    fun provideRequestGateway(startRepository: StartRepository, userRepository: UserRepository): AuthorizeCaller {
        return AuthorizeCaller(startRepository, userRepository)
    }



}