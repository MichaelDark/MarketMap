package ua.nure.marketmap.Model;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class IconMarker implements ClusterItem {
    Outlet mOutlet;

    public IconMarker (Outlet outlet) {
        mOutlet = outlet;
    }

    public Outlet getOutlet() {
        return mOutlet;
    }
    public LatLng getPosition() {
        return mOutlet.getCenter();
    }
    public String getTitle() {
        return mOutlet.getName();
    }
    public BitmapDescriptor getBitmap() {
        return mOutlet.getCategoryBitmap();
    }
    public int getIcon() {
        return mOutlet.getCategoryIcon();
    }

    public String getSnippet() {
        return "";
    }
}
