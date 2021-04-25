package com.example.PizzaApp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.example2.R;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class PizzaMeniu extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.pizza_meniu);

        listView = findViewById(R.id.listView);

            ConnectMySql connectMySql = new ConnectMySql();
            try {
                String result = connectMySql.execute("").get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        PizzaListAdapter adapter = new PizzaListAdapter(this, R.layout.pizza_meniu_row, PizzaList.pizzas);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), PizzaDescription.class);
                intent.putExtra("key", position);
                startActivity(intent);
            }
        });

    }

    class PizzaListAdapter extends ArrayAdapter<Pizza>{

        private Context mContext;
        int mResource;

        public PizzaListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Pizza> objects) {
            super(context, resource, objects);
            mContext = context;
            mResource = resource;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            int id = getItem(position).getId();
            String name = getItem(position).getPizzaName();
            String sDesc = getItem(position).getShortDesc();
            String price = getItem(position).getPrice();
            Bitmap image = getItem(position).getImage();

            Pizza pizza = new Pizza(id,name,sDesc,price,image);

            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);

            TextView pizzaName = convertView.findViewById(R.id.textView1);
            TextView pizzaDesc = convertView.findViewById(R.id.textView2);
            ImageView images = convertView.findViewById(R.id.image);
            TextView prices = convertView.findViewById(R.id.textViewKaina);

            pizzaName.setText(name);
            pizzaDesc.setText(sDesc);
            images.setImageBitmap(image);
            prices.setText(price);

            return convertView;
        }
    }
}