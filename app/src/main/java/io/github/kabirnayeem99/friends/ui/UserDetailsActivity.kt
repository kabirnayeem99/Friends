package io.github.kabirnayeem99.friends.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.UserData
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import io.github.kabirnayeem99.friends.R
import io.github.kabirnayeem99.friends.data.viewobject.User
import org.w3c.dom.Text
import android.content.Intent
import android.net.Uri


class UserDetailsActivity : AppCompatActivity() {

    lateinit var ivPortraitDetails: ImageView
    lateinit var tvFullNameDetails: TextView
    lateinit var tvAddressDetails: TextView
    lateinit var tvCityDetails: TextView
    lateinit var tvEmailDetails: TextView
    lateinit var tvCellPhoneDetails: TextView
    lateinit var llEmail: LinearLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)
        initViews()
        getUserData()
    }

    private fun initViews() {
        ivPortraitDetails = findViewById(R.id.ivPortraitDetails)
        tvFullNameDetails = findViewById(R.id.tvFullNameDetails)
        tvAddressDetails = findViewById(R.id.tvAddressDetails)
        tvCityDetails = findViewById(R.id.tvCityDetails)
        tvEmailDetails = findViewById(R.id.tvEmailDetails)
        tvCellPhoneDetails = findViewById(R.id.tvCellPhoneDetails)
        llEmail = findViewById(R.id.llEmail)
    }

    private fun getUserData() {
        val userData = intent.getParcelableExtra<User>(USER_DATA)

        if (userData != null) {
            loadData(userData)
        }

    }

    @SuppressLint("SetTextI18n")
    private fun loadData(user: User) {
        try {
            Glide.with(this).load(user.picture.large).into(ivPortraitDetails)
        } catch (e: Exception) {
            Log.e(TAG, "loadData: could not load the image ${e.message ?: "Unknown error"}")
        }

        tvFullNameDetails.text = "${user.name.first} ${user.name.last}"
        tvAddressDetails.text = user.location.street.name
        tvCityDetails.text =
            "${user.location.city}, ${user.location.state}, ${user.location.country}"
        tvEmailDetails.text = user.email
        tvCellPhoneDetails.text = user.cell

        llEmail.setOnClickListener {
            navigateToEmail(user.email)
        }

        // sets the action bar title to the first name of the user
        supportActionBar?.title = user.name.first

    }

    private fun navigateToEmail(email: String) {
        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:$email")
        }
        startActivity(Intent.createChooser(emailIntent, "Send email"))
    }

    companion object {
        const val USER_DATA = "user_data"
        private const val TAG = "UserDetailsActivity"
    }
}