package ua.nure.marketmap;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import ua.nure.marketmap.Controller.DBHelper;
import ua.nure.marketmap.Model.Category;
import ua.nure.marketmap.Model.Outlet;

public class OutletActivity extends AppCompatActivity {
    Outlet mOutlet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outlet);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                mOutlet.setFavourite(!mOutlet.isFavourite());
                setFabColor(mOutlet.isFavourite());
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mOutlet = (Outlet) DBHelper.getOutletById(getIntent().getIntExtra("outlet", 1));
        if(mOutlet != null) {
            String categoriesText = "";
            for(Category category : mOutlet.Categories) {
                categoriesText += category.getName() + " ";
            }

            getSupportActionBar().setTitle(mOutlet.getName());

            TextView categoriesView = (TextView) findViewById(R.id.outlet_text_categories);
            categoriesView.setText(categoriesText);

            TextView descriptionView = (TextView) findViewById(R.id.outlet_text_desciption);
            descriptionView.setText(mOutlet.getDescription());

            setFabColor(mOutlet.isFavourite());
        }
    }

    public void setFabColor(boolean isFavourite) {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if(mOutlet.isFavourite()) {
            fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.favourite)));
        } else {
            fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.not_favourite)));
        }
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
}
