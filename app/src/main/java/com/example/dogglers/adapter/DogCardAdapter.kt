/*
* Copyright (C) 2021 The Android Open Source Project.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.example.dogglers.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dogglers.R
import com.example.dogglers.const.Layout
import com.example.dogglers.data.DataSource
import com.example.dogglers.model.Dog

/**
 * Adapter to inflate the appropriate list item layout and populate the view with information
 * from the appropriate data source
 */
class DogCardAdapter(
    private val context: Context?,
    private val layout: Int
): RecyclerView.Adapter<DogCardAdapter.DogCardViewHolder>() {

    val list = DataSource.dogs

    /**
     * Initialize view elements
     */
    class DogCardViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val dogsImage: ImageView = view.findViewById(R.id.dogs_image)
        val dogsName: TextView = view.findViewById(R.id.dogs_name)
        val dogsAge: TextView = view.findViewById(R.id.dogs_age)
        val dogsHobby: TextView = view.findViewById(R.id.dogs_hobby)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogCardViewHolder {
        var layout_int: Int? = null
        if (layout ==  Layout.HORIZONTAL || layout == Layout.VERTICAL) {
            layout_int = R.layout.vertical_horizontal_list_item
        }else if (layout == Layout.GRID) {
            layout_int = R.layout.grid_list_item
        }

        val item = LayoutInflater.from(parent.context).inflate(layout_int!!,parent,false)
        return DogCardViewHolder(item)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: DogCardViewHolder, position: Int) {
        val data = list[position]

        holder.dogsImage.setImageResource(data.imageResourceId)
        holder.dogsName.text = data.name

        val resources = context?.resources
        holder.dogsAge.text = resources!!.getString(R.string.dog_age, data.age)
        holder.dogsHobby.text = resources!!.getString(R.string.dog_hobbies,data.hobbies)

    }
}
