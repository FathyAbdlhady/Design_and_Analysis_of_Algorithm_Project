#include <iostream>
#include <vector>
#include <unordered_set>
#include <algorithm>
#include <list>
#include <queue>
#include <bits/stdc++.h>
#include <chrono>
using namespace std;

#define COLS 3
#define ROWS 4
#define BLACK -1
#define WHITE 1
#define MAX_MOVES 16

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
    int cost = 0;  // Cost of reaching this state
    int heuristic; // Heuristic value for best-first search
    // Constructor
    State(vector<vector<int>> _board, int _heuristic) : board(_board), heuristic(_heuristic) {}

    // Overload comparison operator for priority queue
    bool operator<(const State &other) const
    {
        return cost + heuristic > other.cost + other.heuristic;
    }

    bool isSameBoard(State otherState)
    {
        for (int i = 0; i < ROWS; i++)
        {
            for (int j = 0; j < COLS; j++)
            {
                if (board[i][j] != otherState.board[i][j])
                    return false;
            }
        }
        return true;
    }
};
class Path
{
public:
    vector<State> path;

    Path(State s)
    {
        this->path.push_back(s);
    }
    void push(State s)
    {
        this->path.push_back(s);
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
        bool compare = this->path[this->path.size() - 1].heuristic > other.path[other.path.size() - 1].heuristic;
        return compare;
    }
    bool isSamePath(Path otherPath)
    {
        if (this->path.size() != otherPath.path.size())
            return false;
        for (int i = 0; i < path.size(); i++)
            if (!this->path[i].isSameBoard(otherPath.path[i]))
                return false;
        return true;
    }
    bool hasDupeStates(State currentState)
    {
        for (int i = 0; i < this->path.size(); i++)
            if (currentState.isSameBoard(this->path[i]))
                return true;
        return false;
    }
    int length()
    {
        return this->path.size();
    }
};

int main()
{
    srand(time(0));
    vector<Path> pqVector;
    vector<vector<int>> initialState = {
        {BLACK, BLACK, BLACK},
        {0, 0, 0},
        {0, WHITE, 0},
        {WHITE, WHITE, 0}};
    // vector<vector<int>> initialState = {
    //     {BLACK, BLACK, 0},
    //     {BLACK, 0, 0},
    //     {0, 0, WHITE},
    //     {0, WHITE, WHITE}};

    auto start = std::chrono::high_resolution_clock::now();
    bestFirstSearch(initialState);
    auto end = std::chrono::high_resolution_clock::now();
    auto duration = end - start;
    auto minutes = std::chrono::duration_cast<std::chrono::seconds>(duration).count();

    std::cout << "\n\nExecution time: " << minutes << " second\n";
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
    int heuristic = 0;

    // Define the goal positions for white and black knights
    vector<pair<int, int>> whiteGoalPositions = {{0, 0}, {0, 1}, {0, 2}};
    vector<pair<int, int>> blackGoalPositions = {{3, 0}, {3, 1}, {3, 2}};

    // Iterate over the board to calculate the heuristic
    for (int i = 0; i < ROWS; ++i)
    {
        for (int j = 0; j < COLS; ++j)
        {
            // For white knights
            if (board[i][j] == WHITE)
            {
                // Calculate the Manhattan distance to the nearest goal position
                int minDist = INT_MAX;
                for (const auto &goalPos : whiteGoalPositions)
                {
                    int dist = abs(i - goalPos.first) + abs(j - goalPos.second);
                    minDist = min(minDist, dist);
                }
                heuristic += minDist;
            }
            // For black knights
            else if (board[i][j] == BLACK)
            {
                // Calculate the Manhattan distance to the nearest goal position
                int minDist = INT_MAX;
                for (const auto &goalPos : blackGoalPositions)
                {
                    int dist = abs(i - goalPos.first) + abs(j - goalPos.second);
                    minDist = min(minDist, dist);
                }
                heuristic += minDist;
            }
        }
    }
    if (isGoalState(board))
    {
        return -1;
    }
    return heuristic / 3.0 + 3;
}

//* Function to check if the given coordinates are within the board limits
bool withinLimits(int x, int y)
{
    return ((x >= 0 && y >= 0) && (x < ROWS && y < COLS));
}
// No problems

//* Checks whether a square is valid and empty or not
bool isEmptyAndWithinLimits(const vector<vector<int>> &board, int x, int y)
{
    return (withinLimits(x, y)) && (board[x][y] == 0);
}
// No problems

bool isGoalState(const vector<vector<int>> &board)
{
    if ((board[0][0] == WHITE && board[0][1] == WHITE && board[0][2] == WHITE) && (board[3][0] == BLACK && board[3][1] == BLACK && board[3][2] == BLACK))
        return true;
    return false;
}
// No problems

vector<pair<int, int>> getKnightMoves(int x, int y, const vector<vector<int>> &board)
{
    vector<pair<int, int>> moves;
    vector<pair<int, int>> directions = {{1, 2}, {-2, 1}, {-2, -1}, {-1, -2}, {1, -2}, {2, -1}, {2, 1}, {-1, 2}};

    for (auto &dir : directions)
    {
        int newX = x + dir.first;
        int newY = y + dir.second;
        if (isEmptyAndWithinLimits(board, newX, newY))
            moves.push_back({newX, newY});
    }
    return moves;
}
// No problems

void generateNextState(State currentState, priority_queue<Path> &pq, Path currentPath)
{
    for (int i = 0; i < ROWS; ++i)
    {
        for (int j = 0; j < COLS; ++j)
        {
            if (currentState.board[i][j] != 0)
            {
                // Knight present at this position
                // Generate knight moves
                vector<pair<int, int>> moves = getKnightMoves(i, j, currentState.board);
                // cout << "No of moves:" << moves.size() << endl;
                for (const auto &move : moves)
                {
                    // Create a copy of the current board
                    vector<vector<int>> nextBoard = currentState.board;

                    // Move the knight
                    swap(nextBoard[i][j], nextBoard[move.first][move.second]);
                    // Calculate heuristic for the next state
                    int nextHeuristic = calculateHeuristic(nextBoard);

                    // Create a copy of the path
                    Path nextPath = currentPath;
                    State nextState = State(nextBoard, nextHeuristic);
                    if (!currentPath.hasDupeStates(nextState))
                    {
                        cout << "Current board: ";
                        printBoard(currentState.board);
                        cout << "Swapped board \n Next board: ";
                        printBoard(nextBoard);
                        // Check if the next state has been visited
                        nextPath.push(nextState);
                        // Push the next state to the priority queue
                        // condition checking
                        // pqVector.push_back(nextPath);
                        pq.push(nextPath);
                    }

                    // Mark the next state as visited
                    // visited.insert(nextBoard);
                }
            }
        }
    }
}

// Best-First Search algorithm
void bestFirstSearch(vector<vector<int>> &initialState)
{
    // Initialize priority queue for states
    priority_queue<Path> pq;
    vector<Path> pqVector;

    // Calculate heuristic for initial state
    int initialHeuristic = calculateHeuristic(initialState);

    // Push initial path to priority queue
    State beginningState = State(initialState, initialHeuristic);
    Path initialPath = Path(beginningState);
    pq.push(initialPath);
    pqVector.push_back(initialPath);

    // Perform Best-First Search
    while (!pq.empty())
    {
        // Get the state with the lowest heuristic value
        Path currentPath = pq.top();
        State currentState = currentPath.path[currentPath.path.size() - 1];
        pq.pop();

        // Check if the current state is the goal state
        if (isGoalState(currentState.board) || currentState.heuristic == -1)
        {
            cout << "\n\nSolution found:" << endl;
            printBoard(currentState.board);
            cout << "Path: ";
            currentPath.printPath();
            cout << endl;
            cout << "Total moves: " << currentPath.length() - 1 << endl;
            cout << "Total paths: " << pq.size() << endl;
            return;
        }

        // Generate next states
        // cout << "\nGenerate States:\n"
        //      << "Path Length:" << currentPath.length() << endl;
        if (currentPath.length() <= MAX_MOVES)
            generateNextState(currentState, pq, currentPath);
        cout << "Total paths: " << pq.size() << endl;
    }

    cout << "No solution found." << endl;
}
