package com.codecool.animalsapp.util

import android.content.Context
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.codecool.animalsapp.R
import com.codecool.animalsapp.model.Animal
import com.codecool.animalsapp.view.ListFragmentDirections

fun getProgressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = STROKE_WIDTH
        centerRadius = CENTER_RADIUS
        start()
    }
}

fun ImageView.loadImage(uri: String?, progressDrawable: CircularProgressDrawable) {
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.drawable.launcher)
    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .into(this)
}

@BindingAdapter("android:imageUrl")
fun loadImage(view: ImageView, url: String?) {
    view.loadImage(url, getProgressDrawable(view.context))
}

@BindingAdapter("android:goToDetails")
fun goToDetails(view: ConstraintLayout, animal: Animal) {
    view.setOnClickListener {
        val action = ListFragmentDirections.actionDetail(animal)
        view.findNavController().navigate(action)
    }
}