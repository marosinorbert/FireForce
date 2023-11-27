package com.example.elso_teszt.bottomnav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import com.example.elso_teszt.R;
import com.example.elso_teszt.adapter.AdapterViewPager;
import com.example.elso_teszt.fragment.MapFragment;
import com.example.elso_teszt.fragment.MenuFragment;
import com.example.elso_teszt.fragment.UserFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback{
    private GoogleMap mMap;
    ViewPager2 pagerMain;
    ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        pagerMain = findViewById(R.id.pagerMain);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        fragmentArrayList.add(new MapFragment());
        fragmentArrayList.add(new MenuFragment());
        fragmentArrayList.add(new UserFragment());

        AdapterViewPager adapterViewPager = new AdapterViewPager(this, fragmentArrayList);

        pagerMain.setAdapter(adapterViewPager);
        pagerMain.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position){
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
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng location = new LatLng(46.061906, 25.336797);
        mMap.addMarker(new MarkerOptions().position(location));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(location));

        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
    }
}