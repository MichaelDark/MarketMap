package ua.nure.marketmap.Controller;

import com.google.android.gms.maps.model.LatLng;

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

    public static List<Outlet> getOutlets() {
        Outlet o01 = new Outlet(1, 4.7, "У Ашота", "пр. Гагарина 36", new Category(Color.defaultColor(), "Other"));
        o01.addPoint(50.010566, 36.350756);
        o01.addPoint(50.010592, 36.350788);
        o01.addPoint(50.010574, 36.350835);
        o01.addPoint(50.010545, 36.350800);

        Outlet o02 = new Outlet(2, 3.9, "Наливочка", "пр. Гагарина 36А", new Category(Color.defaultColor(), "Other"));
        o02.addPoint(50.010592, 36.350788);
        o02.addPoint(50.010574, 36.350835);
        o02.addPoint(50.010592, 36.350858);
        o02.addPoint(50.010614, 36.350816);

        return Arrays.asList(
                o01,
                o02
        );
    }

    public static int getUser(String email, String password) {
        if(email.equals("mm@nure.ua") && password.equals("12345")) {
            return 1;
        } else {
            return -1;
        }
    }

    public static boolean userExists(int userId) {
        return userId != -1;
    }
}
