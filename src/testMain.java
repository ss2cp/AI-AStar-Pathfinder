import world.World;

public class testMain {

	public static void main(String[] args) {

		try {
			// initiate a new world
			World myWorld = new World("myInputFile0.txt", true);
//			myWorld.createGUI(50 * myWorld.numCols(), 50 * myWorld.numRows(), 1);

			// initiate a new robot
			// MyRobotClass myRobot = new MyRobotClass();

			MySecondRobotClass myRobot = new MySecondRobotClass();

			// add robot to the world
			myRobot.addToWorld(myWorld);

			myRobot.travelToDestination();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
