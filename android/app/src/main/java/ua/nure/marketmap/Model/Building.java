package ua.nure.marketmap.Model;

import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.List;

public abstract class Building {
    protected String name;
    protected String address;
    protected List<LatLng> points;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public void addPoint(LatLng point) {
        points.add(point);
    }
    public void setPoints(List<? extends LatLng> points) {
        this.points = new ArrayList<>(points);
    }
    public int getPointsCount() { return points.size(); };
    public LatLng getPoint(int index) { return points.get(index); }
    public void removeLastPoint() {
        if(points.size() != 0) {
            points.remove(points.size() - 1);
        }
    }
    public void removeAllPoints() {
        points.clear();
    }
}
