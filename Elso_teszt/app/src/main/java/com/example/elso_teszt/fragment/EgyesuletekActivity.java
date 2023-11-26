package com.example.elso_teszt.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.elso_teszt.R;

public class EgyesuletekActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.egyesuletek);

        Button bejelentkezesGomb = findViewById(R.id.bejelentkezes_gomb);
        bejelentkezesGomb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EgyesuletekActivity.this, MenuFragment.class);
                startActivity(intent);
            }
        });
    }
}
