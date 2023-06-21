package com.example.myapplication.ui.place


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.CoroutineContext
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.logic.network.SunnyWeatherNetwork

import androidx.recyclerview.widget.RecyclerView


class PlaceFragment:Fragment() {

    val viewModel by lazy { ViewModelProviders.of(this).get(PlaceViewModel::class.java) }

    private lateinit var adapter: PlaceAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_place, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val layoutManager = LinearLayoutManager(activity)


        val recyclerView:RecyclerView?= view?.findViewById(R.id.recyclerView)
        val searchPlaceEdit:EditText?=view?.findViewById(R.id.searchPlaceEdit)
        val bgImageView:ImageView?=view?.findViewById(R.id.bgImageView)

        if (recyclerView != null) {
            recyclerView.layoutManager = layoutManager
        }
        adapter = PlaceAdapter(this, viewModel.placeList)
        if (recyclerView != null) {
            recyclerView.adapter = adapter
        }
        searchPlaceEdit?.addTextChangedListener { editable ->
            val content = editable.toString()

            if (content.isNotEmpty()) {
                viewModel.searchPlaces(content)
            } else {
                if (recyclerView != null) {
                    recyclerView.visibility = View.GONE
                }
                if (bgImageView != null) {
                    bgImageView.visibility = View.VISIBLE
                }
                viewModel.placeList.clear()
                adapter.notifyDataSetChanged()
            }
        }
        viewModel.placeLiveData.observe(this.viewLifecycleOwner, Observer{ result ->
            val places = result.getOrNull()
            if (places != null) {
                if (recyclerView != null) {
                    recyclerView.visibility = View.VISIBLE
                }
                if (bgImageView != null) {
                    bgImageView.visibility = View.GONE
                }
                viewModel.placeList.clear()
                viewModel.placeList.addAll(places)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(activity, "未能查询到任何地点", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })
    }
}