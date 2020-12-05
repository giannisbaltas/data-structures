import java.lang.Math;


public class Rectangle {
	private int xmin, xmax, ymin, ymax;
	
	
	public Rectangle(int xmin, int xmax, int ymin, int ymax) {
		if( xmin < 0 || xmin > 100 || xmax < 0 || xmax > 100 || ymin < 0 || ymin > 100 || ymax < 0 || ymax > 100){
			System.out.println("X and Y must be from 0 to 100! \nProgram exiting...");
			System.exit(0);
		} else {
			this.xmin = xmin;
			this.xmax = xmax;
			this.ymin = ymin;
			this.ymax = ymax;
		}
	}
	
	
	public int xmin() {
		return this.xmin;
	}
	
	public int xmax() {
		return this.xmax;
	}
	
	public int ymin() {
		return this.ymin;
	}
	
	public int ymax() {
		return this.ymax;
	}
	
	public boolean contains(Point p) {
		if((this.xmin <= p.x()) && (this.xmax >= p.x()) && (this.ymin <= p.y()) && (this.ymax >= p.y())) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public boolean intersects(Rectangle that) {
		boolean tmp = false;
		Rectangle rct = new Rectangle(this.xmin, this.xmax, this.ymin, this.ymax);
		Point a = new Point(that.xmin(), that.ymin());
		Point b = new Point(that.xmin(), that.ymax());
		Point c = new Point(that.xmax(), that.ymax());
		Point d = new Point(that.xmax(), that.ymin());
		if(rct.contains(a)) tmp = true;
		if(rct.contains(b)) tmp = true;
		if(rct.contains(c)) tmp = true;
		if(rct.contains(d)) tmp = true;
		return tmp;
		
	}
	/*
	
		Here it is a shape to help us see exactly the eight areas 
		of xmax,xmin,ymin,ymax there are created out of the rectangle..
		
			   xmin       xmax
				|			|
				|			|
		ymax -------------------- ymax
				|			|
				|			|
				|			|
		ymin -------------------- ymin
				|			|
				|			|
			   xmin        xmax
	*/
	
	public double distanceTo(Point p){
		Rectangle temp = new Rectangle(this.xmin, this.xmax, this.ymin, this.ymax);
		double distance = 0.000010;
		Point p1;
		if(temp.contains(p)) {
			distance = 0.0;
			return distance;
		} //if point is inside the rectangle distance is 0
		
		//we check where the point is located so we know in which extension area of the xmin, xmax, ymin, ymax
		if(p.x() >= this.xmax) { //if the point is on the right side of the rectangle check if it's up, down or between ymin and ymax extension areas
			if(p.y() >= this.ymax) {
				p1 = new Point(this.xmax, this.ymax); //create a point so we can call distance() when p is on the extension area between xmax and ymax
				distance = p1.distanceTo(p);
				return distance;
			}
			if((p.y() >= this.ymin) && (p.y() <= this.ymax)) {
				distance = Math.abs(p.x() - this.xmax);
				return distance;
			} //if the point is between ymin and ymax then we calculate the distance between p.x and xmax 
			if(p.y() <= this.ymin) { 
				p1 = new Point(this.xmax, this.ymin); //create a point so we can call distance() when p is on the extension area between xmax and ymin
				distance = p1.distanceTo(p);
				return distance;
			}
		}
		if(p.x() <= this.xmin){ //if the point is on the left side of the rectangle check if it's up, down or between ymin and ymax extension areas
			if(p.y() >= this.ymax) {
				p1 = new Point(this.xmin, this.ymax); //create a point so we can call distance() when p is on the extension area between xmin and ymax
				distance = p1.distanceTo(p);
				return distance;
			}
			if((p.y() >= this.ymin) && (p.y() <= this.ymax)) {
				distance = Math.abs(p.x() - this.xmin);
				return distance;
			} //if the point is between ymin and ymax then we calculate the distance between p.x and xmin
			if(p.y() <= this.ymin) { 
				p1 = new Point(this.xmin, this.ymin); //create a point so we can call distance() when p is on the extension area between xmin and ymin
				distance = p1.distanceTo(p);
				return distance;
			}
		}
		if(p.y() > this.ymax) {
			if((p.x() >= this.xmin) && (p.x() <= this.xmax)) {//if the point is on the upside of the rectangle and between the xmax and xmin extension area then we calculate the distance between p.y and ymax
				distance = Math.abs(p.y() - this.ymax);
				return distance;
			}
		} else if (p.y() < this.ymin) {
			if((p.x() >= this.xmin) && (p.x() <= this.xmax)) {//if the point is bellows the rectangle and between the xmax and xmin extension area then we calculate the distance between p.y and ymin
				distance = Math.abs(p.y() - this.ymin);
				return distance;
	
			}
		}
		
		return distance;
	}
	
	
	public double squareDistanceTo(Point p){
		Rectangle temp = new Rectangle(this.xmin, this.xmax, this.ymin, this.ymax);
		double squareDistance = temp.distanceTo(p);
		return squareDistance*squareDistance;
	}
	
	public String toString() {
		return "[" + this.xmin + ", " + this.xmax + "] X [" + this.ymin + ", " + this.ymax + "]";
	}
	
}
	
	
	
	
	
	
