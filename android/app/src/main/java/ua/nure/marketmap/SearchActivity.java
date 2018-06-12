package ua.nure.marketmap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import ua.nure.marketmap.Controller.DBHelper;
import ua.nure.marketmap.Model.Outlet;

public class SearchActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter<SearchAdapter.ViewHolder> mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Outlet> mOutlets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //Default onCreate actions
        setContentView(R.layout.activity_search); //Display content

        mOutlets = DBHelper.getOutlets();

        getSupportActionBar().setDisplayShowHomeEnabled(true); //Show menu button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Set menu button as back button

        updateOutlets();

        EditText textPlain = (EditText) findViewById(R.id.searchText);
        textPlain.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                updateOutlets();
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        updateOutlets();
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

    private void updateOutlets() {
        mRecyclerView = (RecyclerView) findViewById(R.id.my_search_view); //Get displayed list of outlets

        mRecyclerView.setHasFixedSize(true); //Set fixed size for displayed list of items (necessary!)

        mLayoutManager = new LinearLayoutManager(this); //Get manager for list
        mRecyclerView.setLayoutManager(mLayoutManager); //Set manager to list

        List<Outlet> checked = new ArrayList<>();
        for(Outlet outlet : mOutlets) {
            EditText textPlain = (EditText) findViewById(R.id.searchText);
            if(outlet.getName().toLowerCase().contains(textPlain.getText().toString().toLowerCase())) {
                checked.add(outlet);
            }
        }

        mAdapter = new SearchAdapter(checked, mRecyclerView); //Get users favourite outlets and add to adapter
        mRecyclerView.setAdapter(mAdapter); //Set adapter (needed for displaying)
    }
}
