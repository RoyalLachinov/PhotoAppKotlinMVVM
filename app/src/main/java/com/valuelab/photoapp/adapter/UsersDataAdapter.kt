package com.valuelab.photoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.valuelab.photoapp.R
import com.valuelab.photoapp.databinding.UsersCardViewsBinding
import com.valuelab.photoapp.model.UsersDetails
import javax.inject.Inject

/**
 *  Adapter class to bind the view holder
 */
class UsersDataAdapter @Inject constructor(
    private val usersData: List<UsersDetails>,
    private val listener: RecyclerViewClickListener
) : RecyclerView.Adapter<UsersDataAdapter.UsersDataViewHolder>() {

    /**
     * creates the view holder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UsersDataViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.users_card_views,
                parent,
                false
            )
        )

    /**
     * sets the users data and listeners to holder.
     */
    override fun onBindViewHolder(holder: UsersDataViewHolder, position: Int) {
        holder.usersCardViewBinding.usersData = usersData[position]
        holder.itemView.setOnClickListener {
            listener.onRecyclerViewItemClick(
                holder.itemView,
                usersData[position].id
            )
        }
    }

    /**
     * gets the users data list size.
     */
    override fun getItemCount() = usersData.size

    /**
     * binds view to view holder.
     */
    inner class UsersDataViewHolder(
        val usersCardViewBinding: UsersCardViewsBinding
    ) : RecyclerView.ViewHolder(usersCardViewBinding.root)

}