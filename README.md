# Design and Analysis of Algorithm Project 

## What is Required in every Task
1. Problem description.
   
2. Detailed assumptions.

3. Detailed solution including the pseudo-code and the description of the steps of your solution.
   
4. Complexity analysis for the algorithm.

5. Code in java
 
6. Sample output of the solution for the different cases of the technique with proper description for the output.

7. A comparison between your algorithm and at least one other technique that can be used to solve the problem.
 
8. Conclusion.


## Task 1
### Dynamic Programing
Devise an algorithm for the following task: 
- Given a 2n × 2n (n > 1) board with one missing square, tile it with right trominoes of only three colors so that no pair of trominoes that share an edge have the same color.
- Recall that the right tromino is an L-shaped tile formed by three adjacent squares.

#### Problem 119 in Algorithmic Puzzles book


## Task 2
### Greedy 
- Is it possible for a chess knight to visit all the cells of an 8 × 8 chessboard exactly once, ending at a cell one knight’s move away from the starting cell? (Such a tour is called closed or re-entrant. Note that a cell is considered visited only when the knight lands on it, not just passes over it on its move.)

- If it is possible design a Greedy algorithm to find the minimum number of moves the chess knight needs.

##### It is known as: Closed knight's tour

#### Problem 127 in Algorithmic Puzzles book


## Task 3
### Dynamic Programing
There is a row of n security switches protecting a military installation entrance.<br> The switches can be manipulated as follows:

- The rightmost switch may be turned on or off at will.
    
- Any other switch may be turned on or off only if the switch to its immediate right is on and all the other switches to its right, if any, are off.
    
- Only one switch may be toggled at a time.
    
Design a Dynamic Programing algorithm to turn off all the switches, which are initially all on, in the minimum number of moves. (Toggling one switch is considered one move.) Also find the minimum number of moves.

#### Problem 128 in Algorithmic Puzzles book

## Task 4
### 1) Divide and Conquer 
### 2) Dynamic Programming
- There are eight disks of different sizes and four pegs. Initially, all the disks are on the first peg in order of size, the largest on the bottom and the smallest on the top.

- Use divide and conquer method to transfer all the disks to another peg by a sequence of moves. Only one disk can be moved at a time, and it is forbidden to place a larger disk on top of a smaller one.

- Can Dynamic Programing algorithm solve the puzzle in 33 moves? If not then design an algorithm that solves the puzzle in 33 moves.

##### known as: reve's puzzle

###### It is a modified version of tower of hanoi problem 

#### Problem 129 in Algorithmic Puzzles book


## Task 5
### Greedy
- There are \( n \) coins placed in a row.
- The goal is to form \( n/2 \) pairs of coins through a sequence of moves.
- Move sequence:
  - On the first move, a single coin must jump over one adjacent coin.
  - On the second move, a single coin must jump over two adjacent coins.
  - On the third move, a single coin must jump over three adjacent coins.
  - This pattern continues until, after \( n/2 \) moves, \( n/2 \) coin pairs are formed.
- Movement rules:
  - A coin can jump right or left but must land on a single coin.
  - Jumping over a coin pair counts as jumping over two coins.
  - Any empty space between adjacent coins is ignored.
- Determine all values of \( n \) for which the problem has a solution.
- Design an algorithm that solves the problem in the minimum number of moves for those \( n \).
- Design a greedy algorithm to find the minimum number of moves.

#### Problem 137 in Algorithmic Puzzles book

## Task 6
### Iterative Improvement
There are six knights on a 3 × 4 chessboard: the three white knights are at the bottom row, and the three black knights are at the top row.

Design an iterative improvement algorithm to exchange the knights to get the position shown on the right of the figure in the minimum number of knights moves, not allowing more than one knight on a square at any time.

##### Known as: guarini's problem

#### Problem 118 in Algorithmic Puzzles book

## Task 7
### Dynamic Programing
A computer game has a shooter and a moving target. The shooter can hit any of n > 1 hiding spot located along a straight line in which the target can hide. The shooter can never see the target; all he knows is that the target moves to an adjacent hiding spot between every two consecutive shots. Design a Dynamic Programing algorithm that guarantees hitting the target.

#### Problem 146 in Algorithmic Puzzles book

## Task 8 
### brute force approach
If you have 50 boxes that contains 50 pieces of metal all of the same known weight. one of these boxes contains fake metal pieces that weigh 1 kilogram less than the pieces in the rest of the boxes.You can use a digital scale only once to find this fake box.

## Languages used
- C/C++
- Java


# Team Members
| #  | Name                                                                      |   ID    |  Tasks   |
| -  |------------- | ------- | ------- | 
| 01 | [Fathy Abdlhady Fathy](https://github.com/FathyAbdlhady)                  | 2001152 | 1, 7, 8  |
| 02 | [Youssef Wael Hamdy Ibrahim Ashmawy](https://github.com/youssefashmawy)   | 2001430 |   2, 6   |
| 03 | [Yousef Shawky Mohamed](https://github.com/thedarkevil987)                | 2001500 |   7, 8   |
| 04 | [Omar Saleh Mohamed Abdo](https://github.com/MrMariodude)                 | 2001993 |   2, 3   |
| 05 | [Ahmad Youssef Mansour Mahfouz](https://github.com/rye141200)             | 2002238 |    6     |
| 06 | [Heba Maher Abdelrahman](https://github.com/hebamaher)                    | 2001400 |    4     |
| 07 | [Mark Ramy Fathy](https://github.com/markramy23)                          | 2000923 |    4     |
| 08 | [Abdallah Mohamed](https://github.com/AntiHexCodeII)                      | 2001803 |    5     |
| 09 | [Mohamed Ayman Mohamed](https://github.com)                               | 2001048 |   1, 7   |  
| 10 | [Omar Nader Ahmed](https://github.com/ElecSpartan)                        | 2001714 |    3     |
| 11 | [Mohamed Mostafa Mahmoud](https://github.com/mohamed-most)                | 2001299 |    5     |
