import java.util.*;

public class GuariniHard {

    private static final int COLS = 3;
    private static final int ROWS = 4;
    private static final int BLACK = -1;
    private static final int WHITE = 1;

    public static void main(String[] args) {
        Random rand = new Random();
        List<Path> pqVector = new ArrayList<>();
        List<List<Integer>> initialState = Arrays.asList(
            Arrays.asList(BLACK, BLACK, BLACK),
            Arrays.asList(0, 0, 0),
            Arrays.asList(0, 0, 0),
            Arrays.asList(WHITE, WHITE, WHITE)
        );

        bestFirstSearch(initialState);
    }

    //*Works successfully✅
    private static void printBoard(List<List<Integer>> board) {
        for (int i = 0; i < ROWS; i++) {
            System.out.print("\n {");
            for (int j = 0; j < COLS; j++) {
                if (board.get(i).get(j) == BLACK)
                    System.out.print('B' + " ");
                else if (board.get(i).get(j) == WHITE)
                    System.out.print('W' + " ");
                else
                    System.out.print('.' + " ");
            }
            System.out.print("}\n");
        }
    }
    //*Works successfully✅
    private static int calculateHeuristic(List<List<Integer>> board) {
        int misplacedKnights = 0;
        for (int i = 0; i < ROWS; ++i) {
            for (int j = 0; j < COLS; ++j) {
                if (board.get(i).get(j) != 0) { //!Knight stands here 😄 
                    if ((i == 0 && board.get(i).get(j) == WHITE) || ((i == ROWS - 1) && board.get(i).get(j) == BLACK)) {
                        continue;
                    } else {
                        misplacedKnights++;
                    }
                }
            }
        }
        return misplacedKnights;
    }

    //*Works successfully✅
    private static boolean withinLimits(int x, int y) {
        return ((x >= 0 && y >= 0) && (x < ROWS && y < COLS));
    }
    //*Works successfully✅
    private static boolean isEmptyAndWithinLimits(List<List<Integer>> board, int x, int y) {
        return (withinLimits(x, y)) && (board.get(x).get(y) == 0);
    }

    //*Works successfully✅
    private static boolean isGoalState(List<List<Integer>> board) {
        return (board.get(0).get(0) == WHITE && board.get(0).get(1) == WHITE && board.get(0).get(2) == WHITE) &&
               (board.get(3).get(0) == BLACK && board.get(3).get(1) == BLACK && board.get(3).get(2) == BLACK);
    }

    //*Works successfully✅
    private static List<Pair<Integer, Integer>> getKnightMoves(int x, int y, List<List<Integer>> board) {
        List<Pair<Integer, Integer>> moves = new ArrayList<>();
        List<Pair<Integer, Integer>> directions = Arrays.asList(
            new Pair(-2, -1), new Pair(-1, -2), new Pair(1, -2), new Pair(2, -1),
            new Pair(2, 1), new Pair(1, 2), new Pair(-1, 2), new Pair(-2, 1)
        );

        for (Pair<Integer, Integer> dir : directions) {
            int newX = x + dir.getKey();
            int newY = y + dir.getValue();
            if (isEmptyAndWithinLimits(board, newX, newY))
                moves.add(new Pair<>(newX, newY));
        }
        return moves;
    }


    private static void generateNextState(State currentState, List<Path> pqVector, PriorityQueue<Path> pq, Path currentPath) {
        for (int i = 0; i < ROWS; ++i) {
            for (int j = 0; j < COLS; ++j) {
                if (currentState.board.get(i).get(j) != 0) {
                    List<Pair<Integer, Integer>> moves = getKnightMoves(i, j, currentState.board);
                    for (Pair<Integer, Integer> move : moves) {
                        // List<List<Integer>> nextBoard = new ArrayList<>(currentState.board);
                        List<List<Integer>> nextBoard = new ArrayList<>();
                        for (List<Integer> row : currentState.board) {
                            List<Integer> newRow = new ArrayList<>(row);
                            nextBoard.add(newRow);
                        }
                        // Collections.swap(nextBoard.get(i).get(j) ,nextBoard.get(move.getKey()).get(move.getValue()));


                        if ((currentState.moves + 1 <= 16)) {
                            int nextHeuristic = calculateHeuristic(nextBoard);
                            Path nextPath = new Path(currentPath);
                            State nextState = new State(nextBoard, nextHeuristic, currentState.moves + 1);
                            System.out.println( "Current board: ");
                            printBoard(currentState.board);
                            System.out.println("Swapped board \n Next board: ");
                            printBoard(nextBoard);
                            if (!currentPath.hasDupeStates(nextState)) {
                                nextPath.push(currentState);
                                pqVector.add(nextPath);
                                pq.add(nextPath);
                            }
                        }
                    }
                }
            }
        }
    }

    private static void bestFirstSearch(List<List<Integer>> initialState) {
        PriorityQueue<Path> pq = new PriorityQueue<>();
        List<Path> pqVector = new ArrayList<>();
        // HashSet<List<List<Integer>>> visited = new HashSet<>();

        int initialHeuristic = calculateHeuristic(initialState);
        State beginningState = new State(initialState, initialHeuristic, 0);
        Path initialPath = new Path(beginningState);
        pq.add(initialPath);
        pqVector.add(initialPath);

        while (!pq.isEmpty()) {
            Path currentPath = pq.poll();
            State currentState = currentPath.path.get(currentPath.path.size() - 1);
            int index = getIndex(pqVector, currentPath);
            pqVector.remove(index);

            if (isGoalState(currentState.board)) {
                System.out.println("Solution found:");
                printBoard(currentState.board);
                System.out.println("Total moves: " + currentState.moves);
                System.out.print("Path: ");
                currentPath.printPath();
                System.out.println();
                return;
            }

            generateNextState(currentState, pqVector, pq, currentPath);
        }

        System.out.println("No solution found.");
    }

    private static int getIndex(List<Path> pqVector, Path desiredPath) {
        int index = -1;
        for (int i = 0; i < pqVector.size(); i++) {
            if (pqVector.get(i).isSamePath(desiredPath))
                index = i;
        }
        return index;
    }

    private static class State {
        List<List<Integer>> board;
        int cost = 0;
        int heuristic;
        int moves;

        State(List<List<Integer>> _board, int _heuristic, int _moves) {
            this.board = _board;
            this.heuristic = _heuristic;
            this.moves = _moves;
        }

        boolean isSameBoard(State otherState) {
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS; j++) {
                    if (!Objects.equals(this.board.get(i).get(j), otherState.board.get(i).get(j)))
                        return false;
                }
            }
            return true;
        }
    }

    private static class Path implements Comparable<Path> {
        List<State> path;

        Path(State s) {
            this.path = new ArrayList<>();
            this.path.add(s);
        }

        Path(Path p) {
            this.path = new ArrayList<>(p.path);
        }

        void push(State s) {
            this.path.add(s);
        }

        void printPath() {
            for (State state : path) {
                printBoard(state.board);
            }
        }

        boolean isSamePath(Path otherPath) {
            if (this.path.size() != otherPath.path.size())
                return false;
            for (int i = 0; i < path.size(); i++)
                if (!this.path.get(i).isSameBoard(otherPath.path.get(i)))
                    return false;
            return true;
        }

        boolean hasDupeStates(State currentState) {
            for (State state : path)
                if (currentState.isSameBoard(state))
                    return true;
            return false;
        }

        @Override
        public int compareTo(Path other) {
            return Integer.compare(this.path.get(this.path.size() - 1).heuristic, other.path.get(other.path.size() - 1).heuristic);
        }
    }

    private static class Pair<K, V> {
        private final K key;
        private final V value;

        Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        K getKey() {
            return key;
        }

        V getValue() {
            return value;
        }
    }
}

