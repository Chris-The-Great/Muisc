package com.example.ccmusichomework.rest


import com.example.ccmusichomework.model.MusicNetworkData
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ClassicMusicApi {

    /**
     * Setting up a get search for the api
     */
    @GET(PATH_SEARCH)
    suspend fun getMusic(
        @Query("term") genre: String = "classic",
        @Query("amp;media") media: String = "music",
        @Query("amp;entity") entity: String = "song",
        @Query("amp;limit") limit: Int = 50,
    ): Response<MusicNetworkData>

    /**
     * Setting up the base api and doing the framework for networking
     */
    companion object {
        const val BASE_URL = "https://itunes.apple.com/"
        private const val PATH_SEARCH = "search"

        private val okhttp =
            OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                )
                .build()

        val serviceApi: ClassicMusicApi =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okhttp)
                .build()
                .create(ClassicMusicApi::class.java)
    }
}