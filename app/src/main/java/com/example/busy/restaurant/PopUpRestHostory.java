package com.example.busy.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.busy.R;
import com.example.busy.restaurant.OrderForm.OrderForm;
import com.example.busy.restaurant.Rforms.dish_form;
import com.example.busy.restaurant.update.rest_update;
import com.example.busy.users.Uform.Address_form;

import java.util.ArrayList;

public class PopUpRestHostory extends AppCompatActivity implements View.OnClickListener {

    private TextView all_info;
    ArrayList<String> dishes_str = new ArrayList<>();
    private ArrayList<dish_form> dishes = new ArrayList<>();
    private OrderForm order;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_rest_hostory);
        all_info = findViewById(R.id.PopUpRestHist_info_TV);
        Intent i = getIntent();
        Bundle extras = i.getBundleExtra("extras");
        final String order_num = extras.getString("order_id");
        String rest_id = extras.getString("rest_id");
        String client_id = extras.getString("client_id");
        String status = extras.getString("status");
        String City = extras.getString("City");
        String Street = extras.getString("Street");
        String House_num = extras.getString("House_num");
        String Phone_num = extras.getString("Phone_num");
        Address_form address = new Address_form(City, Street, House_num, Phone_num);
        dishes_str.addAll(extras.getStringArrayList("dishes"));
        order = new OrderForm(order_num, rest_id, client_id, status, address);
        string_to_dishes_array();
        all_info.setText(order.toString());
    }

    private void string_to_dishes_array() {
        for (int j = 0; j < dishes_str.size(); j++) {
            String[] split = dishes_str.get(j).split(" ");
            String name = split[0];
            double price = Double.parseDouble(split[2].split(",")[0]);
            String desc = "";
            if (split.length >= 5) {
                desc = split[4];
            }
            dish_form dish = new dish_form(price, name, desc);
            dishes.add(dish);
        }
        for (int i = 0; i < dishes.size(); i++) {
            order.addDish(dishes.get(i));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pupUpRestHis_btn_close:
                Intent i = new Intent(PopUpRestHostory.this, OrderHistoryRest.class);
                startActivity(i);
                break;
        }
    }
}