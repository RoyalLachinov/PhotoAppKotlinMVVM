package com.valuelab.photoapp.di

import com.valuelab.photoapp.ui.main.DisplayUserAlbumsFragment
import com.valuelab.photoapp.ui.main.DisplayUserPhotosFragment
import com.valuelab.photoapp.ui.main.DisplayUsersFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeDisplayUserFragment(): DisplayUsersFragment

    @ContributesAndroidInjector
    abstract fun contributeDisplayUserAlbumsFragment(): DisplayUserAlbumsFragment

    @ContributesAndroidInjector
    abstract fun contributeDisplayUserPhotosFragment(): DisplayUserPhotosFragment
}
