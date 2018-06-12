package ua.nure.marketmap;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import ua.nure.marketmap.Model.User;

import static ua.nure.marketmap.Controller.DBHelper.getFavorites;

public class FavouritesActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter<FavouritesAdapter.ViewHolder> mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    /*
    On avtivity (screen) generation actions
     */
    protected void onCreate(Bundle savedInstanceState) {
        //Default actions
        super.onCreate(savedInstanceState); //Default onCreate actions
        setContentView(R.layout.activity_favourites); //Display content

        getSupportActionBar().setDisplayShowHomeEnabled(true); //Show menu button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Set menu button as back button

        showOutlets();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        showOutlets();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onNavigateUp() {
        finishActivity(1);
        return super.onNavigateUp();
    }

    private void showOutlets() {
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view); //Get displayed list of outlets

        mRecyclerView.setHasFixedSize(true); //Set fixed size for displayed list of items (necessary!)

        mLayoutManager = new LinearLayoutManager(this); //Get manager for list
        mRecyclerView.setLayoutManager(mLayoutManager); //Set manager to list

        User user = (User) getIntent().getParcelableExtra("user"); //Get sent user
        mAdapter = new FavouritesAdapter(getFavorites(user.getId()), mRecyclerView); //Get users favourite outlets and add to adapter
        mRecyclerView.setAdapter(mAdapter); //Set adapter (needed for displaying)
    }
}








