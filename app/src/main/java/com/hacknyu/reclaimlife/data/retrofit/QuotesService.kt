package com.hacknyu.reclaimlife.data.retrofit

import com.hacknyu.reclaimlife.model.Quotes
import retrofit2.http.GET

interface QuotesService {

    @GET("?api=quotes&key=afd81570bea4407a01bb9150c183e39d")
    suspend fun getQuotes(): List<Quotes>

}