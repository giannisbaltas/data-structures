import java.util.*;
import java.io.*; 


public class TwoDTree {
	
	
	private class Treenode {
		
		// The data it holds
		private Point item;

		// Left subtree (child)
		private Treenode l;

		// Right subtree (child)
		private Treenode r;
		
		// The BST node's parent (useful for performing rotations (swaps) during root insertion)
		private Treenode parent;
		
		//defines if we need to check the X or the Y
		private boolean alternate;
		/**
		 * Default TreeNode constructor
		 */
		public Treenode(){
		}
		
		public Treenode(Point item, Treenode l, Treenode r, boolean alternate) {
			this.item = item;
			this.l = l;
			this.r = r;
			this.alternate = alternate;
		}

		public Treenode(Point item){
			this.item = item;
		}


		public Point getItem() {
			return item;
		}

	  
		public void setItem(Point item) {
			this.item = item;
		}

		/**
		 * @return left subtree
		 */
		public Treenode getLeft() {
			return l;
		}

		/**
		 * @param left Set left subtree
		 */
		public void setLeft(Treenode l) {
			this.l = l;
		}

		/**
		 * @return right subtree
		 */
		public Treenode getRight() {
			return r;
		}

		/**
		 * @param right Set right subtree
		 */
		public void setRight(Treenode r) {
			this.r = r;
		}
		
		/** 
		 * @return the BST node's parent
		 */
		public Treenode getParent() {
			return parent;
		}

		/**
		 * 
		 * @param parent Set the BST node's parent
		 */
		public void setParent(Treenode parent) {
			this.parent = parent;
		}
		
	
		private void print(int level) {
            System.out.println(item + " level: " + level);
            if (l != null) {
                System.out.print("left of" + item + ": ");
                l.print(level + 1);
            }
            if (r != null) {
                System.out.print("right of" + item + ": ");
                r.print(level + 1);
            }
        }	
		
	} // end Treenode
	
	
	private Treenode head; //tree root
	private int size;
	
	
	public TwoDTree() { // construct an empty tree
		head = null;
        size = 0;
	} 
	
	public boolean isEmpty() { // is the tree empty?
		return head == null;
	} 
	
	
	public int size() { // number of points in the tree
		return size;
	} 
	
	
	public void insert(Point p) { // inserts the point p to the tree
        if (p == null) {
            System.out.println("Illegal point argument");
        }
        if (head == null || !search(p)) { //if root is empty or when the point isn't in the tree we insert it
            size++;
            head = insert(head, p, true);
        }
    }
	
	private Treenode insert(Treenode leaf, Point p, boolean alternate) {
		if(leaf == null) {
			return new Treenode(p, null, null, alternate); //if leaf is empty we insert the new point and make the left and right leafs of the current leaf null
		}
		
		if(leaf.alternate) {
			if(p.x() < leaf.item.x()) {
				leaf.l = insert(leaf.l, p, !alternate);
			} else {
				leaf.r = insert(leaf.r, p, !alternate);
			}
		} else {
			if(p.y() < leaf.item.y()) {
				leaf.l = insert(leaf.l, p, !alternate);
			} else {
				leaf.r = insert(leaf.r, p, !alternate);
			}
		}
		
		return leaf;
	}
	
	 
	public boolean search(Point p) { // does the tree contain p?
		if(p == null) {
			System.out.println("Illegal point argument");
		}
		return search(head, p);
	} 
	
	private boolean search(Treenode leaf, Point p) {
		if (leaf == null) 
			return false;
		if((p.x() == leaf.item.x()) && (p.y() == leaf.item.y()))
			return true;
		
		if((leaf.alternate && p.x() < leaf.item.x()) || (!leaf.alternate && p.y() < leaf.item.y())) {
			return search(leaf.l, p);
		} else {
			return search(leaf.r, p);
		}
	}
	
	private void print() {
		if (head == null){
			System.out.println("Tree is empty");
		} else {
			head.print(0);
		}
	}
	
	public List<Point> rangeSearch(Rectangle rect) {// Returns a list with the Points that are contained in the rectangle
		if (rect == null) {
            System.out.println("Rectangle can't be null");
			System.exit(0);
        }
        List<Point> rangePoints = new List<>();
        if (!isEmpty()) {
            range(head, rect, rangePoints);
        }
        return rangePoints;
	}
	
	private void range(Treenode current, Rectangle rect, List<Point> rangePoints) {
        if (current == null) {
            return ;
        }

        Point p = current.item;
        if (rect.contains(p)) {
            rangePoints.insertAtFront(p);
        }
        range(current.l, rect, rangePoints);
        range(current.r, rect, rangePoints);
    }
	
	
	public Point nearestNeighbor(Point p) {// point in the tree that is closest to p (null if tree is empty)
		if (p == null) {
            System.out.println("Point is null");
        }
        if (isEmpty()) {
            return null;
        }

        return nearest(head, p, head).item;
	}						  
	
	private Treenode nearest(Treenode current, Point p, Treenode nearest) {
        if (current == null || nearest.item.squareDistanceTo(p) < current.item.squareDistanceTo(p)) {
            return nearest;
        }

        if (current.item.squareDistanceTo(p) < nearest.item.squareDistanceTo(p)) {
            nearest = current;
        }

        Treenode l = nearest(current.l, p, nearest);
        if (l.item.squareDistanceTo(p) < nearest.item.squareDistanceTo(p)) {
            nearest = l;
        }

        Treenode r = nearest(current.r, p, nearest);
        if (r.item.squareDistanceTo(p) < nearest.item.squareDistanceTo(p)) {
            nearest = r;
        }

        return nearest;
    }
	
	
	public static void main(String[] args) {
		TwoDTree tree= new TwoDTree();
		tree = readFiles(tree, args[0]);
		Scanner input = new Scanner(System.in);
		int choice;   // user's choice of the menu
		int X1,Y1, X2, Y2; // needed for case 2
		while (true) {
			System.out.println("\nEnter your selection:\n1. Compute the size of the tree \n2. Insert a new point \n3. Search if a given point exists in the tree \n4. Provide a query rectangle \n5. Provide a query point \n6. Print the Tree \n7. Exit ");
			choice = input.nextInt();
			
			switch (choice) {
			case 1:
				System.out.println("\nThe size of the tree is: " + tree.size());
				break;
			case 2:
				System.out.println("\nYou have to give new coordinates for a new Point. ");
				System.out.println("Give the X coordinate of this Point: ");
				X1 = input.nextInt();
				System.out.println("Give the Y coordinate of this Point: ");
				Y1 = input.nextInt();
				Point p1 = new Point(X1,Y1);
				tree.insert(p1);
				break;
			case 3:
				System.out.println("\nYou have to give new coordinates for a new Point to check if it exists in the tree.");
				System.out.println("Give the X coordinate of this Point: ");
				X1 = input.nextInt();
				System.out.println("Give the Y coordinate of this Point: ");
				Y1 = input.nextInt();
				Point p2 = new Point(X1,Y1);
				tree.search(p2);
				if (tree.search(p2) == true ){
					System.out.println("\nThe given Point (" + X1 + ", " + Y1 + ") exists in the tree. ");
				}else System.out.println("\nThe given Point (" + X1 + ", " + Y1 + ") doesn't exists in the tree. ");
				break;
			case 4:
				System.out.println("Enter the coordinates of the rectangle: (ex. enter 20 30 and then 40 80)");
				X1 = input.nextInt();
				Y1 = input.nextInt();
				X2 = input.nextInt();
				Y2 = input.nextInt();
				Rectangle rtc = new Rectangle(X1, X2, Y1, Y2);
				List<Point> rangeP = new List<>();
				rangeP = tree.rangeSearch(rtc);
				System.out.println("The Points of the Tree that exist in the given Rectangle are: ");
				System.out.println(rangeP.toString());
				break;
			case 5:
				System.out.println("\nYou have to give new coordinates for a new Point to find the nearest point in the tree.");
				System.out.println("Give the X coordinate of this Point: ");
				X1 = input.nextInt();
				System.out.println("Give the Y coordinate of this Point: ");
				Y1 = input.nextInt();
				Point p3 = new Point(X1,Y1);
				Point p4;
				p4 = tree.nearestNeighbor(p3);
				System.out.println("The Point of the Tree which is nearest in the given Point is: " + p4.toString());
				break;
			case 6:
				tree.print();
				break;
			case 7:
				System.exit(0);
				break;
			default:
				return;

			}
		}
	
	
	}
	
	public static TwoDTree readFiles(TwoDTree tree, String filename) {
		try {	
			File f = new File(filename);
			Scanner s = new Scanner(f);
			int counter = -1;
			int x, y;
			int n = 0;
			while(s.hasNextInt()){
				counter++; 				//we count how many Points are in the txt file
				if(counter == 0) {		
					n = s.nextInt();	//if we are at the first line of the txt we store the number of the points that's supposed to be in the file
				} else {
					x = s.nextInt();
					y = s.nextInt();
					if((x >= 0 && x <= 100) && (y >= 0 && y <= 100)) {
						Point tempP = new Point(x, y);
						tree.insert(tempP);
					} else {		
						System.out.println("Illegal parameter");
						System.exit(0); // end of program
					}
				}
			}
			if(counter != n) {
				System.out.println("There are more or less points than expected");
				System.exit(0);
			}
			return tree;
		} catch(Exception e) {
			return null;
		}	
	}

}