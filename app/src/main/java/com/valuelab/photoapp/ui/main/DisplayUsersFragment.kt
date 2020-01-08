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
import com.valuelab.photoapp.adapter.UsersDataAdapter
import com.valuelab.photoapp.di.Injectable
import com.valuelab.photoapp.viewmodel.UsersDataViewModel
import kotlinx.android.synthetic.main.display_users_fragment.*

/**
 * Fragment to display the list of users.
 */
class DisplayUsersFragment : Fragment(),
    RecyclerViewClickListener, Injectable {

    private lateinit var usersDataViewModel: UsersDataViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        usersDataViewModel = activity?.run {
            ViewModelProviders.of(this)[UsersDataViewModel::class.java]
        } ?: throw Exception("Invalid Activity") as Throwable
        return inflater.inflate(R.layout.display_users_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        makeRefreshCall()
        setAdapterToRecyclerView()
    }

    /**
     * Observe the data form view model, prepare adapter and sets it in recycler view.
     */
    private fun setAdapterToRecyclerView() {
        usersDataViewModel.usersLiveDetails.observe(viewLifecycleOwner, Observer { usersData ->
            display_users_recycler_view.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = UsersDataAdapter(
                    usersData,
                    this
                )
                progressBar.visibility = View.GONE
            }
        })
    }

    /**
     * Binding onclick listener and navigation action.
     */
    override fun onRecyclerViewItemClick(view: View, userId: Int) {
        when (view.id) {
            R.id.user_card_view -> {
                //val action = DisplayUsersFragmentDirections.actionDisplayUserFragmentToDisplayUserAlbumsFragment(userId)
                var bundle = bundleOf("userId" to userId)
                view.findNavController()
                    .navigate(R.id.action_displayUserFragment_to_displayUserAlbumsFragment, bundle)
            }
        }
    }

    /**
     * Make request to update view through repository.
     */
    private fun makeRefreshCall() {
        swipe_to_refresh.setOnRefreshListener {
            usersDataViewModel.getUsersData()
            swipe_to_refresh.isRefreshing = false
        }
    }
}
