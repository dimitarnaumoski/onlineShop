package com.example.onlineshop.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.provider.Telephony;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.onlineshop.R;
import com.example.onlineshop.adapters.AddressAdapter;
import com.example.onlineshop.dbroom.Address;
import com.example.onlineshop.dbroom.AppDatabase;
import com.example.onlineshop.models.NewProductsModel;
import com.example.onlineshop.models.PopularProductsModel;
import com.example.onlineshop.models.ShowAllModel;

import java.util.List;

public class AddressActivity extends AppCompatActivity {

      Button paymentBtn;
      Toolbar toolbar;

    private AddressAdapter addresListAdapter;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate menu, adding items to the action bar
        getMenuInflater().inflate(R.menu.menu_address_delete, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.delete_address) {
            deleteAddress();
            Toast.makeText(this, R.string.all_address_deleted, Toast.LENGTH_SHORT).show();
        }

        return true;
    }

    private void deleteAddress() {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        db.addressDao().deleteAll();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        toolbar = findViewById(R.id.address_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




        // get data from detailed activity
        Object obj = getIntent().getSerializableExtra("item");


        paymentBtn = findViewById(R.id.payment_btn);
        paymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double amount = 0.0;

                if (obj instanceof NewProductsModel) {
                    NewProductsModel newProductsModel = (NewProductsModel) obj;
                    amount = newProductsModel.getPrice();
                }
                if (obj instanceof PopularProductsModel) {
                    PopularProductsModel popularProductsModel = (PopularProductsModel) obj;
                    amount = popularProductsModel.getPrice();
                }
                if (obj instanceof ShowAllModel) {
                    ShowAllModel showAllModel = (ShowAllModel) obj;
                    amount = showAllModel.getPrice();
                }

                Intent intent = new Intent(AddressActivity.this,PaymentActivity.class);
                intent.putExtra("amount", amount);
                startActivity(intent);
            }
        });

       Button addNewAddressButton = findViewById(R.id.add_address_btn);
       addNewAddressButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                startActivityForResult(new Intent(AddressActivity.this, AddAddressActivity.class), 100);
           }
       });

       initRecyclerView();

       loadAddressList();
    }

    private void initRecyclerView() {
         RecyclerView recyclerView = (RecyclerView) findViewById(R.id.address_recycler);
         recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        addresListAdapter = new AddressAdapter(this);
        recyclerView.setAdapter(addresListAdapter);
    }

    private void loadAddressList() {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        List<Address> addressList = db.addressDao().getAllAddreses();
        addresListAdapter.setAddressList(addressList);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 100) {
            loadAddressList();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


}