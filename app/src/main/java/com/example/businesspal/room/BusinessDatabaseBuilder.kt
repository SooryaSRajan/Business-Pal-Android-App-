package com.example.businesspal.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.businesspal.model.BusinessDataModel

@Database(entities = [BusinessDataModel::class], version = 1)
abstract class BusinessDatabase : RoomDatabase() {
    abstract fun userDao(): BusinessDataDao
}

class BusinessDatabaseBuilder {

    companion object {
        var db: BusinessDatabase? = null

        fun getInstance(context: Context): BusinessDatabase? {

            if (db == null) {
                db = Room.databaseBuilder(
                    context,
                    BusinessDatabase::class.java, "businessDataTable"
                ).build()
            }

            return db


        }
    }


}