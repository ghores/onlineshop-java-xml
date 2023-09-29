package com.example.onlineshop;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mainDrawer;
    private MaterialToolbar topAppBar;
    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        bindViews();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                mainDrawer,
                topAppBar,
                R.string.open,
                R.string.close
        );
        mainDrawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void bindViews() {
        mainDrawer = findViewById(R.id.mainDrawer);
        topAppBar = findViewById(R.id.topAppBAr);
        bottomNav = findViewById(R.id.bottomNav);
    }
}