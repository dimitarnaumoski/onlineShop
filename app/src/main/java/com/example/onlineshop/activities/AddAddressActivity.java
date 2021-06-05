package com.example.onlineshop.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.onlineshop.R;
import com.example.onlineshop.dbroom.Address;
import com.example.onlineshop.dbroom.AppDatabase;

public class AddAddressActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

        toolbar = findViewById(R.id.add_address_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final EditText fullName = findViewById(R.id.ad_name);
        final EditText address = findViewById(R.id.ad_address);
        final EditText city = findViewById(R.id.ad_city);
        final EditText phoneNumber = findViewById(R.id.ad_phone);
        Button addAddressBtn = findViewById(R.id.ad_add_address);

        addAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNewAddres(fullName.getText().toString(), address.getText().toString(), city.getText().toString(), phoneNumber.getText().toString());
            }
        });
    }

    private void saveNewAddres(String fullName, String address, String city, String phoneNumber) {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());

        Address user = new Address();
        user.fullName = fullName;
        user.address = address;
        user.city = city;
        user.phoneNumber = phoneNumber;
        db.addressDao().insertAddress(user);

        finish();
    }
}