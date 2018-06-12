package ua.nure.marketmap;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;

import java.util.ArrayList;
import java.util.List;

import ua.nure.marketmap.Controller.DBHelper;
import ua.nure.marketmap.Model.CategoriesList;
import ua.nure.marketmap.Model.Category;
import ua.nure.marketmap.Model.Color;
import ua.nure.marketmap.Model.IconMarker;
import ua.nure.marketmap.Model.Outlet;
import ua.nure.marketmap.Model.User;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener,
        OnMapReadyCallback,
        ClusterManager.OnClusterClickListener<IconMarker>,
        ClusterManager.OnClusterInfoWindowClickListener<IconMarker>,
        ClusterManager.OnClusterItemClickListener<IconMarker>,
        ClusterManager.OnClusterItemInfoWindowClickListener<IconMarker> {

    private OutletLoadTask mOutletLoadTask;
    private NavigationView mNavigationView;
    private ProgressBar mProgressView;
    private Menu mMenu;

    private User mUser;
    private List<Outlet> mOutlets;
    private Category mSelectedCategory;

    private ClusterManager<IconMarker> mClusterManager;
    private GoogleMap mMap;

    @Override
    /*
    On avtivity (screen) generation actions
     */
    protected void onCreate(Bundle savedInstanceState) {
        //Default actions
        super.onCreate(savedInstanceState); //Default onCreate actions
        setContentView(R.layout.activity_main); //Display content

        //Initializing local private varibles
        mOutletLoadTask = null; //Object for loading data
        mOutlets = new ArrayList<Outlet>(); //List of outlets
        mProgressView = (ProgressBar) findViewById(R.id.outlet_load_progress); //ProgressBar for loading action
        mUser = User.GUEST; //Current user (GUEST by default)
        mSelectedCategory = null; //Selected category (show all if null)
        CategoriesList.init(this); //Initialize categories

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar); //Get top toolbar
        setSupportActionBar(toolbar); //Attach toolbar to current screen

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout); //Get side menu
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle); //Make side menu be opennable
        toggle.syncState(); //Hide by default

        mNavigationView = (NavigationView) findViewById(R.id.nav_view); //Get side menu items
        mNavigationView.setNavigationItemSelectedListener(this); //Set action on click on side menu items


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map); //Get map
        mapFragment.getMapAsync(this); //Load map
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {return;}
        mUser = (User) data.getParcelableExtra("user");
        String name = mUser.getEmail();
        TextView headerNavBar = (TextView) findViewById(R.id.nav_username);
        headerNavBar.setText(name);
        Snackbar.make((View)headerNavBar, "Logged in as " + name, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng kharkiv = new LatLng(49.989651, 36.275175);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kharkiv, 10.4F));

        mClusterManager = new ClusterManager<IconMarker>(this, mMap);
        mClusterManager.setRenderer(new PersonRenderer());
        mMap.setOnCameraIdleListener(mClusterManager);
        mMap.setOnMarkerClickListener(mClusterManager);
        mMap.setOnInfoWindowClickListener(mClusterManager);
        mClusterManager.setOnClusterClickListener(this);
        mClusterManager.setOnClusterInfoWindowClickListener(this);
        mClusterManager.setOnClusterItemClickListener(this);
        mClusterManager.setOnClusterItemInfoWindowClickListener(this);

        mOutletLoadTask = new OutletLoadTask();
        mOutletLoadTask.execute((Void) null);
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
        boolean isCategory = false;
        int id = item.getItemId();

        if (id == R.id.nav_login) {
            if(mUser.equals(User.GUEST)) {
                item.setTitle(R.string.action_log_out);
                mNavigationView.getMenu().findItem(R.id.nav_favourites).setVisible(true);
                Intent loginActivity = new Intent(getApplicationContext(), LoginActivity.class);
                startActivityForResult(loginActivity, 1);
            } else {
                mUser = User.GUEST;
                item.setTitle(R.string.action_log_in);
                mNavigationView.getMenu().findItem(R.id.nav_favourites).setVisible(false);
                TextView headerNavBar = (TextView) findViewById(R.id.nav_username);
                headerNavBar.setText(R.string.guest_name);
                Snackbar.make((View)headerNavBar, "Logged out", Snackbar.LENGTH_SHORT).show();
            }
        } else if (id == R.id.nav_favourites) {
            Intent favourites = new Intent(getApplicationContext(), FavouritesActivity.class);
            favourites.putExtra("user", mUser);
            startActivityForResult(favourites, 2);
        } else if (id == R.id.nav_search) {
            Intent favourites = new Intent(getApplicationContext(), SearchActivity.class);
            startActivityForResult(favourites, 2);
        } else if (id == R.id.nav_cat_all) {
            isCategory = true;
            mSelectedCategory = null;
        } else if (id == R.id.nav_cat_clothes) {
            isCategory = true;
            mSelectedCategory = CategoriesList.getCategory(0);
        } else if (id == R.id.nav_child_goods) {
            isCategory = true;
            mSelectedCategory = CategoriesList.getCategory(1);
        } else if (id == R.id.nav_cat_stationery) {
            isCategory = true;
            mSelectedCategory = CategoriesList.getCategory(2);
        } else if (id == R.id.nav_cat_household) {
            isCategory = true;
            mSelectedCategory = CategoriesList.getCategory(3);
        } else if (id == R.id.nav_cat_petshops) {
            isCategory = true;
            mSelectedCategory = CategoriesList.getCategory(4);
        } else if (id == R.id.nav_cat_plants) {
            isCategory = true;
            mSelectedCategory = CategoriesList.getCategory(5);
        } else if (id == R.id.nav_cat_sanitary) {
            isCategory = true;
            mSelectedCategory = CategoriesList.getCategory(6);
        } else if (id == R.id.nav_cat_caffee) {
            isCategory = true;
            mSelectedCategory = CategoriesList.getCategory(7);
        } else if (id == R.id.nav_cat_medicine) {
            isCategory = true;
            mSelectedCategory = CategoriesList.getCategory(8);
        } else if (id == R.id.nav_cat_tobacco) {
            isCategory = true;
            mSelectedCategory = CategoriesList.getCategory(9);
        } else if (id == R.id.nav_cat_dairy) {
            isCategory = true;
            mSelectedCategory = CategoriesList.getCategory(10);
        } else if (id == R.id.nav_cat_meat_fish) {
            isCategory = true;
            mSelectedCategory = CategoriesList.getCategory(11);
        } else if (id == R.id.nav_cat_vegetables_fruits) {
            isCategory = true;
            mSelectedCategory = CategoriesList.getCategory(12);
        } else if (id == R.id.nav_cat_bakery) {
            isCategory = true;
            mSelectedCategory = CategoriesList.getCategory(13);
        } else if (id == R.id.nav_cat_other) {
            isCategory = true;
            mSelectedCategory = CategoriesList.getCategory(13);
        }

        if(isCategory) {
            showOutlets();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showProgress(final boolean show) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mProgressView.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }

    private void showOutlets() {
        mClusterManager.clearItems();

        for(Outlet outlet : mOutlets) {
            if(mSelectedCategory == null || outlet.hasCategory(mSelectedCategory)) {
                PolygonOptions outletOutline = new PolygonOptions();
                for (LatLng point : outlet.Points) {
                    outletOutline.add(point);
                }

                mClusterManager.addItem(new IconMarker(outlet));

            /*mMap.addMarker(new MarkerOptions()
                    .icon(outlet.getCategoryBitmap())
                    .position(outlet.getCenter())
                    .title(outlet.getName()));*/

                outletOutline.strokeColor(Color.outlineColor());
                outletOutline.fillColor(outlet.getColor());
                outletOutline.strokeWidth(1F);
                outletOutline.clickable(false);
                outletOutline.visible(true);

                mMap.addPolygon(outletOutline).setTag(outlet);
            }
        }

        mClusterManager.cluster();
    }

    private class PersonRenderer extends DefaultClusterRenderer<IconMarker> {
        private final IconGenerator mIconGenerator = new IconGenerator(getApplicationContext());
        private final IconGenerator mClusterIconGenerator = new IconGenerator(getApplicationContext());
        private final ImageView mImageView;
        private final ImageView mClusterImageView;
        private final int mDimension;

        public PersonRenderer() {
            super(getApplicationContext(), mMap, mClusterManager);

            View multiProfile = getLayoutInflater().inflate(R.layout.multi_outlet, null);
            mClusterIconGenerator.setContentView(multiProfile);
            mClusterImageView = (ImageView) multiProfile.findViewById(R.id.image);

            mImageView = new ImageView(getApplicationContext());
            mDimension = (int) getResources().getDimension(R.dimen.custom_profile_image);
            mImageView.setLayoutParams(new ViewGroup.LayoutParams(mDimension, mDimension));
            int padding = (int) getResources().getDimension(R.dimen.custom_profile_padding);
            mImageView.setPadding(padding, padding, padding, padding);
            mIconGenerator.setContentView(mImageView);
        }

        @Override
        protected void onBeforeClusterItemRendered(IconMarker person, MarkerOptions markerOptions) {
            // Draw a single person.
            // Set the info window to show their name.
            mImageView.setImageResource(person.getIcon());
            Bitmap icon = mIconGenerator.makeIcon();
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon)).title(person.getTitle());
        }

        @Override
        protected void onBeforeClusterRendered(Cluster<IconMarker> cluster, MarkerOptions markerOptions) {
            // Draw multiple people.
            // Note: this method runs on the UI thread. Don't spend too much time in here (like in this example).
            List<Drawable> outletIcons = new ArrayList<Drawable>(Math.min(4, cluster.getSize()));
            int width = mDimension;
            int height = mDimension;

            for (IconMarker p : cluster.getItems()) {
                // Draw 4 at most.
                if (outletIcons.size() == 4) break;
                Drawable drawable = getResources().getDrawable(p.getIcon());
                drawable.setBounds(0, 0, width, height);
                outletIcons.add(drawable);
            }
            MultiDrawable multiDrawable = new MultiDrawable(outletIcons);
            multiDrawable.setBounds(0, 0, width, height);

            mClusterImageView.setImageDrawable(multiDrawable);
            Bitmap icon = mClusterIconGenerator.makeIcon(String.valueOf(cluster.getSize()));
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon));
        }

        @Override
        protected boolean shouldRenderAsCluster(Cluster cluster) {
            // Always render clusters.
            return cluster.getSize() > 1;
        }
    }

    @Override
    public boolean onClusterClick(Cluster<IconMarker> cluster) {

        LatLngBounds.Builder builder = LatLngBounds.builder();
        for (ClusterItem item : cluster.getItems()) {
            builder.include(item.getPosition());
        }
        // Get the LatLngBounds
        final LatLngBounds bounds = builder.build();

        // Animate camera to the bounds
        try {
            mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public void onClusterInfoWindowClick(Cluster<IconMarker> cluster) {
        // Does nothing, but you could go to a list of the users.
    }

    @Override
    public boolean onClusterItemClick(IconMarker item) {
        Intent outlet = new Intent(getApplicationContext(), OutletActivity.class);
        outlet.putExtra("outlet", item.getOutlet().getId());
        startActivityForResult(outlet, 1);
        return true;
    }

    @Override
    public void onClusterItemInfoWindowClick(IconMarker item) {
        // Does nothing, but you could go into the user's profile page, for example.
    }

    public class OutletLoadTask extends AsyncTask<Void, Void, List<Outlet>> {
        OutletLoadTask() {
        }

        @Override
        protected void onPreExecute() {
            showProgress(true);
        }

        @Override
        protected List<Outlet> doInBackground(Void... params) {
            // TODO: attempt outlets loading.
            try {
                Thread.sleep(3000);
            } catch (Exception e) {}
           return DBHelper.getOutlets();
        }

        @Override
        protected void onPostExecute(final List<Outlet> outlets) {
            mOutletLoadTask = null;
            showProgress(false);

            if (!outlets.isEmpty()) {
                mOutlets = outlets;
                showOutlets();
            } else {
                Snackbar.make((View)mProgressView, "Cannot load outlets", Snackbar.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {
            mOutletLoadTask = null;
            showProgress(false);
        }
    }
}
