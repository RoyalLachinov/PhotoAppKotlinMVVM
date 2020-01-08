package com.valuelab.photoapp.service

import com.valuelab.photoapp.model.UserAlbums
import com.valuelab.photoapp.model.UserPhotos
import com.valuelab.photoapp.model.UsersDetails
import retrofit2.Response
import retrofit2.http.GET

interface UserServiceApi {

    @GET("users")
    suspend fun getUsersDataResponse(): Response<List<UsersDetails>>

    @GET("albums")
    suspend fun getUserAlbumsResponse(): Response<List<UserAlbums>>

    @GET("photos")
    suspend fun getUserPhotosResponse(): Response<List<UserPhotos>>
}