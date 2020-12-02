package com.codecool.animalsapp.view

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.codecool.animalsapp.R
import com.codecool.animalsapp.databinding.FragmentDetailBinding
import com.codecool.animalsapp.util.getProgressDrawable
import com.codecool.animalsapp.util.loadImage

class DetailFragment : Fragment() {

    private val args by navArgs<DetailFragmentArgs>()

    private lateinit var dataBinding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        dataBinding.animal = args.animal
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.animal?.imageUrl?.let { setupBackgroundColor(it) }
    }

    private fun setupBackgroundColor(url: String) {
        Glide.with(this)
            .asBitmap()
            .load(url)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    Palette.from(resource)
                        .generate() { palette ->
                            val intColor = palette?.lightMutedSwatch?.rgb ?: 0
                            dataBinding.animalLayout.setBackgroundColor(intColor)
                        }
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                }

            })
    }

}