package ua.nure.marketmap;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ua.nure.marketmap.Model.*;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

    public static final String AUTH = "ua.nure.marketmap.AuthorizationActivity";

    private GetOutletsTask mGetOutlet;
    private GoogleMap mMap;
    private List<Outlet> mOutlets;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager() .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_search);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_favourites) {
            Intent intent = new Intent(this, AuthorizationActivity.class);
            intent.putExtra(AUTH, "");
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        CameraPosition camPos = mMap.getCameraPosition();
        LatLng currentCity  = new LatLng(49.995, 36.270);

        //mMap.addMarker(new MarkerOptions().position(currentCity).title("Marker Kharkiv"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentCity, 10.4F));

        drawOutlets();
/*
        Button mBtnPlus = findViewById(R.id.btn_plus);
        mBtnPlus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CameraPosition mCamPos = mMap.getCameraPosition();
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mCamPos.target, mCamPos.zoom + 0.5F));
            }
        });

        Button mBtnMinus = findViewById(R.id.btn_minus);
        mBtnMinus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CameraPosition mCamPos = mMap.getCameraPosition();
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mCamPos.target, mCamPos.zoom - 0.5F));
            }
        });

        mMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
                ((TextView)findViewById(R.id.caption)).setText(String.valueOf(mMap.getCameraPosition().zoom));
            }
        });
        */
    }

    private void drawOutlets() {
        List<Outlet> outlets = getOutlets();
        for(Outlet outlet : outlets) {
            PolygonOptions polygonOptions = new PolygonOptions();
            for(int i = 0; i < outlet.getPointsCount(); i++) {
                polygonOptions.add(outlet.getPoint(i));
            }
            int currentColor = outlet.getColor();
            polygonOptions.fillColor(currentColor);
            polygonOptions.strokeColor(currentColor);
            polygonOptions.clickable(true);
            polygonOptions.visible(true);
            mMap.addPolygon(polygonOptions);
        }
    }

    public List<Outlet> getOutlets() {
        ColorCollection.shared = 0xFF3F51B5;

        List<Outlet> outlets = new ArrayList<Outlet>();
        outlets.add(new Outlet(
                "У Васи",
                "пр. Тракторостроителей 120",
                Arrays.asList(
                        new LatLng(50.010566, 36.350757),
                        new LatLng(50.010593, 36.350790),
                        new LatLng(50.010573, 36.350832),
                        new LatLng(50.010544, 36.350799)
                ),
                new ArrayList<Category>(),
                new ArrayList<Comment>()
        ));

        return outlets;

        //mGetOutlet = new GetOutletsTask();
        //mGetOutlet.execute((Void) null);
        //return null;
    }

    public class GetOutletsTask extends AsyncTask<Void, Void, Boolean> {
        GetOutletsTask() { }

        @Override
        protected Boolean doInBackground(Void... params) {

            // TODO: attempt to get
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mGetOutlet = null;

            if (success) {
                finish();
            } else {
                //mMap.setError(getStri);
            }
        }

        @Override
        protected void onCancelled() {
            mGetOutlet = null;
        }
    }
}
