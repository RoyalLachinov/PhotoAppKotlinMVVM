package com.valuelab.photoapp.repository

import com.valuelab.photoapp.service.NetworkApiRequest
import com.valuelab.photoapp.service.UserServiceApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkRepository @Inject constructor(private val serviceApi: UserServiceApi) :
    NetworkApiRequest() {
    suspend fun getUsersData() = apiRequest { serviceApi.getUsersDataResponse() }

    suspend fun getUserAlbums() = apiRequest { serviceApi.getUserAlbumsResponse() }

    suspend fun getUserPhotos() = apiRequest { serviceApi.getUserPhotosResponse() }
}