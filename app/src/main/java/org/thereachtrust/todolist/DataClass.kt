package org.thereachtrust.todolist


data class Item(val name: String, var completed: Boolean)

data class Group(val name: String, var items: MutableList<Item>)

