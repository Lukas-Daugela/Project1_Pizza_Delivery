package com.example.example2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderPizza extends AppCompatActivity {

    public static ArrayList<Orders> orders = new ArrayList<Orders>();
    public static ArrayList<String> prices = new ArrayList<String>();
    ListView pizzaList;
    TextView check;
    Button getPizza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.order_pizza);

        pizzaList = findViewById(R.id.listViewCheck);
        check = findViewById(R.id.textView2);
        getPizza = findViewById(R.id.btnGetPizzas);

        OrderListAdapter adapter = new OrderListAdapter(this, R.layout.order_row, orders);
        pizzaList.setAdapter(adapter);

        check.setText("Total price: " + orderPriceSum() + "€");

        getPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startMap = new Intent(getApplicationContext(), StillNotFinished.class);
                startActivity(startMap);
            }
        });

    }

    class OrderListAdapter extends ArrayAdapter<Orders> {

        private Context mContext;
        int mResource;

        public OrderListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Orders> objects) {
            super(context, resource, objects);
            mContext = context;
            mResource = resource;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            String name = getItem(position).getPizzaName();
            String price = getItem(position).getPrice();

            Orders orders = new Orders(name, price);

            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);

            TextView pizzaName = (TextView) convertView.findViewById(R.id.textView1);
            TextView prices = (TextView) convertView.findViewById(R.id.textView12);

            pizzaName.setText(name);
            prices.setText(price + "€");

            return convertView;
        }
    }

    public String orderPriceSum() {

        double[] doubleList = new double[prices.size()];
        double sum = 0;
        for (int i = 0; i < orders.size(); ++i) {
            doubleList[i] = Double.parseDouble((prices.get(i)));
            sum += doubleList[i];
        }
        String sumStr = String.valueOf(sum);

        return sumStr;
    }

}