package com.kaloricketabulky.ktlite.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface KalorickeTabulkyApi {


    //FilMik23
    @GET("getSearchPotraviny.php")
    suspend fun getFoodList(
        @Query("q") searchQuery: String
    )

    @GET("getPotravina.php")
    suspend fun getFoodDetail(
        @Query("GUID_Potravina") foodId: String
    )
}
