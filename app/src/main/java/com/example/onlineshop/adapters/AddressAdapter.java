package com.example.onlineshop.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.R;
import com.example.onlineshop.dbroom.Address;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.MyViewHolder>{

    private Context context;
    private List<Address> addressList;

    private int lastSelectedPosition = -1;

    public AddressAdapter(Context context) {
        this.context = context;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AddressAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.address_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressAdapter.MyViewHolder holder, int position) {
        holder.addressName.setText(this.addressList.get(position).fullName);
        holder.addressStreet.setText(this.addressList.get(position).address);
        holder.addressCity.setText(this.addressList.get(position).city);
        holder.phoneNumber.setText(this.addressList.get(position).phoneNumber);

        holder.selectionState.setChecked(lastSelectedPosition == position);

    }

    @Override
    public int getItemCount() {
       return this.addressList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView addressName;
        TextView addressStreet;
        TextView addressCity;
        TextView phoneNumber;

        public RadioButton selectionState;


        public MyViewHolder(View view) {
            super(view);
           addressName = view.findViewById(R.id.address_add_name);
           addressStreet = view.findViewById(R.id.address_add_address);
           addressCity = view.findViewById(R.id.address_add_city);
           phoneNumber = view.findViewById(R.id.address_add_number);

           selectionState = view.findViewById(R.id.select_address);

           selectionState.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   lastSelectedPosition = getAdapterPosition();
                   notifyDataSetChanged();
               }
           });
        }
    }

}
