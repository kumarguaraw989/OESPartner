package com.example.oespartner.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.appizona.yehiahd.fastsave.FastSave;
import com.example.oespartner.Adapter.SlidingImage_Adapter;
import com.example.oespartner.Fragment.ActionBarActivity;
import com.example.oespartner.Fragment.NavigationDrawerFragment;
import com.example.oespartner.MainActivity;
import com.example.oespartner.model.Data;
import com.example.oespartner.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class HomeActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES= {R.drawable.b,R.drawable.b,R.drawable.c,R.drawable.b};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;
    private DrawerLayout mDrawerLayout;
    TextView textDrawerHeading,txtEmail,visitorgatepass,workgatepass,materialgatepass,authorizedsignatory,partenerperson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle("OES Partner");
        textDrawerHeading=(TextView)findViewById(R.id.textDrawerHeading);
        txtEmail=(TextView)findViewById(R.id.txtEmail);
        visitorgatepass=findViewById(R.id.tv_visitorgatepass);
        workgatepass=findViewById(R.id.tv_workgatepass);
        materialgatepass=findViewById(R.id.tv_Materialgatepass);
        authorizedsignatory=findViewById(R.id.tv_authorisedsignotory);
        partenerperson=findViewById(R.id.tv_partenerperson);
        visitorgatepass.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this,VisitorgatepassActivity.class));
            finish();
        });


        workgatepass.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this,WorkgatepassActivity.class));
            finish();

        });

        materialgatepass.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this,MaterialGatepassActivity.class));
            finish();

        });

        authorizedsignatory.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this,AuthorizedSignatoryActivity.class));
            finish();

        });

        partenerperson.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this,PartnerPersonActivity.class));
            finish();

        });



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
        for (int i = IMAGES.length - 1; i >= 0; i--) {
            Integer image = IMAGES[i];

        }

        init();
    }

    private void init() {
        ImagesArray.addAll(Arrays.asList(IMAGES));

        //CircleIndicator indicator =  getView().findViewById(R.id.indicator);
        ViewPager mPager =findViewById(R.id.pager);


        mPager.setAdapter(new SlidingImage_Adapter(this,HomeActivity.this,ImagesArray));


        CircleIndicator indicator = findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
        // indicator.setRadius(5 * density);

        NUM_PAGES =IMAGES.length;

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

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
