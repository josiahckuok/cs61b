public class Body {

	double xxPos;
	double yyPos;
	double xxVel;
	double yyVel;
	double mass;
	String imgFileName;
	static final double g = 6.67 * Math.pow(10, -11);

	public Body(double xP, double yP, double xV, double yV, double m, String img) {
		this.xxPos = xP;
		this.yyPos = yP;
		this.xxVel = xV;
		this.yyVel = yV;
		this.mass = m;
		this.imgFileName = img;
	}

	public Body(Body b) {
		this.xxPos = b.xxPos;
		this.yyPos = b.yyPos;
		this.xxVel = b.xxVel;
		this.yyVel = b.yyVel;
		this.mass = b.mass;
		this.imgFileName = b.imgFileName;
	}

	/**
	 * Calculate the distance between the supplied body and this body
	 */
	public double calcDistance(Body b) {
		return Math.pow(Math.pow(this.xxPos - b.xxPos, 2) + Math.pow(this.yyPos - b.yyPos, 2), 0.5);
	}

	/**
	 * Calulate the gravitational force exerted on this body by the given body
	 */
	public double calcForceExertedBy(Body b) {
		return (g * this.mass * b.mass) / Math.pow(calcDistance(b), 2);
	}

	/**
	 * Calulate the gravitational force exerted on this body by the given body in the X direction
	 */
	public double calcForceExertedByX(Body b) {
		return calcForceExertedBy(b) * Math.abs(this.xxPos - b.xxPos) / calcDistance(b);
	}

	/**
	 * Calulate the gravitational force exerted on this body by the given body in the Y direction
	 */
	public double calcForceExertedByY(Body b) {
		return calcForceExertedBy(b) * Math.abs(this.yyPos - b.yyPos) / calcDistance(b);
	}

	/**
	 * Calulate the net gravitational force exerted by all bodies in that array upon the current Body in the X
	 * direction
	 */
	public double calcNetForceExertedByX(Body[] allBodies) {
		double netForce = 0;
		for (Body body : allBodies) {
			if (this.equals(body)) {
				continue;
			}
			netForce += calcForceExertedByX(body);
		}
		return netForce;
	}

	/**
	 * Calulate the net gravitational force exerted by all bodies in that array upon the current Body in the Y
	 * direction
	 */
	public double calcNetForceExertedByY(Body[] allBodies) {
		double netForce = 0;
		for (Body body : allBodies) {
			if (this.equals(body)) {
				continue;
			}
			netForce += calcForceExertedByY(body);
		}
		return netForce;
	}

	/**
	 * Determine how much the forces exerted on the body will cause that body to accelerate and the resulting change
	 * in the body’s velocity and position in a small period of time dt.
	 */
	public void update(double dt, double xForce, double yForce) {
		double accelX = xForce / this.mass;
		double accelY = yForce / this.mass;

		this.xxVel += dt * accelX;
		this.yyVel += dt * accelY;
		this.xxPos += dt * this.xxVel;
		this.yyPos += dt * this.yyVel;
	}

	/** Draw a body itself */
	public void draw() {
		String file = "./images/" + this.imgFileName;
		StdDraw.picture(this.xxPos, this.yyPos, file);
	}
}