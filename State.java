import java.util.*;

public class State {
    final char[][] grid;
    final char[][] initialGrid;
    final int size;
    private int totalCost;
    State parent;
    private int heuristicValue;


    public State(char[][] initialGrid) {
        this.size = initialGrid.length;
        this.grid = new char[size][size];
        this.initialGrid = new char[size][size];
        this.parent = null;
        this.totalCost = 0;
        this.heuristicValue = 0;
        for (int i = 0; i < size; i++) {
            this.grid[i] = Arrays.copyOf(initialGrid[i], size);
            this.initialGrid[i] = Arrays.copyOf(initialGrid[i], size);
        }
    }


    private State(char[][] grid, char[][] initialGrid, State parent) {
        this.size = grid.length;
        this.grid = new char[size][size];
        this.initialGrid = new char[size][size];
        this.parent = null;
        this.totalCost = 0;
        for (int i = 0; i < size; i++) {
            this.grid[i] = Arrays.copyOf(grid[i], size);
            this.initialGrid[i] = Arrays.copyOf(initialGrid[i], size);
        }
    }


    public State getParent() {
        return parent;
    }
    public void setParent(State parent) {
        this.parent = parent;
    }
    // دالة للحصول على القيمة التقديرية
    public int getHeuristicValue() {
        return heuristicValue;
    }

    // دالة لتعيين القيمة التقديرية
    public void setHeuristicValue(int heuristicValue) {
        this.heuristicValue = heuristicValue;
    }

    // دالة للحصول على التكلفة الإجمالية (التي تشمل التكلفة الفعلية + التقدير



    private char[][] createGridCopy() {
        char[][] newGrid = new char[size][size];
        for (int i = 0; i < size; i++) {
            newGrid[i] = Arrays.copyOf(grid[i], size);
        }
        return newGrid;
    }


    public boolean isGoalState() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                char currentCell = grid[row][col];
                char initialCell = initialGrid[row][col];

                if (currentCell == 'B' && initialCell != 'b') {
                    return false;
                }
                if (currentCell == 'R' && initialCell != 'r') {
                    return false;
                }
            }
        }
        return true;
    }

//    public State checkGoalState() {
//        boolean allGoalsReached = true;
//        char[][] newGrid = createGridCopy();
//
//        for (int row = 0; row < size; row++) {
//            for (int col = 0; col < size; col++) {
//                if ((grid[row][col] == 'B' && initialGrid[row][col] != 'b') ||
//                        (grid[row][col] == 'R' && initialGrid[row][col] != 'r')) {
//                    allGoalsReached = false;
//                }
//            }
//        }
//
//        if (allGoalsReached) {
//            for (int row = 0; row < size; row++) {
//                for (int col = 0; col < size; col++) {
//                    if ((grid[row][col] == 'B' && initialGrid[row][col] == 'b') ||
//                            (grid[row][col] == 'R' && initialGrid[row][col] == 'r')) {
//                        newGrid[row][col] = '.';
//                    }
//                }
//            }
//        }
//
//        return new State(newGrid, initialGrid,this);
//    }


    public State movePlayer(int dRow, int dCol) {
        char[][] newGrid = createGridCopy();

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (newGrid[row][col] == 'B' || newGrid[row][col] == 'R') {
                    int newRow = row;
                    int newCol = col;

                    while (newRow + dRow >= 0 && newRow + dRow < size &&
                            newCol + dCol >= 0 && newCol + dCol < size) {
                        char nextCell = newGrid[newRow + dRow][newCol + dCol];
                        if (nextCell == '.' || (nextCell == 'r' && newGrid[row][col] == 'B') ||
                                (nextCell == 'b' && newGrid[row][col] == 'R')) {
                            newRow += dRow;
                            newCol += dCol;
                        } else if (nextCell == 'r' && newGrid[row][col] == 'R') {
                            newGrid[newRow + dRow][newCol + dCol] = '.';
                            newGrid[row][col] = '.';
                            return new State(newGrid, initialGrid, this);
                        } else if (nextCell == 'b' && newGrid[row][col] == 'B') {
                            newGrid[newRow + dRow][newCol + dCol] = '.';
                            newGrid[row][col] = '.';
                            return new State(newGrid, initialGrid, this);
                        } else {
                            break;
                        }
                    }

                    if (newRow != row || newCol != col) {
                        newGrid[newRow][newCol] = newGrid[row][col];
                        if ((initialGrid[row][col] == 'r' && newGrid[row][col] == 'B') ||
                                (initialGrid[row][col] == 'b' && newGrid[row][col] == 'R')) {
                            newGrid[row][col] = initialGrid[row][col];
                        } else {
                            newGrid[row][col] = '.';
                        }
                    }
                }
            }
        }
        return new State(newGrid, initialGrid, this);
    }


    public List<State> getAllPossibleMovesStates() {
        Set<State> possibleStates = new HashSet<>();

        List<int[]> directions = Arrays.asList(
                new int[]{-1, 0}, // أعلى
                new int[]{1, 0},  // أسفل
                new int[]{0, -1}, // يسار
                new int[]{0, 1}   // يمين
        );

        Collections.shuffle(directions);

        for (int[] dir : directions) {
            int dRow = dir[0];
            int dCol = dir[1];

            State newState = this.movePlayer(dRow, dCol);

            possibleStates.add(newState);
        }

        return new ArrayList<>(possibleStates);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Arrays.deepEquals(grid, state.grid);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(grid);
    }


    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (char[] row : grid) {
            for (char cell : row) {
                s.append(cell).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    public char[][] getGrid() {
        return createGridCopy();
    }


    public void setTotalCost(int cost) {
        this.totalCost = cost;
    }
    public int getTotalCost() {
        return totalCost + heuristicValue;
    }

}