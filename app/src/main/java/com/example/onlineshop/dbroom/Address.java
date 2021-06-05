package com.example.onlineshop.dbroom;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Address {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "full_name")
    public String fullName;

    @ColumnInfo(name = "address")
    public String address;

    @ColumnInfo(name = "city")
    public String city;

    @ColumnInfo(name = "phoneNumber")
    public String phoneNumber;

}
