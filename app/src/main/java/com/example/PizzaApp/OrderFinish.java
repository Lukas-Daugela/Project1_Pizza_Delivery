package com.example.PizzaApp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.example2.R;

public class OrderFinish extends AppCompatActivity {

    TextView completedOrderText, desc;
    ImageView courierPhoto;
    int Photo = R.drawable.delivery_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.order_finish);

        completedOrderText = findViewById(R.id.textView3);
        desc = findViewById(R.id.textView4);
        courierPhoto = findViewById(R.id.imageView33);

        completedOrderText.setText("Order was sent to courier");
        desc.setText("Thanks for using our app :)");
        courierPhoto.setImageResource(Photo);

    }
}