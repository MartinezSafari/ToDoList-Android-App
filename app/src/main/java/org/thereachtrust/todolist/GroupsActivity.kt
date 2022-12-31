package org.thereachtrust.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.DecorContentParent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.groups.*
import java.util.zip.Inflater

class GroupsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.groups)

        groupsRecycleView.layoutManager= LinearLayoutManager(this)

        AppData.initialize()
        val groupsAdapter= GroupsAdapter (AppData.groups)
            groupsRecycleView.adapter= groupsAdapter
    }
}


