package com.example.bibli;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;


import java.util.ArrayList;
import java.util.List;

public class BottomActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private FragmentPagerAdapter _fragmentPagerAdapter;
    private int currentFragment = 0;
    private final List<Fragment> _fragments = new ArrayList<Fragment>();
    public static final int FRAGMENT_ONE = 0;
    public static final int FRAGMENT_TWO = 1;
    public static final int FRAGMENT_THREE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom);
        _fragments.add(FRAGMENT_ONE, new SearchFragment());
        _fragments.add(FRAGMENT_TWO, new NewsFragment());
        _fragments.add(FRAGMENT_THREE, new ProfileFragment());
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(this);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        //AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications).build();
        //NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(navView, navController);
//        initFragment();
        //loadFragment(new SearchFragment());
    }

    @Override
    protected void onStart() {
        super.onStart();
        initFragment();
    }

    public boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_content, fragment)
//                    .replace(R.id.fragment_container, fragment)
                    .commit();

            return true;
        }
        return false;
    }
    private void initFragment(){
        for(Fragment fragment :_fragments){
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_content, fragment)
                    .hide(fragment)
                    .commit();
        }
        getSupportFragmentManager().beginTransaction().show(_fragments.get(FRAGMENT_ONE))
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        getSupportFragmentManager().beginTransaction().hide(_fragments.get(currentFragment))
                .commit();
        switch (item.getItemId()) {
            case R.id.navigation_home:
                getSupportFragmentManager().beginTransaction().show(_fragments.get(FRAGMENT_ONE));
                currentFragment = FRAGMENT_ONE;
                break;
            case R.id.navigation_dashboard:
                getSupportFragmentManager().beginTransaction().show(_fragments.get(FRAGMENT_TWO))
                        .commit();
                currentFragment = FRAGMENT_TWO;
                break;
            case R.id.navigation_notifications:
                getSupportFragmentManager().beginTransaction().show(_fragments.get(FRAGMENT_THREE))
                        .commit();
                currentFragment = FRAGMENT_THREE;
                break;
        }
        return loadFragment(fragment);
    }
}
