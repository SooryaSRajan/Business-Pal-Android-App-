package com.example.businesspal.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "businessDataTable")
data class BusinessDataModel(
    @PrimaryKey var _id: String,
    @ColumnInfo(name = "business_name") var BusinessName: String,
    @ColumnInfo(name = "business_caption") var BusinessCaption: String,
    @ColumnInfo(name = "business_description") var BusinessDescription: String,
    @ColumnInfo(name = "business_location_latitude") var BusinessLocationLatitude: Double,
    @ColumnInfo(name = "business_location_longitude") var BusinessLocationLongitude: Double,
    @ColumnInfo(name = "created_time") var CreatedTime: String,
    @ColumnInfo(name = "email_id") var EmailID: String
) : Serializable