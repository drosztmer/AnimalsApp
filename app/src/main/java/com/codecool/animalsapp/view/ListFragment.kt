package com.codecool.animalsapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.codecool.animalsapp.R
import com.codecool.animalsapp.model.Animal
import com.codecool.animalsapp.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private val listAdapter = AnimalListAdapter(arrayListOf())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        animal_list.apply {
            layoutManager = GridLayoutManager(requireActivity(), resources.getInteger(R.integer.column_count))
            adapter = listAdapter
        }

        observeViewModel()

        refresh_layout.setOnRefreshListener {
            animal_list.visibility = View.GONE
            list_error.visibility = View.GONE
            loading_view.visibility = View.VISIBLE
            viewModel.hardRefresh()
            refresh_layout.isRefreshing = false
        }
    }

    private fun observeViewModel() {
        viewModel.animals.observe(viewLifecycleOwner, { list ->
            list?.let {
                animal_list.visibility = View.VISIBLE
                listAdapter.updateAnimalList(it)
            }
        })
        viewModel.loading.observe(viewLifecycleOwner, { isLoading ->
            loading_view.visibility = if (isLoading) View.VISIBLE else View.GONE
            if (isLoading) {
                list_error.visibility = View.GONE
                animal_list.visibility = View.GONE
            }
        })
        viewModel.loadError.observe(viewLifecycleOwner, { isError ->
            list_error.visibility = if (isError) View.VISIBLE else View.GONE
        })
    }

}