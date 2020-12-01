package com.codecool.animalsapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codecool.animalsapp.R
import com.codecool.animalsapp.util.getProgressDrawable
import com.codecool.animalsapp.util.loadImage
import com.codecool.animalsapp.model.Animal
import kotlinx.android.synthetic.main.item_animal.view.*

class AnimalListAdapter(private val animalList: ArrayList<Animal>) :
    RecyclerView.Adapter<AnimalListAdapter.AnimalViewHolder>() {

    fun updateAnimalList(newAnimalList: List<Animal>) {
        animalList.clear()
        animalList.addAll(newAnimalList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_animal, parent, false)
        return AnimalViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        holder.itemView.animal_name.text = animalList[position].name
        holder.itemView.animal_image.loadImage(
            animalList[position].imageUrl,
            getProgressDrawable(holder.itemView.context)
        )
    }

    override fun getItemCount() = animalList.size

    class AnimalViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}