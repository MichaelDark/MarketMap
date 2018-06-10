package ua.nure.marketmap.Controller;

import java.util.Arrays;
import java.util.List;

import ua.nure.marketmap.Model.Category;
import ua.nure.marketmap.Model.Color;
import ua.nure.marketmap.Model.Outlet;

public class DBHelper {
    public static List<Outlet> getFavorites(int id) {
        return Arrays.asList(
                new Outlet(1, 4.7, "У Ашота", "пр. Гагарина 36", new Category(Color.defaultColor(), "Other")),
                new Outlet(2, 3.9, "Наливочка", "пр. Гагарина 36А", new Category(Color.defaultColor(), "Other"))
        );
    }
}
