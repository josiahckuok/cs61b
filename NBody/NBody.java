public class NBody {
    /** Simulate a universe specified in one of the data files */

    /** Return a double corresponding to the radius of the universe in a given file */
    public static double readRadius(String file) {
        In in = new In(file);
        int n = in.readInt();
        double radius = in.readDouble();
        return radius;
    }
}
