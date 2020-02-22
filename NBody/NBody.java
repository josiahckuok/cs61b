public class NBody {
    /** Simulate a universe specified in one of the data files */

    /** Return a double corresponding to the radius of the universe in a given file */
    public static double readRadius(String filepath) {
        In in = new In(filepath);
        int n = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    /** Return an array of bodies corresponding to the bodies in the file */
    public static Body[] readBodies(String filepath) {
        In in = new In(filepath);
        int n = in.readInt();
        double radius = in.readDouble();
        Body[] allbodies = new Body[n];

        for (int i = 0; i < n; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgfilename = in.readString();

            Body b = new Body(xxPos, yyPos, xxVel, yyVel, mass, imgfilename);
            allbodies[i] = b;
        }
        return allbodies;
    }
}
