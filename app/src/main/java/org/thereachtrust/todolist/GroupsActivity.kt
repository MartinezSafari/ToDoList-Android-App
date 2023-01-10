package org.thereachtrust.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.DecorContentParent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.groups.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.lang.Exception
import java.util.zip.Inflater

class GroupsActivity : AppCompatActivity(), OnGroupClickListener
{
    private var groupsAdapter: GroupsAdapter?= null

    private fun databaseFileExist(): Boolean
    {
        return try {
            File(getDatabasePath(AppData.dbFileName).absolutePath).exists()
        }
        catch (e:Exception){
            false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.groups)

        groupsRecycleView.layoutManager= LinearLayoutManager(this)

        AppData.initialize()
        groupsAdapter = GroupsAdapter (AppData.groups, this)
            groupsRecycleView.adapter= groupsAdapter


        AppData.db= ToDoDatabase.getDatabase(this)!!
        if (databaseFileExist())
        {
            //read content from room
            CoroutineScope(Dispatchers.IO).launch {
                AppData.groups= AppData.db.todoDao().getGroupsWithItems()

                withContext(Dispatchers.Main){
                    groupsAdapter = GroupsAdapter (AppData.groups, this)
                    groupsRecycleView.adapter= groupsAdapter

                }
            }
        }
        else {
            //first time running
            AppData.initialize()
            groupsAdapter = GroupsAdapter(AppData.groups, this)
            groupsRecycleView.adapter = groupsAdapter
            CoroutineScope(Dispatchers.IO).launch {
                for (groupWithItems in AppData.groups)
                {
                    AppData.db.todoDao().insertGroup(groupWithItems.group)

                    for(item in groupWithItems.items)
                    {
                        AppData.db.todoDao().insertItem(item)
                    }
                }

            }
        }
    }

    override fun onResume()
    {
        super.onResume()
        groupsAdapter!!.notifyDataSetChanged()
    }

    fun createNewGroup(v:View)
    {
        val builder = AlertDialog.Builder(this)

         builder.setTitle("New Group")
        builder.setMessage("Please enter a name for yor group")

        val myInput= EditText(this)
        myInput.inputType= InputType.TYPE_CLASS_TEXT
        builder.setView(myInput)

        builder.setPositiveButton("Save"){
            dialogue, which ->
            val groupName: String = myInput.text.toString()
            val newGroup= Group(groupName, mutableListOf())

            AppData.groups.add(newGroup)
            groupsAdapter!!.notifyItemInserted(AppData.groups.count())
        }

        builder.setNegativeButton("Cancel"){
                dialogue, which ->
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()

    }

    override fun groupClicked(index: Int) {
        val intent= Intent(this, ItemsActivity::class.java)

        intent.putExtra("groupIndex", index)

        startActivity(intent)

        overridePendingTransition(R.anim.slide_in_right,
            R.anim.slide_out_left)
    }

    override fun groupLongClicked(index: Int)
    {
        AppData.groups.removeAt(index)
        groupsAdapter!!.notifyItemRemoved(index)
    }
}


