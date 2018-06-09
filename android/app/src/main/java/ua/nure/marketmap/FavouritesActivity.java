package ua.nure.marketmap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;

import java.util.Arrays;
import java.util.List;

public class FavouritesActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter<FavouritesAdapter.ViewHolder> mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)

        Integer userId = getIntent().getIntExtra("id", -1);
        mAdapter = new FavouritesAdapter(getFavorites(userId));
        mRecyclerView.setAdapter(mAdapter);
    }

    private List<Pair<Integer, String>> getFavorites(int id) {
        return Arrays.asList(
                new Pair<Integer, String>(1, "У Ашота"),
                new Pair<Integer, String>(2, "МЯСО")
        );
    }
}

