package org.thereachtrust.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class GroupsAdapter (private val list: List<Group>): RecyclerView.Adapter<GroupsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return GroupsViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: GroupsViewHolder, position: Int) {
        val goup = list[position]
        holder.bind(goup)
    }

    override fun getItemCount(): Int = list.size
}
