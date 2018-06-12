package ua.nure.marketmap.Model;

import android.os.Parcel;

public class Color {
    public double A;
    public int R;
    public int G;
    public int B;

    public Color(double A, int R, int G, int B) {
        this.A = A;
        this.R = R;
        this.G = G;
        this.B = B;
    }
    public Color(Parcel in) {
        this.A = in.readDouble();
        this.R = in.readInt();
        this.G = in.readInt();
        this.B = in.readInt();
    }

    public static Color defaultColor() { return new Color (1.0,102,153, 255); }
    public static int outlineColor() { return getColorIntFromARGB(new Color (1.0,105,105, 105)); }

    public static int getColorIntFromARGB(Color color) {
        int A = ((int)(color.A * 255) << 24);
        int R = (color.R << 16);
        int G = (color.G << 8);
        int B = color.B;

        return A | R | G | B;
    }
    public static Color getColorFromRgbHex(int color) {
        int R = color >> 16;
        int G = R - color >> 8;
        int B = (R + G) - color;

        return new Color(1, R, G, B);
    }
}
