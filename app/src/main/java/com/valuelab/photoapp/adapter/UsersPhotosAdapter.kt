package com.valuelab.photoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.valuelab.photoapp.R
import com.valuelab.photoapp.databinding.UserPhotosCardViewsBinding
import com.valuelab.photoapp.model.UserPhotos
import javax.inject.Inject

/**
 *  Adapter class to bind the view holder
 */
class UsersPhotosAdapter @Inject constructor(
    private val userPhotos: List<UserPhotos>,
    private val listener: RecyclerViewClickListener
) : RecyclerView.Adapter<UsersPhotosAdapter.UserPhotosViewHolder>() {

    /**
     * creates the view holder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UserPhotosViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.user_photos_card_views,
                parent,
                false
            )
        )

    /**
     * sets the users data and listeners to holder.
     */
    override fun onBindViewHolder(holder: UserPhotosViewHolder, position: Int) {
        holder.userPhotosCardViewsBinding.userPhotos = userPhotos[position]
        holder.itemView.setOnClickListener {
            listener.onRecyclerViewItemClick(
                holder.itemView,
                userPhotos[position].id
            )
        }
    }

    /**
     * gets the users data list size.
     */
    override fun getItemCount() = userPhotos.size

    /**
     * binds view to view holder.
     */
    inner class UserPhotosViewHolder(
        val userPhotosCardViewsBinding: UserPhotosCardViewsBinding
    ) : RecyclerView.ViewHolder(userPhotosCardViewsBinding.root)

}