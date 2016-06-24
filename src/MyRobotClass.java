import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import world.Robot;
import world.World;

public class MyRobotClass extends Robot {

	private World myWorld;

	@Override
	public void addToWorld(World myWorld) {
		this.myWorld = myWorld;
		super.addToWorld(myWorld);
	}

	@Override
	public void travelToDestination() {

		// two special cases where only 1 col or 1 row
		if (this.myWorld.numCols() == 1 || this.myWorld.numRows() == 1) {
			if (myWorld.numCols() == 1) { // when 1 col,
				// if robotX is greater than endX, move up
				if (super.getX() > myWorld.getEndPos().x) {
					while (super.getX() > myWorld.getEndPos().x) {
						super.move(new Point(super.getX() - 1, 0));
					}
				} else { // else, move down
					while (super.getX() < myWorld.getEndPos().x) {
						super.move(new Point(super.getX() + 1, 0));
					}
				}
			}

			if (myWorld.numRows() == 1) { // when 1 row,
				// if robotY is greater than endY, move left
				if (super.getY() > myWorld.getEndPos().y) {
					while (super.getY() > myWorld.getEndPos().y) {
						super.move(new Point(0, super.getY() - 1));
					}
				} else { // else, move down
					while (super.getY() < myWorld.getEndPos().y) {
						super.move(new Point(0, super.getY() + 1));
					}
				}
			}
			return;
		}

		// returns a Stack<Point> of path
		Stack<Point> path = new Stack<Point>();

		// initiate a 2d array that keep track of obstacles
		String[][] map = new String[myWorld.numRows()][myWorld.numCols()];

		// return a 2d array with distance between point and endPos
		double[][] dist = evalDist(myWorld);

		// initiate two priority queues to store points
		Queue<Node> open = new LinkedList<Node>();
		List<Node> closed = new ArrayList<Node>();

		// add the start point to the queue
		Node root = new Node(myWorld.getStartPos(), calcDist(
				myWorld.getStartPos(), myWorld.getEndPos()), 0);
		open.add(root);

		/*
		 * pop point from the queue, and add surrounding points to the end of
		 * the queue
		 */
		while (!open.isEmpty()) {
			// sort the list in order of (gcost+hcost)
			((List<Node>) open).sort((o1, o2) -> Double.compare(o1.hcost
					+ o1.gcost, o2.hcost + o2.gcost));

			// store the current node as n
			Node n = open.poll();
			closed.add(n);

			// if current point is the end point, then break
			if (n.p.equals(myWorld.getEndPos())) {
				break;
			} else {
				// else, add all surrounding points to the queue by its
				// distance order
				List<Point> surr = surround(n.p, myWorld, dist);

				for (int i = 0; i < surr.size(); i++) {
					boolean closedHasIt = false;
					boolean openHasIt = false;

					for (int j = 0; j < closed.size(); j++) {
						if (closed.get(j).p.equals(surr.get(i))) {

							closedHasIt = true;
						}
					}

					// pingMap and store result in a 2D Array
					if (map[surr.get(i).x][surr.get(i).y] == null) {

						map[surr.get(i).x][surr.get(i).y] = "O";
						// if (myRobot.pingMap(surr.get(i)).equals("X")) {
						// map[surr.get(i).x][surr.get(i).y] = "X";
						// } else {
						// map[surr.get(i).x][surr.get(i).y] = "O";
						// }
					}

					if (closedHasIt) {

					} else if (!closedHasIt
							&& map[surr.get(i).x][surr.get(i).y].equals("X")) {
						closed.add(new Node(surr.get(i)));

					} else {
						// if this node is traversable and not in closed,

						for (Node item : open) {
							if (item.p.equals(surr.get(i))) {

								openHasIt = true;

								if (item.gcost + item.hcost > n.gcost + 2
										+ n.hcost) {

									item.gcost = n.gcost + 1;
									item.parent = n;

								}

							}
						}
						Node temp = new Node(surr.get(i));

						temp.parent = n;
						temp.gcost = temp.parent.gcost + 1;
						temp.hcost = calcDist(surr.get(i), myWorld.getEndPos());

						if (!openHasIt) {

							// create a new node of this point

							// add this node to open
							// pingMap and store result in a 2D Array
							if (map[temp.p.x][temp.p.y].equals("X")) {

							} else {

								if (!surr.get(i).equals(myWorld.getEndPos())
										&& super.pingMap(surr.get(i)).equals(
												"X")) {
									map[surr.get(i).x][surr.get(i).y] = "X";

								} else {
									open.add(temp);
								}
								// super.makeGuess(surr.get(i), true);
							}

						}
					}
					// System.out.println(open);
				}
			}
		}

		// to locate the endPos node within closed list
		for (int i = 0; i < closed.size(); i++) {
			if (closed.get(i).p.equals(myWorld.getEndPos())) {

				path = printPath(closed.get(i), new Stack<Point>());

			}
		}

		// actual move
		while (path != null && !path.isEmpty()) {
			super.move(path.pop());
		}

	}

	public static Stack<Point> printPath(Node n, Stack<Point> s) {

		if (n.parent == null) {
			return s;
		} else {
			// System.out.println("(" + n.p.x + ", " + n.p.y + ")");
			// System.out.println("^");
			s.push(n.p);
			return printPath(n.parent, s);
		}
	}

	/*
	 * This method returns the ArrayList of all surrounding points of the given
	 * point p, sorted by its distance to the endPos
	 */
	public static List<Point> surround(Point p, World myWorld, double[][] dist) {

		List<Point> surround = new ArrayList<Point>();

		// special cases when robot is by the edges
		if (p.x == 0) {
			// at top row
			if (p.y == 0) {
				// if at top left corner
				// System.out.println("TOP LEFT");
				surround.add(new Point(p.x, p.y + 1));
				surround.add(new Point(p.x + 1, p.y + 1));
				surround.add(new Point(p.x + 1, p.y));
			} else if (p.y == myWorld.numCols() - 1) {
				// if at top right corner
				// System.out.println("TOP RIGHT");
				surround.add(new Point(p.x, p.y - 1));
				surround.add(new Point(p.x + 1, p.y - 1));
				surround.add(new Point(p.x + 1, p.y));
			} else {
				// if at top middle points
				// System.out.println("TOP MIDDLE");
				surround.add(new Point(p.x, p.y - 1));
				surround.add(new Point(p.x, p.y + 1));
				surround.add(new Point(p.x + 1, p.y - 1));
				surround.add(new Point(p.x + 1, p.y + 1));
				surround.add(new Point(p.x + 1, p.y));
			}
		} else if (p.x == myWorld.numRows() - 1) {
			// at bottom row
			if (p.y == 0) {
				// if at bottom left corner
				// System.out.println("BOT LEFT");
				surround.add(new Point(p.x, p.y + 1));
				surround.add(new Point(p.x - 1, p.y + 1));
				surround.add(new Point(p.x - 1, p.y));
			} else if (p.y == myWorld.numCols() - 1) {
				// if at bottom right corner
				// System.out.println("BOT RIGHT");
				surround.add(new Point(p.x, p.y - 1));
				surround.add(new Point(p.x - 1, p.y - 1));
				surround.add(new Point(p.x - 1, p.y));
			} else {
				// if at bottom middle points
				// System.out.println("BOT MIDDLE");
				surround.add(new Point(p.x, p.y - 1));
				surround.add(new Point(p.x, p.y + 1));
				surround.add(new Point(p.x - 1, p.y - 1));
				surround.add(new Point(p.x - 1, p.y + 1));
				surround.add(new Point(p.x - 1, p.y));
			}
		} else if (p.y == 0) {
			// if at left middle
			// System.out.println("LEFT MIDDLE");
			surround.add(new Point(p.x, p.y + 1));

			surround.add(new Point(p.x - 1, p.y));
			surround.add(new Point(p.x - 1, p.y + 1));

			surround.add(new Point(p.x + 1, p.y));
			surround.add(new Point(p.x + 1, p.y + 1));
		} else if (p.y == myWorld.numCols() - 1) {
			// if at right middle
			// System.out.println("RIGHT MIDDLE");
			surround.add(new Point(p.x, p.y - 1));

			surround.add(new Point(p.x - 1, p.y - 1));
			surround.add(new Point(p.x - 1, p.y));

			surround.add(new Point(p.x + 1, p.y - 1));
			surround.add(new Point(p.x + 1, p.y));

		} else {
			// Normal case when not on the edges
			// System.out.println("NOT ON THE EDGE");
			surround.add(new Point(p.x, p.y - 1));
			surround.add(new Point(p.x, p.y + 1));

			surround.add(new Point(p.x - 1, p.y - 1));
			surround.add(new Point(p.x - 1, p.y));
			surround.add(new Point(p.x - 1, p.y + 1));

			surround.add(new Point(p.x + 1, p.y - 1));
			surround.add(new Point(p.x + 1, p.y));
			surround.add(new Point(p.x + 1, p.y + 1));
		}
		surround.sort((o1, o2) -> Double.compare(dist[o1.x][o1.y],
				dist[o2.x][o2.y]));
		return surround;
	}

	public static double[][] evalDist(World myWorld) {
		double[][] map = new double[myWorld.numRows()][myWorld.numCols()];

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = calcDist(new Point(i, j), myWorld.getEndPos());
			}
		}

		// for (int i = 0; i < map.length; i++) {
		// for (int j = 0; j < map[i].length; j++) {
		// System.out.print(String.format("%.2f", (double) map[i][j])
		// + ", ");
		// }
		// System.out.println();
		// }
		return map;
	}

	public static double calcDist(Point currPos, Point endPos) {

		return endPos.distance(currPos);
	}

}
