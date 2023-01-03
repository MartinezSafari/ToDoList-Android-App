package org.thereachtrust.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.items.*

class ItemsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?)
     {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.items)

        var selectedIndex= intent.getIntExtra("groupIndex", 0 )
        var thisGroup= AppData.groups[selectedIndex]

         toolBarTitle.text= thisGroup.name

         itemsRecycleView.layoutManager= LinearLayoutManager(this)
         var itemsAdapter= ItemsAdapter(thisGroup)
         itemsRecycleView.adapter= itemsAdapter

         setSupportActionBar(toolbar)
         supportActionBar!!.setDisplayHomeAsUpEnabled(true)
         supportActionBar!!.setDisplayShowTitleEnabled(false)
     }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}