package ua.nure.marketmap.Model;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class Outlet {
    private int Id;
    private Double Rating;
    private String Name;
    private String Address;

    public Color Color() { return Color.defaultColor(); }

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
}
