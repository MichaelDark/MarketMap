package ua.nure.marketmap.Model;

import android.graphics.Color;
import java.util.Dictionary;

public class ColorCollection
{
    public static int shared;
    private static Dictionary<Category, Integer> values;

    public static int getColor(Category c) {
        Integer value = values.get(c);
        if (value != null) {
            return value;
        } else {
            return shared;
        }
    }
}