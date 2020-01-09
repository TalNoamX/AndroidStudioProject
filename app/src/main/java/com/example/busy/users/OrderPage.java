package com.example.busy.users;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.busy.R;
import com.example.busy.restaurant.OrderForm.OrderForm;
import com.example.busy.restaurant.Rforms.dish_form;
import com.example.busy.users.Uform.Address_form;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OrderPage extends AppCompatActivity implements View.OnClickListener {
    private ListView listview;
    private ArrayList<OrderForm> orders_list;
    private ArrayAdapter<OrderForm> addapter;
    private final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    private final DatabaseReference ref_orders = FirebaseDatabase.getInstance().getReference("Orders");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);

        orders_list = new ArrayList<>();
        listview = findViewById(R.id.ListView_order_page);
        ref_orders.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snep : dataSnapshot.getChildren()) {
                        if (snep.child("client_id").getValue().equals(uid) && snep.child("status").getValue().equals("active")) {
                            String order_num = snep.child("order_num").getValue(String.class);
                            String rest_id = snep.child("rest_id").getValue(String.class);
                            String client_id = snep.child("client_id").getValue(String.class);
                            String status = snep.child("status").getValue(String.class);
                            double total_price = snep.child("total_price").getValue(double.class);
                            Address_form address_data = snep.child("user_address").getValue(Address_form.class);
                            OrderForm order = new OrderForm(order_num, rest_id, client_id, status, address_data);
                            DataSnapshot dishes_orderd = snep.child("dishs_orderd");
                            for (DataSnapshot child_dt : dishes_orderd.getChildren()) {
                                double price = child_dt.child("price").getValue(double.class);
                                String dish_name = child_dt.child("dish_name").getValue(String.class);
                                String dish_discription = child_dt.child("dish_discription").getValue(String.class);
                                dish_form dish = new dish_form(price, dish_name, dish_discription);
                                order.addDish(dish);
                            }
                            orders_list.add(order);
                        }
                    }
                }
                addapter = new ArrayAdapter<OrderForm>(OrderPage.this, android.R.layout.simple_list_item_1, orders_list);
                listview.setAdapter(addapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    //click function on personal settings button
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Orders_history_btn:
                Intent i = new Intent(OrderPage.this, OrderHistoryUser.class);
                startActivity(i);
                break;
        }
    }
}
