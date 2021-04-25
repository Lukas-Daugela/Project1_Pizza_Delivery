package com.example.PizzaApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.example2.R;

public class CityForOrder extends AppCompatActivity {

    Button vilnius, kaunas, klaipeda, siauliai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_for_order);

        kaunas = findViewById(R.id.kaunasBtn);

        kaunas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPizzaPlaces();
            }
        });

        /*
        // For future development, if you want to add more cities

        vilnius = findViewById(R.id.vilniusBtn);
        klaipeda = findViewById(R.id.klaipedaBtn);
        siauliai = findViewById(R.id.siauliaiBtn);

        vilnius.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPizzaPlaces();
            }
        });

        klaipeda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPizzaPlaces();
            }
        });

        siauliai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPizzaPlaces();
            }
        });
*/
    }

    private void startPizzaPlaces() {
        Intent startPlaces = new Intent(getApplicationContext(), PizzaPlaces.class);
        startActivity(startPlaces);
    }
}