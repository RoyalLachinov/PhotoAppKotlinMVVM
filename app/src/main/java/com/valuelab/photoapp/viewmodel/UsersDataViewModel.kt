package com.valuelab.photoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.valuelab.photoapp.model.UserAlbums
import com.valuelab.photoapp.model.UserPhotos
import com.valuelab.photoapp.model.UsersDetails
import com.valuelab.photoapp.repository.NetworkRepository
import com.valuelab.photoapp.service.Coroutines
import kotlinx.coroutines.Job
import javax.inject.Inject

/**
 *  Common view model to gets the data from repository using coroutines.
 */
class UsersDataViewModel @Inject constructor(private val networkRepository: NetworkRepository) :
    ViewModel() {

    private lateinit var job: Job
    private val usersMutableLiveData = MutableLiveData<List<UsersDetails>>()
    private val userAlbumsMutableLiveData = MutableLiveData<List<UserAlbums>>()
    private val userPhotosMutableLiveData = MutableLiveData<List<UserPhotos>>()
    val usersLiveDetails: LiveData<List<UsersDetails>> get() = usersMutableLiveData
    val userAlbumsLiveData: LiveData<List<UserAlbums>> get() = userAlbumsMutableLiveData
    val userPhotosLiveData: LiveData<List<UserPhotos>> get() = userPhotosMutableLiveData

    /**
     * create jobs for async call to get the user data and sets it in live data object
     */
    fun getUsersData() {
        job = Coroutines.ioTheMain({ networkRepository.getUsersData() },
            { usersMutableLiveData.value = it!! })
    }

    /**
     * create jobs for async call to get the user album data and sets it in live data object
     */
    fun getUserAlbums() {
        job = Coroutines.ioTheMain({ networkRepository.getUserAlbums() },
            { userAlbumsMutableLiveData.value = it!! })
    }

    /**
     * create jobs for async call to get the user photos data and sets it in live data object
     */
    fun getUserPhotos() {
        job = Coroutines.ioTheMain({ networkRepository.getUserPhotos() },
            { userPhotosMutableLiveData.value = it!! })
    }


    /**
     * Cancel the job when view destroyed.
     */
    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}
