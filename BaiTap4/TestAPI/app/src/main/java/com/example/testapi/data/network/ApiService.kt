package com.example.testapi.data.network

import com.example.testapi.data.model.TaskResponse
import com.example.testapi.data.model.TaskResponse2
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("researchUTH/tasks")
    suspend fun getTasks(): Response<TaskResponse>

    @GET("researchUTH/task/{id}")
    suspend fun getTaskById(@Path("id") id : String) : Response<TaskResponse2>
}

