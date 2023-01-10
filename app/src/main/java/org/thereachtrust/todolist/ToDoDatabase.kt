package org.thereachtrust.todolist

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Items::class, Groups::class], version = 1)
abstract class ToDoDatabase : RoomDatabase()
{
    abstract fun todoDao(): ToDoDao
    companion object{
        var instance: ToDoDatabase?= null

        fun getDatabase(context: Context): ToDoDatabase?
        {
           if( instance == null)
           {
               synchronized(ToDoDatabase::class)
               {
                   instance = Room.databaseBuilder(context.applicationContext,
                   ToDoDatabase::class.java,
                   "temporary_name").build()
               }
           }
            return instance
        }
    }
}