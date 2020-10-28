package com.example.example2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class StillNotFinished extends AppCompatActivity {

    TextView pavadinimas, desc;
    ImageView nuotrauka;
    int foto = R.drawable.careerbuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.still_not_finished);

        pavadinimas = findViewById(R.id.textView3);
        desc = findViewById(R.id.textView4);
        nuotrauka = findViewById(R.id.imageView33);

        pavadinimas.setText("Atsiprašome app'sas vis dar kuriamas");
        desc.setText("Ačių už dėmesį !!!");
        nuotrauka.setImageResource(foto);

    }
}