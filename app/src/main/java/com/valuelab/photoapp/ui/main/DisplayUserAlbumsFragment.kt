package com.valuelab.photoapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.valuelab.photoapp.R
import com.valuelab.photoapp.adapter.RecyclerViewClickListener
import com.valuelab.photoapp.adapter.UsersAlbumsAdapter
import com.valuelab.photoapp.di.Injectable
import com.valuelab.photoapp.viewmodel.UsersDataViewModel
import kotlinx.android.synthetic.main.display_user_albums_fragment.*
import kotlinx.android.synthetic.main.display_users_fragment.progressBar
import kotlinx.android.synthetic.main.display_users_fragment.swipe_to_refresh

/**
 * Display users albums
 */
class DisplayUserAlbumsFragment : Fragment(), Injectable, RecyclerViewClickListener {


    private lateinit var usersDataViewModel: UsersDataViewModel
    private var selectedUserId: Int? = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        usersDataViewModel = activity?.run {
            ViewModelProviders.of(this)[UsersDataViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
        return inflater.inflate(R.layout.display_user_albums_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        usersDataViewModel.getUserAlbums()
        arguments?.let {
            selectedUserId = arguments?.getInt("userId")
        }
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
        usersDataViewModel.userAlbumsLiveData.observe(viewLifecycleOwner, Observer { userAlbums ->
            val album = userAlbums.filter { it.userId == selectedUserId }
            display_user_album_recycler_view.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = UsersAlbumsAdapter(
                    album,
                    this
                )
                progressBar.visibility = View.GONE
            }
        })
    }

    /**
     * Binding onclick listener and navigation action.
     */
    override fun onRecyclerViewItemClick(view: View, albumId: Int) {
        when (view.id) {
            R.id.user_albums_card_view -> {
                var bundle = bundleOf("albumId" to albumId)
                view.findNavController()
                    .navigate(
                        R.id.action_displayUserAlbumsFragment_to_displayUserPhotosFragment2,
                        bundle
                    )
            }
        }
    }

    /**
     * Make request to update view through repository.
     */
    private fun makeRefreshCall() {
        swipe_to_refresh.setOnRefreshListener {
            usersDataViewModel.getUserAlbums()
            swipe_to_refresh.isRefreshing = false
        }
    }

}
