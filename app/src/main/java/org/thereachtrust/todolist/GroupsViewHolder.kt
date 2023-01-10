package org.thereachtrust.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GroupsViewHolder(inflater: LayoutInflater, parent: ViewGroup):
    RecyclerView.ViewHolder(inflater.inflate(R.layout.groups_row, parent, false)) {
    private var groupNameTextView: TextView? = null
    private var groupCountTextView: TextView? = null

    init {
        groupNameTextView = itemView.findViewById(R.id.groupNameTextView)
        groupCountTextView = itemView.findViewById(R.id.groupCountTextView)

    }
    fun bind(groupWithItems: GroupWithItems) {
        groupNameTextView!!.text= groupWithItems.group.name
        groupCountTextView!!.text= "${groupWithItems.items.count()} items"
    }
}
