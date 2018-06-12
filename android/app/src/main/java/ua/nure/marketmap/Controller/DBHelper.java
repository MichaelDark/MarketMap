package ua.nure.marketmap.Controller;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import ua.nure.marketmap.Model.CategoriesList;
import ua.nure.marketmap.Model.Category;
import ua.nure.marketmap.Model.Color;
import ua.nure.marketmap.Model.Outlet;

public class DBHelper {
    public static List<Outlet> getFavorites(int id) {
        return getOutlets();
        /*
        return Arrays.asList(
                new Outlet(1, 4.7, "У Ашота", "пр. Гагарина 36", "8:00 - 19:00", CategoriesList.getCategory(7)),
                new Outlet(2, 3.9, "Наливочка", "пр. Гагарина 36А", "8:00 - 19:00", CategoriesList.getCategory(7))
        );*/
    }

    public static List<Outlet> getOutlets() {
        /*try {
            // This is getting the url from the string we passed in
            URL url = new URL("");

            // Create the urlConnection
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();


            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);

            urlConnection.setRequestProperty("Content-Type", "application/json");

            urlConnection.setRequestMethod("GET");

            int statusCode = urlConnection.getResponseCode();

            if (statusCode ==  200) {

                InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());

                //String response = convertInputStreamToString(inputStream);

                // From here you can convert the string to JSON with whatever JSON parser you like to use
                // After converting the string to JSON, I call my custom callback. You can follow this process too, or you can implement the onPostExecute(Result) method
            } else {
                // Status code is not 200
                // Do something to handle the error
            }

        } catch (Exception e) {

        }
        return null;
        */
        Outlet o01 = new Outlet(1, 4.7, "У Ашота", "пр. Тракторстроителей 128", "8:00 - 19:00", CategoriesList.getCategory(7));
        o01.addPoint(50.010566, 36.350756);
        o01.addPoint(50.010592, 36.350788);
        o01.addPoint(50.010574, 36.350835);
        o01.addPoint(50.010545, 36.350800);

        Outlet o02 = new Outlet(2, 3.9, "Наливочка", "пр. Тракторстроителей 128", "7:00 - 18:00", CategoriesList.getCategory(7));
        o02.addPoint(50.010592, 36.350788);
        o02.addPoint(50.010574, 36.350835);
        o02.addPoint(50.010592, 36.350858);
        o02.addPoint(50.010614, 36.350816);

        Outlet o03 = new Outlet(3, 4.3, "All 5", "пр. Тракторстроителей 128", "6:30 - 20:00", CategoriesList.getCategory(3));
        o03.addPoint(50.010647, 36.350234);
        o03.addPoint(50.010704, 36.350312);
        o03.addPoint(50.010672, 36.350366);
        o03.addPoint(50.010618, 36.350292);
        o03.setFavourite(true);

        return Arrays.asList(
                o01,
                o02,
                o03
        );
    }
    public static Outlet getOutletById(int id) {
        List<Outlet> outlets = getOutlets();

        for(Outlet outlet : outlets)
            if(outlet.getId() == id)
                return outlet;

        return null;
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
