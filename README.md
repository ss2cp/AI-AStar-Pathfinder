# AI_HW2
Pathfinder A* for CS4710

## Background
A* path finding system within a basic simulator. The simulator code was provided. The program takes in a *.txt file consisting map information.

**Rules:** The robot can go all 8 directions for one block. Robot starts at the starting point. 

**Goal:** Move the robot to the ending point in the shortest path possible. 

**Uncertainty:** The robot may have imperfect vision. 

**Language:** This program is coded in Java.

## Imperfect Vision
In order to simulate uncertainty, the robot can be switched to *"uncertain mode"*. Under uncertain mode, it is possible for the robot to receive incorrect information about its surroundings, the further it detects, the more incorrect it would be. 

For instance, if the robot is looking at 1 block to its left, under *"certain mode"*, it would return the correct result; under "uncertain mode", there is a small chance it may return the incorrect result. There is bigger chance it returns the incorrect result if it were look at 10 blocks away.

## Testing Levels
####Level 1
![alt tag](https://raw.githubusercontent.com/ss2cp/AI_HW2/master/Results/Level_1.png)
####Level 2
![alt tag](https://raw.githubusercontent.com/ss2cp/AI_HW2/master/Results/Level_2.png)
####Level 3
![alt tag](https://raw.githubusercontent.com/ss2cp/AI_HW2/master/Results/Level_3.png)
####Level 4
![alt tag](https://raw.githubusercontent.com/ss2cp/AI_HW2/master/Results/Level_4.png)
####Spiral
![alt tag](https://raw.githubusercontent.com/ss2cp/AI_HW2/master/Results/Spiral.png)

## Calculating Shortest Path (Level 4)
####Sensing the surrounding blocks toward the goal point
![alt tag](https://raw.githubusercontent.com/ss2cp/AI_HW2/master/Results/Level_4.png)
![alt tag](https://raw.githubusercontent.com/ss2cp/AI_HW2/master/Results/Level_4_1.png)
![alt tag](https://raw.githubusercontent.com/ss2cp/AI_HW2/master/Results/Level_4_2.png)
![alt tag](https://raw.githubusercontent.com/ss2cp/AI_HW2/master/Results/Level_4_3.png)
####Start moving through the shortest path
![alt tag](https://raw.githubusercontent.com/ss2cp/AI_HW2/master/Results/Level_4_4.png)
![alt tag](https://raw.githubusercontent.com/ss2cp/AI_HW2/master/Results/Level_4_5.png)

## Results
Under *certain mode*, the robot is guaranteed to find the optimal path and travel to the destination. 
The result is in the following tables:

####*Certain Mode* Before Optimizations
| Test Case  | # of Blocks in World |# of Moves|# of Pings|Ping coverage= pings/blocks|
| ------------- | ------------- | ------------- | ------------- |------------- |
| 1  | 8  |3|11|137.500%|
| 2  | 32 |7|37|115.625%|
|3|120|13|179|149.167%|
|4|400|38|334|83.500%
|Spiral|100|28|110|110.000%

####*Certain Mode* After Optimizations
| Test Case  | # of Blocks in World |# of Moves|# of Pings|Ping coverage= pings/blocks|
| ------------- | ------------- | ------------- | ------------- |------------- |
|1|8|3|5|62.500%|
|2|32|7|26|81.250%|
|3|120|13|79|65.833%|
|4|400|38|192|48.000%|
|Spiral|100|28|97|97.000%|

####Pings of Uncertain Maps and Certain Maps
| Test Case  | # of Blocks|Optimal # of Pings on *Certain* Maps|Average # of Pings on *Uncertain* Maps|Ping Multiplier= pings on uncertain maps / pings on certain maps|
| ------------- | ------------- | ------------- | ------------- |------------- |
|1|8|5|2.135|0.427|
|2|32|26|24.840|0.955|
|3|120|79|353.045|4.469|
|4|400|192|1732.435|9.023|
|Spiral|100|97|543.295|5.601|

####Moves of Uncertain Maps and Certain Maps
| Test Case  | # of Blocks|Optimal # of Moves on *Certain* Maps|Average # of Moves on *Uncertain* Maps|Move Multiplier= moves on uncertain maps / moves on certain maps|
| ------------- | ------------- | ------------- | ------------- |------------- |
|1|8|3|3.000|1.000|
|2|32|7|8.940|1.277|
|3|120|13|45.650|3.512|
|4|400|38|83.150|2.190|
|Spiral|100|28|84.945|3.034|



