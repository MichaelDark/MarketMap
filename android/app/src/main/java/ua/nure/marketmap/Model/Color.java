package ua.nure.marketmap.Model;

public class Color {
    public int A;
    public int R;
    public int G;
    public int B;

    public Color(int A, int R, int G, int B) {
        this.A = A;
        this.R = R;
        this.G = G;
        this.B = B;
    }

    public static Color defaultColor() { return new Color (1,102,153, 255); }
}
