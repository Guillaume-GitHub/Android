package com.example.guillaume.navigationdrawer;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

// for catch click on items
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //FOR DESIGN
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // configure views
        this.configureToolBar();
        this.configureDrawerLayout();
        this.configureNavigationView();
    }

    // call when item are clicking
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // 4 - Handle Navigation Item Click
        int id = menuItem.getItemId();

        switch (id){
            case R.id.activity_main_drawer_news :
                break;
            case R.id.activity_main_drawer_profile:
                break;
            case R.id.activity_main_drawer_settings:
                break;
            default:
                break;
        }

        this.drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    // To close navigation drawer
    @Override
    public void onBackPressed() {
        // 5 - Handle back click to close menu
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


        // ---------------------
        // CONFIGURATION
        // ---------------------

        // 1 - Configure Toolbar
        private void configureToolBar () {
            this.toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
            setSupportActionBar(toolbar);
        }

        // 2 - Configure Drawer Layout
        private void configureDrawerLayout () {
            this.drawerLayout = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();
        }

        // 3 - Configure NavigationView
        private void configureNavigationView() {
            this.navigationView = (NavigationView) findViewById(R.id.activity_main_nav_view);
            navigationView.setNavigationItemSelectedListener(this);
        }
}
