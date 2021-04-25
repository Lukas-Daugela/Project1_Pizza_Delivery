package com.example.PizzaApp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.example.example2.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class infoForCourier extends AppCompatActivity {

    public static ArrayList<OrderForCourier> courierOrders = new ArrayList<OrderForCourier>();

    public static ArrayList<PizzaPlaceForOrders> groupList = new ArrayList<PizzaPlaceForOrders>();
    List<String> orderList;
    Map<PizzaPlaceForOrders, OrderForCourier> order;
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_for_courier);

        CourierOrderMySql courierOrderMySql = new CourierOrderMySql();
        try {
            String result = courierOrderMySql.execute("").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        createOrder();
        expandableListView = findViewById(R.id.cities);
        expandableListAdapter = new MyExpandableListAdapter(this, order, groupList);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int lastExpandedPosition = -1;
            @Override
            public void onGroupExpand(int i) {
                if(lastExpandedPosition != -1 && i != lastExpandedPosition){
                    expandableListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = i;
            }
        });
    }

    private void createOrder() {
        order = new HashMap<PizzaPlaceForOrders, OrderForCourier>();

        for(int i = 0; i <= groupList.size(); i++){
            order.put(groupList.get(i), courierOrders.get(i));
        }
    }
}