package org.thereachtrust.todolist

class AppData {
    companion object dataHolder{
        var groups: MutableList<Group> = mutableListOf()

        fun initialize(){
            val items1 = Item ("bread" ,false)
            val items2 = Item ("sugar" ,false)

            val items3 = Item ("paper" ,false)
            val items4 = Item ("pen" ,false)

            val group1 = Group("Home", mutableListOf(items1, items2))
            val group2 = Group("Office", mutableListOf(items3, items4))

            groups = mutableListOf(group1, group2)
        }
    }
}