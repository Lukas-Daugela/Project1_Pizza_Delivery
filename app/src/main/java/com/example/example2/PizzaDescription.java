package com.example.example2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.example2.OrderPizza.orders;
import static com.example.example2.OrderPizza.prices;
import static com.example.example2.PizzaList.pizzas;

public class PizzaDescription extends AppCompatActivity {

    TextView pizzaName, longDesc, price, totalPrice;
    ImageView image;
    Button addPizzaPrice, finishOrder;
    int position;

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

        pizzaName.setText(pizzas.get(position).getPizzaName());
        longDesc.setText(pizzas.get(position).getLongDesc());
        price.setText(pizzas.get(position).getPrice());
        image.setImageBitmap(pizzas.get(position).getImage());

        totalPrice.setText("0");

        addPizzaPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String defaultPrice = totalPrice.getText().toString();

                double onePizzaPrice = Double.parseDouble(price.getText().toString());
                String onePizzaPriceStr = price.getText().toString();

                double priceNow = Double.parseDouble(totalPrice.getText().toString());

                Orders order = new Orders(pizzaName.getText().toString(), onePizzaPriceStr);
                orders.add(order);
                prices.add(onePizzaPriceStr);

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


}