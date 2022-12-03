package uz.digital.footballapp.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetroInstance {
    fun retroInstance(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://apiv3.apifootball.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideClient())
            .build()
            .create(ApiService::class.java)
    }

    private fun provideClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }
    val defaultUrl = "https://apiv3.apifootball.com/badges/logo_country/44_england.png"
}