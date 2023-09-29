package com.example.onlineshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.onlineshop.fragments.BasketFragment;
import com.example.onlineshop.fragments.BlogFragment;
import com.example.onlineshop.fragments.HomeFragment;
import com.example.onlineshop.fragments.ProductFragment;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mainDrawer;
    private MaterialToolbar topAppBar;
    private BottomNavigationView bottomNav;
    private FrameLayout mainFrame;

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

        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                /*switch (item.getItemId()) {
                    case R.id.home:
                        transaction.replace(R.id.mainFrame, new HomeFragment(MainActivity.this));
                        break;
                    case R.id.products:
                        transaction.replace(R.id.mainFrame, new ProductFragment(MainActivity.this));
                        break;
                    case R.id.blog:
                        transaction.replace(R.id.mainFrame, new BlogFragment(MainActivity.this));
                        break;
                    case R.id.basket:
                        transaction.replace(R.id.mainFrame, new BasketFragment(MainActivity.this));
                        break;
                }*/
                int itemId = item.getItemId();
                if (itemId == R.id.home) {
                    transaction.replace(R.id.mainFrame, new HomeFragment(MainActivity.this));
                } else if (itemId == R.id.products) {
                    transaction.replace(R.id.mainFrame, new ProductFragment(MainActivity.this));
                } else if (itemId == R.id.blog) {
                    transaction.replace(R.id.mainFrame, new BlogFragment(MainActivity.this));
                } else if (itemId == R.id.basket) {
                    transaction.replace(R.id.mainFrame, new BasketFragment(MainActivity.this));
                }

                //transaction.addToBackStack(null);
                transaction.commit();
                return true;
            }
        });
        bottomNav.setSelectedItemId(R.id.home);
    }

    private void bindViews() {
        mainDrawer = findViewById(R.id.mainDrawer);
        topAppBar = findViewById(R.id.topAppBAr);
        bottomNav = findViewById(R.id.bottomNav);
        mainFrame = findViewById(R.id.mainFrame);
    }
}