package io.github.kabirnayeem99.friends.utils.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import io.github.kabirnayeem99.friends.R
import io.github.kabirnayeem99.friends.data.viewobject.User
import io.github.kabirnayeem99.friends.ui.UserDetailsActivity
import io.github.kabirnayeem99.friends.ui.UserDetailsActivity.Companion.USER_DATA
import java.lang.Exception
import javax.inject.Inject

/**
 * It will determine the children of user recycler view
 *
 * It works as a bridge between the recycler view and the user list item
 */
class UserAdapter @Inject constructor() : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        private var tvFullName: TextView = view.findViewById(R.id.tvFullName)
        private var tvCountry: TextView = view.findViewById(R.id.tvCountry)
        private var ivPortrait: ImageView = view.findViewById(R.id.ivPortrait)
        private var mcvUserCard: MaterialCardView = view.findViewById(R.id.mcvUserCard)

        /**
         * Will bind the user object to the
         * user list card view
         */
        @SuppressLint("SetTextI18n")
        fun bindTo(user: User) {
            tvFullName.text = "${user.name.first} ${user.name.last}"
            tvCountry.text = user.location.country

            try {
                Glide.with(view.context).load(user.picture.medium).into(ivPortrait)
            } catch (e: Exception) {
                Log.d(TAG, "bindTo: image could not be loaded ${e.message}")
            }

            mcvUserCard.setOnClickListener {
                val intent = Intent(view.context, UserDetailsActivity::class.java)
                intent.putExtra(USER_DATA, user)
                view.context.startActivity(intent)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val context = parent.context;
        val inflater = LayoutInflater.from(context)

        val userCard = inflater.inflate(R.layout.list_item_user, parent, false)

        return UserViewHolder(userCard)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = differ.currentList[position]

        holder.bindTo(user)
    }

    override fun getItemCount(): Int = differ.currentList.size

    private val diffCallback =
        object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                /*
                     as only image, country, and name would be shown,
                     no need to add comparison between other contents
                 */

                if (oldItem.location.country != newItem.location.country) {
                    return false
                }

                if (oldItem.name.first != newItem.name.first) {
                    return false
                }

                if (oldItem.name.last != newItem.name.last) {
                    return false
                }

                if (oldItem.picture.medium != newItem.picture.medium) {
                    return false
                }

                return true
            }
        }

    /**
     * This will consume the list of users from the user list live data
     *
     * and will present the data in the UI
     */
    val differ = AsyncListDiffer(this, diffCallback)

    companion object {
        private const val TAG = "UserAdapter"
    }
}