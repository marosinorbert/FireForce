package com.example.elso_teszt.fragment;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.elso_teszt.R;

// EgyesuletekActivity
public class EgyesuletekActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.egyesuletek);

        // Példányosítsd a Fragmenteket
        MenuFragment menuFragment = new MenuFragment();
        UserFragment userFragment = new UserFragment();

        // Add hozzá a Fragmenteket az Activity-hez
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, menuFragment, "menu_fragment_tag")
                .add(R.id.fragment_container, userFragment, "user_fragment_tag")
                .commit();
    }
}

