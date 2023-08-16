package com.kaloricketabulky.ktlite.data.remote

import com.kaloricketabulky.ktlite.data.remote.dto.FoodDetailDto
import com.kaloricketabulky.ktlite.data.remote.dto.SearchResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface KalorickeTabulkyApi {

    @GET("getSearchPotraviny.php")
    suspend fun getFoods(
        @Query("Q") searchQuery: String
    ) : SearchResponseDto

    @GET("getPotravina.php")
    suspend fun getFoodDetail(
        @Query("GUID_Potravina") foodId: String
    ) : FoodDetailDto
}
