package com.example.oespartner.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.drawerlayout.widget.DrawerLayout;

import com.appizona.yehiahd.fastsave.FastSave;
import com.example.oespartner.Fragment.ActionBarActivity;
import com.example.oespartner.Fragment.NavigationDrawerFragment;
import com.example.oespartner.MainActivity;
import com.example.oespartner.Model.Data;
import com.example.oespartner.R;


public class HomeActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;
    private DrawerLayout mDrawerLayout;
    TextView textDrawerHeading,txtEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle("OES Partner");
        textDrawerHeading=(TextView)findViewById(R.id.textDrawerHeading);
        txtEmail=(TextView)findViewById(R.id.txtEmail);
        Data data_model= FastSave.getInstance().getObject("login_data",Data.class);
        data_model.getAdminId();
        data_model.getName();
        data_model.getEmail();
        txtEmail.setText(data_model.getEmail());
        textDrawerHeading.setText(data_model.getName());
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                findViewById(R.id.main_layout));
        mDrawerLayout = findViewById(R.id.main_layout);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            new AlertDialog.Builder(this)
                    .setTitle("Logout")
                    .setMessage("Are you sure you want to exit?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        startActivity(new Intent(HomeActivity.this, MainActivity.class));
                        finish();
                    })
                    .setNegativeButton("No", (dialog, which) -> {
                    })
                    .show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onNavigationDrawerItemSelected(int position) {
     }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, id) -> HomeActivity.this.finish())
                .setNegativeButton("No", (dialog, id) -> dialog.cancel());
        AlertDialog alert = builder.create();
        alert.show();
    }
}
