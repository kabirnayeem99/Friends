package io.github.kabirnayeem99.friends.ui

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import io.github.kabirnayeem99.friends.R
import io.github.kabirnayeem99.friends.utils.Resource
import io.github.kabirnayeem99.friends.utils.adapters.UserAdapter
import io.github.kabirnayeem99.friends.viewmodels.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

@AndroidEntryPoint
class LandingActivity : AppCompatActivity() {

    private lateinit var rvFriends: RecyclerView
    private lateinit var pbLoading: ProgressBar


    @Inject
    lateinit var userAdapter: UserAdapter

    private val userViewModel: UserViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)
        initViews() // initialises the views by their id
        setUpObserver() // sets up user list data observer from viewmodel
        setUpRecyclerView() // sets up the recycler view
    }


    private fun initViews() {
        rvFriends = findViewById(R.id.rvFriends)
        pbLoading = findViewById(R.id.pbLoading)
    }


    private fun setUpObserver() {
        userViewModel.getUserList().observe(
            this@LandingActivity,
            { resource ->

                when (resource) {

                    is Resource.Loading -> {
                        // if it is loading,

                        // show the loading bar
                        pbLoading.visibility = View.VISIBLE
                    }

                    is Resource.Error -> {

                        // if there is an error,

                        // hide the loading bar
                        pbLoading.visibility = View.GONE

                        // show a toast message to inform the user
                        Toast.makeText(
                            this@LandingActivity,
                            "Could not load from Internet",
                            Toast.LENGTH_LONG
                        ).show()

                    }
                    is Resource.Success -> {

                        // if there was no error,

                        // hide the loading bar
                        pbLoading.visibility = View.GONE

                        // show the recycler view
                        rvFriends.visibility = View.VISIBLE

                        // and make the adapter differ consume the
                        // user list
                        userAdapter.differ.submitList(resource.data)
                    }
                }

            },
        )

    }


    private fun setUpRecyclerView() {
        rvFriends.apply {

            /*
                 if the device is in landscape mode, recycler view
                 will show a grid view with 2 span count.

                 and if it is in portrait mode, this will
                 show a linear layout with 1 span count
             */

            layoutManager =
                if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    GridLayoutManager(this@LandingActivity, 2) // for landscape mode
                } else {
                    LinearLayoutManager(this@LandingActivity) // for portrait mode
                }

            adapter = userAdapter
        }
    }

}