package io.github.kabirnayeem99.friends.ui

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import io.github.kabirnayeem99.friends.R
import io.github.kabirnayeem99.friends.utils.Resource
import io.github.kabirnayeem99.friends.utils.adapters.UserAdapter
import io.github.kabirnayeem99.friends.viewmodels.UserViewModel
import javax.inject.Inject
import android.content.Intent
import android.util.Log
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout


@AndroidEntryPoint
class LandingActivity : AppCompatActivity() {

    private lateinit var rvFriends: RecyclerView
    private lateinit var pbLoading: ProgressBar
    private lateinit var ivNoInternet: ImageView
    private lateinit var parentLayout: View


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
        parentLayout = findViewById(android.R.id.content)
        rvFriends = findViewById(R.id.rvFriends)
        pbLoading = findViewById(R.id.pbLoading)
        ivNoInternet = findViewById(R.id.ivNoInternet)
    }


    private fun setUpObserver() {

        userViewModel.userListLiveData?.observe(
            this@LandingActivity,
            { resource ->


                when (resource) {

                    is Resource.Loading -> {
                        // if it is loading,

                        // show the loading bar
                        pbLoading.visibility = View.VISIBLE
                        ivNoInternet.visibility = View.GONE

                    }

                    is Resource.Error -> {

                        // if there is an error,

                        // hide the loading bar
                        pbLoading.visibility = View.GONE
                        ivNoInternet.visibility = View.VISIBLE

                        Log.e(tag, "setUpObserver: ${resource.message}")

                        handleErrorSnackBar()
                    }

                    is Resource.Success -> {

                        // if there was no error,

                        // hide the loading bar
                        pbLoading.visibility = View.GONE
                        ivNoInternet.visibility = View.GONE

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

    // shows snack bar based on error type
    // if the error is due to the internet connection is off
    // then it would show to turn on the internet
    private fun handleErrorSnackBar() {

        val snackBar = Snackbar.make(parentLayout, "Something went wrong", Snackbar.LENGTH_SHORT)


        if (!userViewModel.internetStatus) {
            snackBar.setText("Your Internet Connection is off")

            snackBar.setAction(
                "Turn On"
            ) {
                try {
                    launchWifiSettings()
                } catch (e: Exception) {
                    snackBar.setText("Could not open Wi-Fi settings")
                    snackBar.show()
                }
            }
            snackBar.show()

        } else {
            snackBar.show()

        }

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

            overScrollMode = View.OVER_SCROLL_NEVER

        }
    }

    private fun launchWifiSettings() {
        val intent = Intent("nandroid.settings.WIFI_SETTINGS")
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }


    private val tag = "LandingActivity"

}