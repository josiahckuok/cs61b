public class NBody {

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

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        double radius = readRadius(filename);
        Body[] allbodies = readBodies(filename);

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();

        for (int t = 0; t <= T; t += dt) {
            double[] xForces = new double[allbodies.length];
            double[] yForces = new double[allbodies.length];

            for (int i = 0; i < allbodies.length; i++) {
                xForces[i] = allbodies[i].calcNetForceExertedByX(allbodies);
                yForces[i] = allbodies[i].calcNetForceExertedByY(allbodies);
            }

            for (int i = 0; i < allbodies.length; i++) {
                allbodies[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.picture(0, 0, "./images/starfield.jpg");

            for (Body body : allbodies) {
                body.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", allbodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < allbodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    allbodies[i].xxPos, allbodies[i].yyPos, allbodies[i].xxVel,
                    allbodies[i].yyVel, allbodies[i].mass, allbodies[i].imgFileName);
        }
    }
}
