package com.aamirashraf.retrofit_complete

import retrofit2.Response
import retrofit2.http.GET

interface TodoApi {
    @GET("/todos")
   suspend fun getTodos():Response<Todos>
}