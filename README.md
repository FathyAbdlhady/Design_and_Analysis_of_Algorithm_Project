## What is Required in every Task?
1. Problem description.
   
2. Detailed assumptions.

3. Detailed solution including the pseudo-code and the description of the steps of your solution.
   
4. Complexity analysis for the algorithm.

5. Code in java
 
6. A comparison between your algorithm and at least one other technique that can be used to solve the problem.
 
7. Sample output of the solution for the different cases of the technique with proper description for the output.
 
8. Conclusion.

### Teams

Team 1 : 1) Omar Saleh.  2) Youssef Ashmawy.  3) Omar Gamal

Team 1 Tasks : Task 2 and 3

Team 2 : 1)Ahmed Mahfouz. 2)Mohamed Mostafa  3) Abdallah Mohamed

Team 2 Tasks : Task 5 and 6 

Team 3 : 1) Fathy Abdlhady. 2) Youssef Shawky. 3) Mohamed Ayman

Team 3 Tasks : Task 8 and 1

### Task 1
Devise an algorithm for the following task: given a 2n × 2n (n > 1) board with one missing square, tile it with right trominoes of only three colors so that no pair of trominoes that share an edge have the same color. Recall that the right tromino is an L-shaped tile formed by three adjacent squares.

Use dynamic programing to solve this problem.

#### Problem 119 in Algorithmic Puzzles book

#### Working on it: 


### Task 2
Is it possible for a chess knight to visit all the cells of an 8 × 8 chessboard exactly once, ending at a cell one knight’s move away from the starting cell? (Such a tour is called closed or re-entrant. Note that a cell is considered visited only when the knight lands on it, not just passes over it on its move.)

If it is possible design a greedy algorithm to find the minimum number of moves the chess knight needs.

It is known as : closed knight's tour

#### Problem 127 in Algorithmic Puzzles book

#### Working on it: 


### Task 3
There is a row of n security switches protecting a military installation entrance. The switches can be manipulated as follows:

(i)The rightmost switch may be turned on or off at will.
    
(ii)Any other switch may be turned on or off only if the switch to its immediate right is on and all the other switches to its right, if any, are off.
    
(iii)Only one switch may be toggled at a time.
    
Design a Dynamic Programing algorithm to turn off all the switches, which are initially all on, in the minimum number of moves. (Toggling one switch is considered one move.) Also find the minimum number of moves.

#### Problem 128 in Algorithmic Puzzles book

#### Working on it: 


### Task 4
There are eight disks of different sizes and four pegs. Initially, all the disks are on the first peg in order of size, the largest on the bottom and the smallest on the top.

Use divide and conquer method to transfer all the disks to another peg by a sequence of moves. Only one disk can be moved at a time, and it is forbidden to place a larger disk on top of a smaller one.

Does the Dynamic Programing algorithm can solve the puzzle in 33 moves? If not then design an algorithm that solves the puzzle in 33 moves.

known as: reve's puzzle

it is a tower of hanoi modified problem

#### Problem 129 in Algorithmic Puzzles book

#### Working on it: 


### Task 5
There are n coins placed in a row. The goal is to form n/2 pairs of them by a sequence of moves. On the first move a single coin has to jump over one coin adjacent to it, on the second move a single coin has to jump over two adjacent coins, on the third move a single coin has to jump over three adjacent coins, and so on, until after n/2 moves n/2 coin pairs are formed. (On each move, a coin can jump right or left but it has to land on a single coin. Jumping over a coin pair counts as jumping over two coins. Any empty space between adjacent coins is ignored.) 

Determine all the values of n for which the problem has a solution and design an algorithm that solves it in the minimum number of moves for those n’s.

Design greedy algorithm to find the minimum number of moves.

#### Problem 137 in Algorithmic Puzzles book

#### Working on it: 


### Task 6
There are six knights on a 3 × 4 chessboard: the three white knights are at the bottom row, and the three black knights are at the top row.

Design an iterative improvement algorithm to exchange the knights to get the position shown on the right of the figure in the minimum number of knights moves, not allowing more than one knight on a square at any time.

known as: guarini's problem

#### Problem 118 in Algorithmic Puzzles book

#### Working on it: 


### Task 7
A computer game has a shooter and a moving target. The shooter can hit any of n > 1 hiding spot located along a straight line in which the target can hide. The shooter can never see the target; all he knows is that the target moves to an adjacent hiding spot between every two consecutive shots. 

Design a Dynamic Programing algorithm that guarantees hitting the target.

#### Problem 146 in Algorithmic Puzzles book

#### Working on it: 


### Task 8
If you have 50 boxes that contains 50 pieces of metal all of the same known weight. one of these boxes contains fake metal pieces that weigh 1 kilogram less than the pieces in the rest of the boxes. 

You can use a digital scale only once to find this fake box.

Design a brute force algorithm to solve this problem.

#### Working on it: 


