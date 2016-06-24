import java.awt.Point;

public class Node {
	public Point p;
	public Node parent;
	public double hcost;
	public int gcost;

	// default constructor
	public Node() {
		p = null;
		parent = null;
		hcost = 0;
		gcost = 0;
	}

	public Node(Point point) {
		p = point;
	}

	public Node(Point point, double h, int g) {
		p = point;
		parent = null;
		hcost = h;
		gcost = g;
	}

}