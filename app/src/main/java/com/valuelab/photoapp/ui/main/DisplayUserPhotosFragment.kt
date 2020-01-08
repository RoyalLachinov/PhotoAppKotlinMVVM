package com.valuelab.photoapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.valuelab.photoapp.R
import com.valuelab.photoapp.adapter.RecyclerViewClickListener
import com.valuelab.photoapp.adapter.UsersPhotosAdapter
import com.valuelab.photoapp.di.Injectable
import com.valuelab.photoapp.viewmodel.UsersDataViewModel
import kotlinx.android.synthetic.main.display_user_photos_fragment.*
import kotlinx.android.synthetic.main.display_users_fragment.progressBar
import kotlinx.android.synthetic.main.display_users_fragment.swipe_to_refresh

/**
 * Display user photos fragment.
 */
class DisplayUserPhotosFragment : Fragment(), Injectable, RecyclerViewClickListener {


    private lateinit var usersDataViewModel: UsersDataViewModel
    private var selectedAlbumId: Int? = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        usersDataViewModel = activity?.run {
            ViewModelProviders.of(this)[UsersDataViewModel::class.java]
        } ?: throw Exception("Invalid Activity") as Throwable
        return inflater.inflate(R.layout.display_user_photos_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
            selectedAlbumId = arguments?.getInt("albumId")
        }
        usersDataViewModel.getUserPhotos()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        makeRefreshCall()
        setAdapterToRecyclerView()
        super.onActivityCreated(savedInstanceState)
    }

    /**
     * Observe the data form view model, prepare adapter and sets it in recycler view.
     */
    private fun setAdapterToRecyclerView() {
        usersDataViewModel.userPhotosLiveData.observe(viewLifecycleOwner, Observer { userPhotos ->
            val photos = userPhotos.filter { it.albumId == selectedAlbumId }
            display_user_photos_recycler_view.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = UsersPhotosAdapter(
                    photos,
                    this
                )
                progressBar.visibility = View.GONE
            }
        })
    }

    /**
     * Binding onclick listener and navigation action.
     */
    override fun onRecyclerViewItemClick(view: View, usersDetails: Int) {
        //Todo
    }

    /**
     * Make request to update view through repository.
     */
    private fun makeRefreshCall() {
        swipe_to_refresh.setOnRefreshListener {
            usersDataViewModel.getUserPhotos()
            swipe_to_refresh.isRefreshing = false
        }
    }

}
