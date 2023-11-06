package com.example.elso_teszt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.elso_teszt.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    private final int MAP_ID = R.id.map;
    private final int MENU_ID = R.id.menu;
    private final int USER_ID = R.id.user;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new MenuFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            int itemId = item.getItemId();
            switch (itemId) {

                case MAP_ID:
                    replaceFragment(new MapFragment());
                    break;
                case MENU_ID:
                    replaceFragment(new MenuFragment());
                    break;
                case USER_ID:
                    replaceFragment(new UserFragment());
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + itemId);
            }

            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();

    }
}