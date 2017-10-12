# CS440 Intro to AI Spring 2017
### Assignment 1: Heuristic Search Through Procedurally Generated 2D Maps
By Mike Allen and Jarisha Olanday

## Description
Java Program that procedurally generates a 2D Map of 4 different types of terrain(normal, hard-to-traverse, blocked, and highway) with a randomly generated start and endpoint. Using heuristic search algorthims (A*), the shortest path from the start to endpoint is found.

## Program Design & Implementation
This section is a guide to our design and implementation of our pathfinding project. Our code for this project is written in Java, which allows us to easily take advantage of the JFrame, javax.swing, java.awt, and related packages. These features in Java are what allowed us to create our graphical user interface (GUI) in an effective and relatively simple manner. Our code consists of a few main classes, each described in detail below.

`Asst1Driver.java`
This class is the heart of our project. This class manages exactly what runs, and how it runs it. The driver contains the first of our two JFrames for our GUI, which contains the buttons for selecting the various maps, heuristics, and benchmarks for display/testing. It does this by keeping track of variables, which determine what methods are called. Each map has it’s own method, and depending on the button pressed, the GUI will spawn a new JFrame with the map represented by the button hit. Based on the other buttons the user may push, this changes the condition variables, resulting in a new window appearing with the updated visualized map. 

`Cell.java`
The Cell.java class is what we use to create Cell objects. Cell objects are used to populate the 2-D array that represents our “grid world” in this particular project. Each coordinate of the grid world 2-D array is populated with a Cell, which contains all the properties of that particular coordinate. Each Cell contains information like the X and Y values, the H values, if it’s part of the path to the goal, etc. Each cell also contains a reference to its parent, as well as a list of neighbors, which are both useful in the pathfinding algorithms.

`Map.java`
Map.java contains vital information that is used throughout the various classes of our project. The Map.java class is where the map is created/read in from a source document (.txt). The algorithms in this class take the document, loop through it, and read in each cell from the source document, generating a map that our algorithms and interpret and use. For each coordinate point in the grid, this algorithm creates a Cell (using our Cell.java class), and stores it within that point. This allows us for easy, organized access to each point in the grid and what information lies within it. The Map class also contains helper methods for generating difficult terrain, highways, and other map features.

`AStar.java`
AStar.java contains the actual algorithms for calculating the path through the map that is given to it via parameters. The AStar.java class contains the functionality to calculate a path using our different heuristics, which will be discussed in the “Heuristics” section below. This class also contains functionality to calculate the path using the Weighted A* algorithm, as well as Uniform-Cost Search.

`Grid.java`
Grid.java contains the second of our two JFrames that we’ve created for this project. The JFrame that is created when a Grid is created is the visual representation of our map, as well as an interactive GUI that allows information from each Cell to be viewed. The Grid class is also responsible for coloring each cell, which includes coloring the path when it is found, as well as coloring the start and goal nodes (green and red, respectively)

## Optimization
Our algorithm has been optimized by using various data structures to speed up the process of finding the correct path in a more organized manner. We implemented a cellID system, which allows for easy comparisons between cells. Also, by using various data structures, such as the priority queue and ArrayLists, it makes it easy to store and locate various objects that we create (using the contains(…) method for ArrayLists, for example). As for the algorithm itself, when we create the map, we pre-calculate all the h values and store them in the Cells, making the algorithm itself more simple because it doesn’t have to calculate the H values every time. We also created a good way to get the various neighbors of the Cells, using a system of checks to get each neighbor and load it into an array.

## Heuristics
For this particular project, we decided upon 5 different heuristics – one admissible heuristic, and four other non-admissible heuristics.

**Admissible Heuristic**
An admissible heuristic is one that never overestimates the cost of reaching the goal. In our case, our admissible heuristic is Manhattan distance. The reasoning behind this is that when calculating Manhattan Distance, it takes into account each Cell independently, meaning that it will never overestimate the cost.

**Inadmissible Heuristics**
The other heuristics we used are as follows:
-	Euclidian distance
-	Euclidian distance divided by 4
-	Manhattan distance divided by 4
-	Distance to nearest difficult terrain center
All of these heuristics are relatively inexpensive to compute, as most of them just require the application of some type of distance formula, which simply computes using two pairs of coordinates. These choices were chosen as they truly help guide the algorithm to the goal node effectively, especially when using raw distance to the goal nodes (as the algorithm gets closer, the h values get lower). This simple application of heuristics, even though not the most accurate, justify the use of the these inadmissible heuristics. 

