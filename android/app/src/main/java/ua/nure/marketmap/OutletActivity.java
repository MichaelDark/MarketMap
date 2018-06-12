package ua.nure.marketmap;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import ua.nure.marketmap.Controller.DBHelper;
import ua.nure.marketmap.Model.Category;
import ua.nure.marketmap.Model.Outlet;

public class OutletActivity extends AppCompatActivity {
    Outlet mOutlet;

    @Override
    /*
    On avtivity (screen) generation actions
     */
    protected void onCreate(Bundle savedInstanceState) {
        //Default actions
        super.onCreate(savedInstanceState); //Default onCreate actions
        setContentView(R.layout.activity_outlet); //Display content

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar); //Get top toolbar
        setSupportActionBar(toolbar); //Attach toolbar to current screen

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab); //Get favouite button
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, (!mOutlet.isFavourite() ? "Set" : "Unset") + " to favourite", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                mOutlet.setFavourite(!mOutlet.isFavourite());
                setFabColor(mOutlet.isFavourite());
            }
        }); //Set event on click on favourite button

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Set menu button as back button

        mOutlet = (Outlet) DBHelper.getOutletById(getIntent().getIntExtra("outlet", 1)); //Get outlet
        if(mOutlet != null) { //If there is a suitable outlet...
            String categoriesText = ""; //...make a string for categories list...
            for(Category category : mOutlet.Categories) {
                categoriesText += category.getName() + " "; //...merge them...
            }

            getSupportActionBar().setTitle(mOutlet.getName());

            CollapsingToolbarLayout toolbarLayout = (CollapsingToolbarLayout)  findViewById(R.id.toolbar_layout);
            toolbarLayout.setBackgroundColor(mOutlet.getColor());

            TextView categoriesView = (TextView) findViewById(R.id.outlet_text_categories);
            categoriesView.setText(categoriesText); //...display categories...

            TextView descriptionView = (TextView) findViewById(R.id.outlet_text_desciption);
            descriptionView.setText(mOutlet.getDescription()); //...display description...

            setFabColor(mOutlet.isFavourite()); //...and set favourite button
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
