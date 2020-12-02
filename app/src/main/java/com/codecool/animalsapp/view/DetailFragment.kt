package com.codecool.animalsapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
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

        animal_image.loadImage(args.animal.imageUrl, getProgressDrawable(requireContext()))
        animal_name.text = args.animal.name
        animal_location.text = args.animal.location
        animal_lifespan.text = args.animal.lifeSpan
        animal_diet.text = args.animal.diet
    }

}