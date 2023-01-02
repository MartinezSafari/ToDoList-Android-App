package org.thereachtrust.todolist

interface OnGroupClickListener{
    fun groupClicked(index: Int)

    fun groupLongClicked(index: Int)
}