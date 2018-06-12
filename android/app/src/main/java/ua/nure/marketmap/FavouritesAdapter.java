package ua.nure.marketmap;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import ua.nure.marketmap.Model.Category;
import ua.nure.marketmap.Model.Color;
import ua.nure.marketmap.Model.Outlet;

public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.ViewHolder> {
    private List<Outlet> mDataset;
    private RecyclerView mRecyclerView;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout mItem;
        public ViewHolder(LinearLayout v) {
            super(v);
            mItem = v;
        }
    }

    public FavouritesAdapter(List<Outlet> myDataset) {
        mDataset = myDataset;
    }

    public FavouritesAdapter(List<Outlet> myDataset, RecyclerView recyclerView) {
        mDataset = myDataset;
        mRecyclerView = recyclerView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fav_item, parent, false);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int itemPosition = mRecyclerView.getChildLayoutPosition(view);

                Outlet item = mDataset.get(itemPosition);

                Activity host = (Activity) view.getContext();

                Intent outlet = new Intent(host, OutletActivity.class);
                outlet.putExtra("outlet", item.getId());
                host.startActivityForResult(outlet, 1);
            }
        });
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TextView viewName = (TextView) holder.mItem.findViewById(R.id.fav_name);
        TextView viewCat = (TextView) holder.mItem.findViewById(R.id.fav_cat);
        Outlet outlet = mDataset.get(position);

        viewName.setText(outlet.getName());
        String categoriesText = "";
        for(Category category : outlet.Categories) {
            categoriesText += category.getName() + " ";
        }
        viewCat.setText(categoriesText);
        holder.mItem.setBackgroundColor(outlet.getColor());
        //holder.mItem.setBackgroundColor(viewName.getContext().getResources().getColor(R.color.fav_item_color_light));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
