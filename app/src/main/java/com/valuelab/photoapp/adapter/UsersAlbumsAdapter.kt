package com.valuelab.photoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.valuelab.photoapp.R
import com.valuelab.photoapp.databinding.UserAlbumsCardViewsBinding
import com.valuelab.photoapp.model.UserAlbums
import javax.inject.Inject

/**
 *  Adapter class to bind the view holder
 */
class UsersAlbumsAdapter @Inject constructor(
    private val userAlbums: List<UserAlbums>,
    private val listener: RecyclerViewClickListener
) : RecyclerView.Adapter<UsersAlbumsAdapter.UserAlbumsViewHolder>() {

    /**
     * creates the view holder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UserAlbumsViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.user_albums_card_views,
                parent,
                false
            )
        )

    /**
     * sets the users data and listeners to holder.
     */
    override fun onBindViewHolder(holder: UserAlbumsViewHolder, position: Int) {
        holder.userAlbumsCardViewsBinding.userAlbums = userAlbums[position]
        holder.itemView.setOnClickListener {
            listener.onRecyclerViewItemClick(
                holder.itemView,
                userAlbums[position].userId
            )
        }
    }

    /**
     * gets the users data list size.
     */
    override fun getItemCount() = userAlbums.size

    /**
     * binds view to view holder.
     */
    inner class UserAlbumsViewHolder(
        val userAlbumsCardViewsBinding: UserAlbumsCardViewsBinding
    ) : RecyclerView.ViewHolder(userAlbumsCardViewsBinding.root)

}