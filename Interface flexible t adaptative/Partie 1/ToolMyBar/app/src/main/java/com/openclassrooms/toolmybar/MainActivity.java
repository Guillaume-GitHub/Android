package com.openclassrooms.toolmybar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configureToolbar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action menu
        switch (item.getItemId()){
            case(R.id.app_bar_params):
                Toast.makeText(this, "Params", Toast.LENGTH_SHORT).show();
                return true;
            case(R.id.app_bar_search):
                Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void configureToolbar(){
        //get toolbar view in activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //set the toolbar
        setSupportActionBar(toolbar);
    }
}
