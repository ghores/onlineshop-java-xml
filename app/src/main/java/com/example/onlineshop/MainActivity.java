package com.example.onlineshop;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import com.example.onlineshop.fragments.BasketFragment;
import com.example.onlineshop.fragments.BlogFragment;
import com.example.onlineshop.fragments.HomeFragment;
import com.example.onlineshop.fragments.LoginFragment;
import com.example.onlineshop.fragments.ProductsFragment;
import com.example.onlineshop.handlers.CardDBHandler;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mainDrawer;
    private MaterialToolbar topAppBar;
    private BottomNavigationView bottomNav;
    private FrameLayout mainFrame;
    private NavigationView navigationView;
    private boolean doubleBackToExitPressedOnce = false;

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
                int itemId = item.getItemId();
                if (itemId == R.id.home) {
                    transaction.replace(R.id.mainFrame, new HomeFragment(MainActivity.this));
                } else if (itemId == R.id.products) {
                    transaction.replace(R.id.mainFrame, new ProductsFragment(MainActivity.this));
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

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                @SuppressLint("CommitTransaction") FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                int itemId = item.getItemId();
                if (itemId == R.id.login) {
                    transaction.replace(R.id.mainFrame, new LoginFragment(MainActivity.this));
                } else if (itemId == R.id.register) {
                    // transaction.replace(R.id.mainFrame, new RegisterFragment(MainActivity.this));
                } else if (itemId == R.id.about) {
                    //transaction.replace(R.id.mainFrame, new AboutFragment(MainActivity.this));
                }
                transaction.commit();
                mainDrawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    private void bindViews() {
        mainDrawer = findViewById(R.id.mainDrawer);
        topAppBar = findViewById(R.id.topAppBAr);
        bottomNav = findViewById(R.id.bottomNav);
        mainFrame = findViewById(R.id.mainFrame);
        navigationView = findViewById(R.id.navigationView);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Snackbar snackbar = Snackbar.make(mainFrame, R.string.twice_back, Snackbar.LENGTH_LONG);
        snackbar.getView().setTranslationY(-135);
        snackbar.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        CardDBHandler cardDBHandler = new CardDBHandler(this);
        int basketCount = cardDBHandler.getAllBasketDataCount();
        BadgeDrawable badge = bottomNav.getOrCreateBadge(R.id.basket);
        if (basketCount > 0) {
            badge.setVisible(true);
            badge.setNumber(basketCount);
        } else {
            badge.setVisible(false);
            badge.setNumber(0);
        }
    }
}










