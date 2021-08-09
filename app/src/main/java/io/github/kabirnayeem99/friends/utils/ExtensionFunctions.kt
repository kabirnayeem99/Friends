package io.github.kabirnayeem99.friends.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.github.kabirnayeem99.friends.R


/**
 * loads the url into the imageview
 *
 * takes the image Url as a parameter
 */
fun ImageView.loadImage(url: String?) {

    val options = RequestOptions()
        .error(R.drawable.ic_image_not_found) // if the image can't be image can't be loaded icon
        .placeholder(R.drawable.ic_image_downloading) // show download icon when image is loading


    Glide.with(this.context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}