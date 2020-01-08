package com.valuelab.photoapp.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.valuelab.photoapp.R
import com.valuelab.photoapp.viewmodel.UsersDataViewModel
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Activity to host the fragment and preparing the common view model
 */
class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var userDataFactory: ViewModelProvider.Factory
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    lateinit var usersDataViewModel: UsersDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        prepareViewModel()
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    /**
     * Validates for internet connection and prepare the view model to consume the data on respective fragments.
     */
    private fun prepareViewModel() {
        usersDataViewModel = ViewModelProviders.of(this, userDataFactory).get(
            UsersDataViewModel::class.java
        )
        usersDataViewModel.getUsersData()
    }
}
