package com.example.businesspal.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.businesspal.model.BusinessDataModel

@Dao
interface BusinessDataDao {
    @Query("SELECT * FROM businessDataTable")
    fun getAll(): List<BusinessDataModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<BusinessDataModel>?)

    @Query("DELETE FROM businessDataTable")
    fun deleteAll()

}