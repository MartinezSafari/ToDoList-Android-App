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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.security.KeyException

class ItemsActivity : AppCompatActivity(), OnItemClickListener
{
    lateinit var groupWithItems: GroupWithItems
    var itemsAdapter: ItemsAdapter?= null

    override fun itemClicked(index: Int)
    {
        val item= groupWithItems.items[index]
        item.completed= !(item.completed)
        CoroutineScope(Dispatchers.IO).launch{
            AppData.db.todoDao().updateItem(groupWithItems.group.name,
                                            item.name, item.completed)

        }

        itemsAdapter!!.notifyDataSetChanged()
    }



    override fun itemLongClicked(index: Int)
    {
        val groupName= groupWithItems.group.name
        val itemName= groupWithItems.items[index].name

        CoroutineScope(Dispatchers.IO).launch {
            AppData.db.todoDao().deleteItem(groupName, itemName)
        }


        groupWithItems.items.removeAt(index)
        itemsAdapter!!.notifyItemRemoved(index)
    }


    override fun onCreate(savedInstanceState: Bundle?)
     {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.items)

        var selectedIndex= intent.getIntExtra("groupIndex", 0 )
         groupWithItems= AppData.groups[selectedIndex]

         toolBarTitle.text= groupWithItems.group.name

         itemsRecycleView.layoutManager= LinearLayoutManager(this)
         itemsAdapter= ItemsAdapter(groupWithItems,this )
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
                     val item= Items(name, groupWithItems.group.name,false)
                     groupWithItems.items.add(item)

                     CoroutineScope(Dispatchers.IO).launch {
                         AppData.db.todoDao().insertItem(item)
                     }

                     itemsAdapter!!.notifyItemInserted(groupWithItems.items.count())
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