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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class PizzaPlaces extends AppCompatActivity {

    ListView listView;
    String name[] = {"American pizza", "Express pizza", "Fresco pizza", "Chilli pizza", "Charlie pizza"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pizza_places);
        getSupportActionBar().hide();

        listView = findViewById(R.id.listView);

        Adapteris adapter = new Adapteris(this, name);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent startConnection = new Intent(getApplicationContext(), PizzaMeniu.class);
                startActivity(startConnection);
            }
        });
    }

    private class Adapteris extends ArrayAdapter<String> {

        Context context;
        String rTitle[];

        Adapteris (Context c, String title[]){
            super(c, R.layout.naujas_row, R.id.textView1, title);
            this.context = c;
            this.rTitle = title;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.naujas_row, parent, false);
            TextView myTitle = row.findViewById(R.id.textView1);

            myTitle.setText(rTitle[position]);

            return row;
        }
    }
}