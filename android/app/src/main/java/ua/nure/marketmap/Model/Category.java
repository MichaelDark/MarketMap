package ua.nure.marketmap.Model;

import android.os.Parcel;
import android.os.Parcelable;


public class Category {
    private Color Color;
    private int Id;
    private int IconId;
    private String Name;

    public Category(int id, Color color, String name, int iconId) {
        Id = id;
        IconId = iconId;
        Color = color;
        Name = name;
    }

    public int getId() {
        return Id;
    }
    public void setId(int id) {
        Id = id;
    }
    public Color getColor() {
        return Color;
    }
    public void setColor(Color color) {
        Color = color;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public int getIconId() {
        return IconId;
    }
    public void setIconId(int iconId) {
        IconId = iconId;
    }
}

