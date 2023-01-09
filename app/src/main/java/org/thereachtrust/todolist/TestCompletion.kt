package org.thereachtrust.todolist

internal fun findCompletion(group: Group): CompletionRate
{
   val totalItem= group.items.size
   val activeItems= group.items.count { !it.completed }
    val completedItem= group.items.count { !it.completed }

    val actionRate= 100* activeItems/totalItem.toFloat()
    val completeRate= 100* completedItem/totalItem.toFloat()

    return CompletionRate(actionRate, completeRate)
}

data class CompletionRate(val actionRate: Float, val completionRate: Float)