package org.thereachtrust.todolist


import org.junit.Assert.assertEquals
import org.junit.Test

internal class TestCompletionClass {

    @Test
    fun findCompletionTest()
    {
        val items= mutableListOf<Item>(
            Item("Bread", true),
            Item("Milk", false)
        )

        val group= Group("TestGrp", items)
        val result= findCompletion(group)

        assertEquals(result.actionRate, 50f)
        assertEquals(result.completionRate, 50f)

    }
}