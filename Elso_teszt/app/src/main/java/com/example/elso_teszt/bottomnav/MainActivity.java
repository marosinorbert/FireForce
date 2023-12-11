package com.example.elso_teszt.bottomnav;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.elso_teszt.R;
import com.example.elso_teszt.adapter.AdapterViewPager;
import com.example.elso_teszt.fragment.MapFragment;
import com.example.elso_teszt.fragment.MenuFragment;
import com.example.elso_teszt.fragment.UserFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private GoogleMap myMap;
    ViewPager2 pagerMain;
    ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pagerMain = findViewById(R.id.pagerMain);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Módosítás: Helyezd át a fragmentArrayList.add() sorokat úgy, hogy a fragment_menu legyen a középső lap.
        fragmentArrayList.add(new MapFragment()); // A fragment_map lesz a baloldali lap.
        fragmentArrayList.add(new MenuFragment()); // A fragment_menu lesz a középső lap.
        fragmentArrayList.add(new UserFragment()); // A fragment_user lesz a jobboldali lap.

        AdapterViewPager adapterViewPager = new AdapterViewPager(this, fragmentArrayList);

        pagerMain.setAdapter(adapterViewPager);
        pagerMain.setCurrentItem(1); // Az alkalmazás megnyitásakor a középső lap lesz az aktuális lap.

        // A kiválasztott elem beállítása
        bottomNavigationView.setSelectedItemId(R.id.menu);

        pagerMain.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        bottomNavigationView.setSelectedItemId(R.id.map);
                        break;
                    case 1:
                        bottomNavigationView.setSelectedItemId(R.id.menu);
                        break;
                    case 2:
                        bottomNavigationView.setSelectedItemId(R.id.user);
                        break;
                }
                super.onPageSelected(position);
            }
        });

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.map) {
                    pagerMain.setCurrentItem(0);
                } else if (itemId == R.id.menu) {
                    pagerMain.setCurrentItem(1);
                } else if (itemId == R.id.user) {
                    pagerMain.setCurrentItem(2);
                }
                return true;
            }
        });
    }

}
