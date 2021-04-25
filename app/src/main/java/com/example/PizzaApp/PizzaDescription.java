package com.example.PizzaApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.example2.R;

import java.util.ArrayList;

public class PizzaDescription extends AppCompatActivity {

    TextView pizzaName, longDesc, price, totalPrice;
    ImageView image;
    Button addPizzaPrice, finishOrder;
    int position;
    public ArrayList<String> pizzaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.pizza_description);

        // Buttons
        addPizzaPrice = findViewById(R.id.btnAddToCart);
        finishOrder = findViewById(R.id.btnFinishOrder);

        position = getIntent().getExtras().getInt("key");

        // TextViews
        pizzaName = findViewById(R.id.textViewDName);
        longDesc = findViewById(R.id.textViewDDesc);
        price = findViewById(R.id.textViewDKaina);
        image = findViewById(R.id.imageViewD);
        totalPrice = findViewById(R.id.textViewPrice);

        pizzaName.setText(PizzaList.pizzas.get(position).getPizzaName());
        longDesc.setText(PizzaList.pizzas.get(position).getLongDesc());
        price.setText(PizzaList.pizzas.get(position).getPrice());
        image.setImageBitmap(PizzaList.pizzas.get(position).getImage());

        totalPrice.setText("0");

        addPizzaPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String defaultPrice = totalPrice.getText().toString();

                double onePizzaPrice = Double.parseDouble(price.getText().toString());
                String onePizzaPriceStr = price.getText().toString();

                double priceNow = Double.parseDouble(totalPrice.getText().toString());

                Orders order = new Orders(pizzaName.getText().toString(), onePizzaPriceStr);
                OrderPizza.orders.add(order);
                OrderPizza.prices.add(onePizzaPriceStr);
                OrderPizza.pizzaLst.add(pizzaName.getText().toString());

                if (defaultPrice.equals("0")) {
                    totalPrice.setText(onePizzaPriceStr);
                }
                if(priceNow > 0){
                    double sum = priceNow + onePizzaPrice;
                    totalPrice.setText(String.valueOf(sum));
                }
            }
        });

        finishOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startOrder = new Intent(getApplicationContext(), OrderPizza.class);
                startActivity(startOrder);
            }
        });

    }

    public ArrayList<String> getPizzaList() {
        return pizzaList;
    }
}