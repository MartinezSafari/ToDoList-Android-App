package org.thereachtrust.todolist

import android.app.Activity
import android.hardware.input.InputManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.InputMethod
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.items.*
import java.security.KeyException

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
        thisGroup.items.removeAt(index)
        itemsAdapter!!.notifyItemRemoved(index)
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


         newItemEditText.setOnKeyListener { view, keyCode, event ->
             if (keyCode == KeyEvent.KEYCODE_ENTER)
             {
                 if( event.action== KeyEvent.ACTION_DOWN)
                 {
                     val name: String= newItemEditText.text.toString()
                     val item= Item(name, false)
                     thisGroup.items.add(item)
                     itemsAdapter!!.notifyItemInserted(thisGroup.items.count())
                     newItemEditText.text.clear()

                     val inputManager= getSystemService(Activity.INPUT_METHOD_SERVICE)
                     as InputMethodManager
                     inputManager.hideSoftInputFromWindow(view.windowToken,0)
                 }
             }
             false
         }

     }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left,
            R.anim.slide_out_right)
    }


}