package com.example.guillaume.navigationdrawer.Controllers.Activities;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.guillaume.navigationdrawer.Controllers.Fragments.NewsFragment;
import com.example.guillaume.navigationdrawer.Controllers.Fragments.ParamsFragment;
import com.example.guillaume.navigationdrawer.Controllers.Fragments.ProfileFragment;
import com.example.guillaume.navigationdrawer.R;

// for catch click on items
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final String TAG = "NavigationDrawer";
    //FOR DESIGN
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    //FOR FRAGMENT
    // Declare fragment handled by Navigation drawer
    private Fragment newsFragment;
    private Fragment paramsFragment;
    private Fragment profileFragment;

    //FOR DATA
    // Identify each fragments with a number
    private static final int FRAGMENT_NEWS = 0;
    private static final int FRAGMENT_PARAMS = 1;
    private static final int FRAGMENT_PROFILE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // configure views
        this.configureToolBar();
        this.configureDrawerLayout();
        this.configureNavigationView();

        showFirstFragment();

    }

    // call when item are clicking
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // 4 - Handle Navigation Item Click
        int id = menuItem.getItemId();

        switch (id){
            case R.id.activity_main_drawer_news :
                this.showFragment(FRAGMENT_NEWS);
                Log.d(TAG, "onNavigationItemSelected: 0");
                break;
            case R.id.activity_main_drawer_profile:
                this.showFragment(FRAGMENT_PROFILE);
                Log.d(TAG, "onNavigationItemSelected: 1");
                break;
            case R.id.activity_main_drawer_settings:
                Log.d(TAG, "onNavigationItemSelected: 2");
                this.showFragment(FRAGMENT_PARAMS);
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

        //--------
        //FRAGMENT
        //--------

        private void showFirstFragment(){
            Fragment visibleFragment = getSupportFragmentManager().findFragmentById(R.id.activity_main_frame_layout);
            if (visibleFragment == null){
                //Show a default Fragment (NewsFragment)
                this.showFragment(FRAGMENT_NEWS);
                // Mark as selected in menu
                this.navigationView.getMenu().getItem(0).setChecked(true);
            }
        }


        private void showFragment(int fragmentIdentifierTag){
            switch (fragmentIdentifierTag){
                case FRAGMENT_NEWS:
                    this.showNewsFragment();
                    Log.d(TAG, "showFragment: news");
                    break;
                case FRAGMENT_PARAMS:
                    this.showParamsFragment();
                    Log.d(TAG, "showFragment: params");
                    break;
                case FRAGMENT_PROFILE:
                    this.showProfileFragment();
                    Log.d(TAG, "showFragment: profile");
                    break;
            }
        }

        // Generic methods to show fragment in mainActivity FrameLayout
        private void startTransactionFragment(Fragment frag){
            if(!frag.isVisible()){
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.activity_main_frame_layout,frag)
                        .commit();
            }
        }


        // Create each fragment and show it
        private void showNewsFragment(){

            if (this.newsFragment == null) {
                newsFragment = NewsFragment.newInstance();
                this.startTransactionFragment(this.newsFragment);
            }
        }

        private void showParamsFragment(){
            if (this.paramsFragment == null) {
                paramsFragment = ParamsFragment.newInstance();
                this.startTransactionFragment(this.paramsFragment);
            }
        }

        private void showProfileFragment(){
            if (this.profileFragment == null) {
                profileFragment = ProfileFragment.newInstance();
                this.startTransactionFragment(this.profileFragment);
            }
        }
}
