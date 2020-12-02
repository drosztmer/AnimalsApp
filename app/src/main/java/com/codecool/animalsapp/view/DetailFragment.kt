package com.codecool.animalsapp.view

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.codecool.animalsapp.R
import com.codecool.animalsapp.model.Animal
import com.codecool.animalsapp.util.getProgressDrawable
import com.codecool.animalsapp.util.loadImage
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        populateViewsWithData()
        args.animal.imageUrl?.let { setupBackgroundColor(it) }
    }

    private fun setupBackgroundColor(url: String) {
        Glide.with(this)
            .asBitmap()
            .load(url)
            .into(object: CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    Palette.from(resource)
                        .generate() { palette ->
                            val intColor = palette?.lightMutedSwatch?.rgb ?: 0
                            animal_layout.setBackgroundColor(intColor)
                        }
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                }

            })
    }

    private fun populateViewsWithData() {
        animal_image.loadImage(args.animal.imageUrl, getProgressDrawable(requireContext()))
        animal_name.text = args.animal.name
        animal_location.text = args.animal.location
        animal_lifespan.text = args.animal.lifeSpan
        animal_diet.text = args.animal.diet
    }

}