package com.example.helloandroid;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.helloandroid.ui.HomeFragment;
import com.example.helloandroid.ui.MoreFragment;
import com.example.helloandroid.ui.MoviesFragment;
import com.example.helloandroid.ui.SeriesFragment;
import com.example.helloandroid.ui.WatchlistFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setOnItemSelectedListener(item -> {
            showTab(item.getItemId());
            return true;
        });
        if (savedInstanceState == null) {
            bottomNav.setSelectedItemId(R.id.nav_home); // shows the Home tab on first launch
        }

        // Back on any other tab goes to Home; Back on Home leaves the app.
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (bottomNav.getSelectedItemId() != R.id.nav_home) {
                    bottomNav.setSelectedItemId(R.id.nav_home);
                } else {
                    finish();
                }
            }
        });
    }

    private void showTab(int menuItemId) {
        getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainer, createFragment(menuItemId))
                .commit();
    }

    private Fragment createFragment(int menuItemId) {
        if (menuItemId == R.id.nav_movies) {
            return new MoviesFragment();
        }
        if (menuItemId == R.id.nav_series) {
            return new SeriesFragment();
        }
        if (menuItemId == R.id.nav_watchlist) {
            return new WatchlistFragment();
        }
        if (menuItemId == R.id.nav_more) {
            return new MoreFragment();
        }
        return new HomeFragment();
    }
}
