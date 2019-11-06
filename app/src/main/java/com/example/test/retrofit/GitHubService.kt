package com.example.test.retrofit

import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface GitHubService {
    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String): Call<List<Repo>>

    @GET("users/{user}/repos")
    fun listRepos2(@Path("user") user: String): Call<ResponseBody>

    @GET("users/{user}/repos")
    fun listRepos3(@Path("user") user: String): Call<Void>

    @GET("users/{user}/repos")
    fun listRepos4(@Path("user") user: String): Single<List<Repo>>
}