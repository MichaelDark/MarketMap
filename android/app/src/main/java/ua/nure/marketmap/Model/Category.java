package ua.nure.marketmap.Model;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import ua.nure.marketmap.R;

public class Category {
    private Color Color;
    private String Name;

    public Category(Color color, String name) {
        Color = color;
        Name = name;
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
}
