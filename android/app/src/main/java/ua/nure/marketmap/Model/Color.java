package ua.nure.marketmap.Model;

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

    public static Color defaultColor() { return new Color (1.0,102,153, 255); }
    public static int outlineColor() { return getFromARGB(new Color (1.0,105,105, 105)); }

    public static int getFromARGB(Color color) {
        int A = ((int)(color.A * 255) << 24);
        int R = (color.R << 16);
        int G = (color.G << 8);
        int B = color.B;

        return A | R | G | B;
    }
}
