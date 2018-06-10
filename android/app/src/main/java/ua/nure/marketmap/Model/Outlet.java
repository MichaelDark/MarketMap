package ua.nure.marketmap.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import ua.nure.marketmap.R;

public class Outlet {
    private int Id;
    private Double Rating;
    private String Name;
    private String Address;

    public List<LatLng> Points;
    public List<Category> Categories;
    public List<Comment> Comments;

    public Outlet(int id, Double rating, String name, String address) {
        Id = id;
        Rating = rating;
        Name = name;
        Address = address;
        Points = new ArrayList<>();
        Categories = new ArrayList<>();
        Comments = new ArrayList<>();
    }

    public Outlet(int id, Double rating, String name, String address, Category category) {
        this(id, rating, name, address);
        Categories = new ArrayList<>();
        Categories.add(category);
    }

    public int getId() {
        return Id;
    }
    public void setId(int id) {
        Id = id;
    }
    public Double getRating() {
        return Rating;
    }
    public void setRating(Double rating) {
        Rating = rating;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getAddress() {
        return Address;
    }
    public void setAddress(String address) {
        Address = address;
    }

    public Color getArgbColor() { return Color.defaultColor(); }
    public int getColor() { return Color.getFromARGB(Color.defaultColor()); }

    public void addPoint(double lat, double lng) {
        Points.add(new LatLng(lat, lng));
    }
    public void addPoint(LatLng point) {
        Points.add(point);
    }
    public int getPointsCount() {
        return Points.size();
    };
    public LatLng getPoint(int index) {
        return Points.get(index);
    };
    public LatLng getCenter() {
        double latLow = 360;
        double lngLow = 360;
        double latHigh = -1;
        double lngHigh = -1;

        for(LatLng point : Points) {
            double lat = point.latitude;
            double lng = point.longitude;

            if(lat > latHigh) {
                latHigh = lat;
            } else if(lat < latLow) {
                latLow = lat;
            }

            if(lng > lngHigh) {
                lngHigh = lng;
            } else if(lng < lngLow) {
                lngLow = lng;
            }
        }

        return new LatLng(latLow + ((latHigh - latLow) / 2), lngLow + ((lngHigh - lngLow) / 2));
    };

    public void addCategory(Category cat) {
        Categories.add(cat);
    }
    public int getCategoriesCount() {
        return Categories.size();
    };
    public Category getCategory(int index) {
        return Categories.get(index);
    };

    public BitmapDescriptor getCategoryBitmap() {
        return BitmapDescriptorFactory.fromResource(getCategoryIcon());
    }
    public int getCategoryIcon() {
        return Categories.get(0).getIconId();
    }

    public void addComment(Comment comment) {
        Comments.add(comment);
    }
    public int getCommentsCount() {
        return Comments.size();
    };
    public Comment getComments(int index) {
        return Comments.get(index);
    };
}
