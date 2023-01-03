package org.thereachtrust.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.items.*

class ItemsActivity : AppCompatActivity(), OnItemClickListener
{
    lateinit var thisGroup: Group
    var itemsAdapter: ItemsAdapter?= null

    override fun itemClicked(index: Int)
    {
        thisGroup.items[index].completed= !thisGroup.items[index].completed
        itemsAdapter!!.notifyDataSetChanged()
    }

    override fun itemLongClicked(index: Int) {
        TODO("Not yet implemented")
    }


    override fun onCreate(savedInstanceState: Bundle?)
     {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.items)

        var selectedIndex= intent.getIntExtra("groupIndex", 0 )
        thisGroup= AppData.groups[selectedIndex]

         toolBarTitle.text= thisGroup.name

         itemsRecycleView.layoutManager= LinearLayoutManager(this)
         itemsAdapter= ItemsAdapter(thisGroup,this )
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