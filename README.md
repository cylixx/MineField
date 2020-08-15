
## Mine Field Challenge


There are bunch of mines in a mine field, and you are tasked with exploding as many of them as you can.  The only caveat is you can only explode one manually.  The mine you manually explode will set off a chain reaction.  For any mine that explodes, all mines within the blast radius of that mine will be triggered to explode in one second.  The mine you manually explode blows up at time 0.

Your Task: 
Write a program which will read in a mines file and output the Maximum number of mines you can explode.  
Also output which mine you need to manually set off to explode this maximum number.  Since there may be multiple mines that blow up a maximal number of mines you should output all the mines that do that.

We'll provide you with:

Mines File (space separated) with values:
(x, y, r) - where x is x coordinate, y is y coordinate and r is blast radius.

**Example:** 
  
1 2 53   
-32 40 100   
10 15 25   
-15 -15 200

Formula to determine if a mine explodes another mine:

> 	static boolean mine_exploded(Float x1, Float y1, Float r, Float x2, Float y2) {
		 return r <= Math.sqrt(Math.pow((x2-x1),2) + Math.pow((y2-y1),2));
	}


#### Solution:

The problem consists of different mines in an n x n matrix, this can be represented as a graph where each mine represents an edge and the formula to calculate the distance between two mines will indicate whether there is a path between them. That way we can know which are the mines adjacent to said mine; in the same way, for each adjacent mine or rather each mine that has exploited, we must find its adjacent mines and keep the sum of the total of mines. Repeat the above for each mine and compare totals to determine which was the mine with the highest number of exploited mines (highest number of paths of all its adjacent mines).
  
These were the steps I followed:
- Read the file in the path **src/main/resources/mines.txt**  and load it into a memory map and thus be able to access each mine.
- Take a random mine and iterate over the rest to see if there is a path between the two, if there is then put the adjacent mine in a queue with adjacent mines (mines that have exploded). At the same time I keep a list of the mines that have exploded.
- Repeat the process for each mine in the queue of adjacent mines until there are no more mines in the queue. Keep the count of how many adjacent mines we have found in a variable.
- At the end, compare the total of mines exploited by each mine and take the maximum of all.


#### To Run this project locally


> $ git clone https://github.com/cylixx/MineField.git   
$ mvn clean install   
or to run the test:  
$ mvn test

_Note: To add more test cases modify the file src/main/resources/mines.txt and add mines. After that run the above commands or execute directly the class named Solution.java_ 
