# AI_HW2
Pathfinder A* for CS4710

## Background
A path finding system within a basic simulator. The simulator code was provided. The program takes in a *.txt file consisting map information.

**Rules:** The robot can go all 8 directions for one block. Robot starts at the starting point. 

**Goal:** Move the robot to the ending point in the shortest path possible. 

**Uncertainty:** The robot may have imperfect vision. 

**Language:** This program is coded in Java.

## Imperfect Vision
In order to simulate uncertainty, the robot can be switched to "uncertain mode". *Under uncertain mode,* it is possible for the robot to receive incorrect information about its surroundings, the further it detects, the more incorrect it would be. 

For instance, if the robot is looking at 1 block to its left, under "certain mode", it would return the correct result; under "uncertain mode", there is a small chance it may return the incorrect result. There is bigger chance it returns the incorrect result if it were look at 10 blocks away.

