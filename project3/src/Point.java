import java.lang.Math;

public class Point {
	private int x;
	private int y;
	
	public Point() {} //creates null Point
					  //needed for insert in 2dtree	
	
	public Point(int x, int y) {
		if(x < 0 || x > 100 || y < 0 || y > 100) {
			System.out.println("X and Y must be from 0 to 100! \nProgram exiting...");
			System.exit(0);
		} else {
			this.x = x;
			this.y =y;
		}
	}
	
	public int x() {
		return this.x;
	}
	
	public int y() {
		return this.y;
	}
	
	public double distanceTo(Point z) {
		double tmp1 = z.x() - this.x;
		double tmp2 = z.y() - this.y;
		double tmp3 = Math.sqrt((tmp1*tmp1) + (tmp2*tmp2));
		return tmp3;
	}
	
	public double squareDistanceTo(Point z) {
		double tmp1 = z.x() - this.x;
		double tmp2 = z.y() - this.y;
		double tmp3 = (tmp1*tmp1) + (tmp2*tmp2);
		return tmp3;
	}
	
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}
	
	
}