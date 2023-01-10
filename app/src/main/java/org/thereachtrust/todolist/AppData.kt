package org.thereachtrust.todolist

class AppData {
    companion object DataHolder
    {
        var dbFileName= "todo_db"
        lateinit var db: ToDoDatabase

        var groups: MutableList<GroupWithItems> = mutableListOf()

        fun initialize()

        {
            val group1= Groups("Home")
            val item1 = Items ("bread" ,group1.name,false)
            val item2 = Items ("sugar" ,group1.name,false)
            val groupWithItems1= GroupWithItems(group1, mutableListOf(item1, item2))

            val group2= Groups("Office")
            val items3 = Items ("paper" ,group2.name,false)
            val items4 = Items ("pen" ,group2.name,false)
            val groupWithItems2= GroupWithItems(group2, mutableListOf(items3, items4))

            groups = mutableListOf(groupWithItems1, groupWithItems2)
        }
    }
}