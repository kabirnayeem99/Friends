package io.github.kabirnayeem99.friends.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.kabirnayeem99.friends.R

class LandingActivity : AppCompatActivity() {

    private lateinit var tvFriendList: TextView
    private lateinit var rvFriends: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)
        initViews()
        setUpRecyclerView()
    }

    private fun initViews() {
        tvFriendList = findViewById(R.id.tvFriendList)
        rvFriends = findViewById(R.id.rvFriends)
    }


    private fun setUpRecyclerView() {
        rvFriends.apply {
            layoutManager = LinearLayoutManager(this@LandingActivity)
        }
    }

}