#include <iostream>
#include <vector>
#include <unordered_set>
#include <algorithm>
#include <list>
#include <queue>
#include <bits/stdc++.h>
using namespace std;
#define COLS 3
#define ROWS 4
#define BLACK -1
#define WHITE 1

void printBoard(const vector<vector<int>> &board);
int calculateHeuristic(const vector<vector<int>> &board);
void bestFirstSearch(vector<vector<int>> &initialState);
bool isGoalState(const vector<vector<int>> &board);
vector<pair<int, int>> getKnightMoves(int x, int y, const vector<vector<int>> &board);
bool isEmptyAndWithinLimits(const vector<vector<int>> &board, int x, int y);

struct VectorVectorHash
{
    size_t operator()(const vector<vector<int>> &vec) const
    {
        size_t hash = 0;
        for (const auto &subvec : vec)
        {
            for (int num : subvec)
            {
                // Combine hash with the hash of each element
                hash ^= std::hash<int>{}(num) + 0x9e3779b9 + (hash << 6) + (hash >> 2);
            }
        }
        return hash;
    }
};

class State
{
public:
    vector<vector<int>> board;
    int cost = 0; // Cost of reaching this state
    // string path;               // Path to reach this state
    int heuristic;        // Heuristic value for best-first search
    int moves;            // Total number of moves
    vector<State *> path; // Path leading to this state
    // Constructor
    State(vector<vector<int>> _board, int _heuristic, int _moves, vector<State *> _path) : board(_board), heuristic(_heuristic), moves(_moves), path(_path) {}

    // Overload comparison operator for priority queue
    bool operator<(const State &other) const
    {
        return cost + heuristic > other.cost + other.heuristic;
    }
    void printPath()
    {
        for (int i = 0; i < path.size(); i++)
        {
            printBoard(path[i]->board);
        }
    }
};
class Path
{
public:
    vector<State> path;
    Path(State s)
    {
        path.push_back(s);
    }
    void push(State s)
    {
        path.push_back(s);
    }
    void printPath()
    {
        for (int i = 0; i < path.size(); i++)
        {
            printBoard(path[i].board);
        }
    }
    bool operator<(const Path &other) const
    {
        return path[path.size()-1].heuristic > other.path[other.path.size()-1].heuristic;
    }
};

int main()
{
    srand(time(0));
    vector<vector<int>> initialState = {
        {BLACK, BLACK, BLACK},
        {0, 0, 0},
        {0, 0, 0},
        {WHITE, WHITE, WHITE}};

    // bestFirstSearch(initialState);
    

    // std::vector<std::vector<int>> graph = {
    //     {5},
    //     {6, 8},
    //     {3},
    //     {8, 10},
    //     {},
    //     {6, 10},
    //     {1, 11},
    //     {},
    //     {1, 9},
    //     {8}};
}

void printBoard(const vector<vector<int>> &board)
{
    for (int i = 0; i < ROWS; i++)
    {
        cout << "\n {";
        for (int j = 0; j < COLS; j++)
        {
            if (board[i][j] == BLACK)
                cout << 'B' << " ";
            else if (board[i][j] == WHITE)
                cout << 'W' << " ";
            else
                cout << '.' << " ";
        }
        cout << "}\n";
    }
}

int calculateHeuristic(const vector<vector<int>> &board)
{
    // Calculate the number of misplaced knights
    int misplacedKnights = 0;
    for (int i = 0; i < ROWS; ++i)
    {
        for (int j = 0; j < COLS; ++j)
        {
            if (board[i][j] != 0) // Ignore empty squares
            {
                if ((i == 0 && board[i][j] == WHITE) || ((i == ROWS - 1) && board[i][j] == BLACK))
                {
                    continue;
                }
                else
                {
                    misplacedKnights++; // Count misplaced knights
                }
            }
        }
    }
    return misplacedKnights;
}

//* Function to check if the given coordinates are within the board limits
bool withinLimits(int x, int y)
{
    return ((x >= 0 && y >= 0) && (x < ROWS && y < COLS));
}
// No problems ✅

//* Checks whether a square is valid and empty or not
bool isEmptyAndWithinLimits(const vector<vector<int>> &board, int x, int y)
{
    return (withinLimits(x, y)) && (board[x][y] == 0);
}
// No problems ✅

bool isGoalState(const vector<vector<int>> &board)
{
    if ((board[0][0] == WHITE && board[0][1] == WHITE && board[0][2] == WHITE) && (board[3][0] == BLACK && board[3][1] == BLACK && board[3][2] == BLACK))
        return true;
    return false;
}
// No problems ✅

vector<pair<int, int>> getKnightMoves(int x, int y, const vector<vector<int>> &board)
{
    vector<pair<int, int>> moves;
    vector<pair<int, int>> directions = {{-2, -1}, {-1, -2}, {1, -2}, {2, -1}, {2, 1}, {1, 2}, {-1, 2}, {-2, 1}};
    for (auto &dir : directions)
    {
        int newX = x + dir.first;
        int newY = y + dir.second;
        if (isEmptyAndWithinLimits(board, newX, newY))
            moves.push_back({newX, newY});
    }
    return moves;
}
// No problems ✅

void generateNextState(State current, unordered_set<vector<vector<int>>, VectorVectorHash> visited, priority_queue<State> &pq)
{
    for (int i = 0; i < ROWS; ++i)
    {
        for (int j = 0; j < COLS; ++j)
        {
            if (current.board[i][j] != 0)
            {
                // Knight present at this position
                // Generate knight moves
                vector<pair<int, int>> moves = getKnightMoves(i, j, current.board);
                // cout << "No of moves:" << moves.size() << endl;
                for (const auto &move : moves)
                {
                    // Create a copy of the current board
                    vector<vector<int>> nextBoard = current.board;

                    // Move the knight
                    swap(nextBoard[i][j], nextBoard[move.first][move.second]);
                    // cout << "Current board: ";
                    // printBoard(current.board);
                    // cout << "Swapped board \n Next board: ";
                    // printBoard(nextBoard);
                    // Check if the next state has been visited
                    if ((visited.find(nextBoard) == visited.end() && (current.moves + 1 <= 16)))
                    {
                        // Calculate heuristic for the next state
                        int nextHeuristic = calculateHeuristic(nextBoard);

                        // Create a copy of the path
                        vector<State *> nextPath = current.path;
                        nextPath.push_back(&current);

                        // Push the next state to the priority queue
                        pq.push(State(nextBoard, nextHeuristic, current.moves + 1, nextPath));

                        // Mark the next state as visited
                        visited.insert(nextBoard);
                    }
                }
            }
        }
    }
}
// Best-First Search algorithm
void bestFirstSearch(vector<vector<int>> &initialState)
{
    // Initialize priority queue for states
    priority_queue<State> pq;

    // Initialize set to keep track of visited states
    unordered_set<vector<vector<int>>, VectorVectorHash> visited({}); // return to hash for original code in second template variable

    // Calculate heuristic for initial state
    int initialHeuristic = calculateHeuristic(initialState);

    // Push initial state to priority queue
    pq.push(State(initialState, initialHeuristic, 0, {}));

    // Perform Best-First Search
    while (!pq.empty())
    {
        // Get the state with the lowest heuristic value
        State current = pq.top();
        pq.pop();

        // Check if the current state is the goal state
        if (isGoalState(current.board))
        {
            cout << "Solution found:" << endl;
            printBoard(current.board);
            cout << "Total moves: " << current.moves << endl;
            cout << "Path: ";
            current.printPath();
            cout << endl;
            return;
        }

        // Generate next states
        generateNextState(current, visited, pq);
    }

    cout << "No solution found." << endl;
}

/* Graph
0:{5}
1:{6,8}
2:{3}
3:{8,10}
4:{}
5:{6,10}
6:{1,11}
7{}
8:{1,9}
9:{8}
*/
