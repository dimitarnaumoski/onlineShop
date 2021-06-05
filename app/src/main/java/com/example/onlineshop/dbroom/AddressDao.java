package com.example.onlineshop.dbroom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AddressDao {

    @Query("SELECT * FROM Address")
    List<Address> getAllAddreses();

    @Query("DELETE FROM Address")
    void deleteAll();

    @Insert
    void insertAddress(Address... addresses);

    @Delete
    void delete(Address address);

}
