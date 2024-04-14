#include <bits/stdc++.h> 

using namespace std;

constexpr int N = 8; //* The size of the chessboard

//* Patterns for the Knight's moves
static int moveX[N] = {1, 1, 2, 2, -1, -1, -2, -2};
static int moveY[N] = {2, -2, 1, -1, 2, -2, 1, -1};

//* Function to check if the given coordinates are within the board limits
bool withinLimits(int x, int y)
{
    return ((x >= 0 && y >= 0) && (x < N && y < N));
}

//* Checks whether a square is valid and empty or not
bool isEmpty(int board[N][N], int x, int y)
{
    return (withinLimits(x, y)) && (board[x][y] < 0);
}

//* Returns the number of empty squares adjacent to (x, y)
int getDegree(int board[N][N], int x, int y)
{
    int count = 0;
    for (int i = 0; i < N; ++i)
        if (isEmpty(board, (x + moveX[i]), (y + moveY[i])))
            count++;

    return count;
}

/*
*Picks next point using Warnsdorff's heuristic, which is choosing the position with minimum number of valid moves
*Returns false if it is not possible to pick the next point.
*/
bool nextMove(int board[N][N], int *x, int *y)
{
    int minDegIdx = -1, c, minDeg = (N + 1), nextX, nextY;

    //* Try all possible moves from the current position
    int start = rand() % N; //* Randomize starting point this is where that approach might fall off
    for (int count = 0; count < N; ++count)
    {
        int i = (start + count) % N;
        nextX = *x + moveX[i];
        nextY = *y + moveY[i];
        
        if ((isEmpty(board, nextX, nextY)) && (c = getDegree(board, nextX, nextY)) < minDeg)
        {
            minDegIdx = i;
            minDeg = c;
        }
    }

    //* If we could not find a next valid move
    if (minDegIdx == -1)
        return false;

    nextX = *x + moveX[minDegIdx];
    nextY = *y + moveY[minDegIdx];

    board[nextX][nextY] = board[*x][*y] + 1;

    *x = nextX;
    *y = nextY;

    return true;
}

// *Displays the chessboard with all the legal knight's moves
void printBoard(int board[N][N])
{
    for (int i = 0; i < N; ++i)
    {
        for (int j = 0; j < N; ++j)
            cout << board[i][j] << "\t";
        cout << endl;
    }
}

/*
 *Checks if a square is a neighboring square
 *If the knight ends on a square that is one
 *knight's move from the beginning square,
 *then the tour is closed
 */
bool isNeighbour(int x, int y, int xx, int yy)
{
    for (int i = 0; i < N; ++i)
        if (((x + moveX[i]) == xx) && ((y + moveY[i]) == yy))
            return true;

    return false;
}

/*
*Generates the legal moves using Warnsdorff's heuristics.
*Returns false if not possible
*/
bool findClosedTour(int startX, int startY)
{
    // *Initialize the chessboard with -1's
    int board[N][N];
    for (int i = 0; i < N; ++i)
        for (int j = 0; j < N; ++j)
            board[i][j] = -1;

    int currentX = startX;
    int currentY = startY;
    board[currentX][currentY] = 0;

    for (int i = 0; i < N * N - 1; ++i)
        if (nextMove(board, &currentX, &currentY) == false)
            return false;

    //* Check if tour is closed (Can end at starting point)
    if (!isNeighbour(currentX, currentY, startX, startY))
        return false;

    printBoard(board);
    return true;
}

int main()
{
    srand(time(NULL));

    int startX, startY;
    cout << "Enter Starting position(X Y): ";
    cin >> startX >> startY;

    //! While we don't get a solution
    while (!findClosedTour(startX, startY))
    {
        //? Keep trying different starting positions until a closed tour is found
        ;
    }

    return 0;
}
