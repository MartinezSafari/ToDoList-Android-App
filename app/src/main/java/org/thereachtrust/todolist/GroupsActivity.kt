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
import java.util.zip.Inflater

class GroupsActivity : AppCompatActivity(), OnGroupClickListener
{
    private var groupsAdapter: GroupsAdapter?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.groups)

        groupsRecycleView.layoutManager= LinearLayoutManager(this)

        AppData.initialize()
        groupsAdapter = GroupsAdapter (AppData.groups, this)
            groupsRecycleView.adapter= groupsAdapter
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
        startActivity(intent)
    }

    override fun groupLongClicked(index: Int) {
        TODO("Not yet implemented")
        //Delete group
    }
}


