package ua.nure.marketmap;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import ua.nure.marketmap.Model.Outlet;

public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.ViewHolder> {
    private List<Outlet> mDataset;

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

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fav_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TextView viewName = (TextView) holder.mItem.findViewById(R.id.fav_name);
        TextView viewCat = (TextView) holder.mItem.findViewById(R.id.fav_cat);
        viewName.setText(mDataset.get(position).getName());
        viewCat.setText(mDataset.get(position).Categories.get(0).getName());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
