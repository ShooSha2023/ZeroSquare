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

    public int getHeuristicValue() {
        return heuristicValue;
    }

    public void setHeuristicValue(int heuristicValue) {
        this.heuristicValue = heuristicValue;
    }

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
                if (currentCell == 'Y' && initialCell != 'y') {
                    return false;
                }
                if (currentCell == 'G' && initialCell != 'g') {
                    return false;
                }
                if (currentCell == 'P' && initialCell != 'p') {
                    return false;
                }
            }
        }
        return true;
    }

    public State movePlayer(int dRow, int dCol) {
        char[][] newGrid = createGridCopy();

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (newGrid[row][col] == 'B' || newGrid[row][col] == 'R' || newGrid[row][col] == 'Y' ||
                        newGrid[row][col] == 'G' || newGrid[row][col] == 'P') {
                    int newRow = row;
                    int newCol = col;

                    while (newRow + dRow >= 0 && newRow + dRow < size &&
                            newCol + dCol >= 0 && newCol + dCol < size) {
                        char nextCell = newGrid[newRow + dRow][newCol + dCol];

                        if (nextCell == '.' || nextCell == 'r' || nextCell == 'b' ||
                                nextCell == 'y' || nextCell == 'g' || nextCell == 'p') {
                            newRow += dRow;
                            newCol += dCol;

                            if ((nextCell == 'r' && newGrid[row][col] == 'R') ||
                                    (nextCell == 'b' && newGrid[row][col] == 'B') ||
                                    (nextCell == 'y' && newGrid[row][col] == 'Y') ||
                                    (nextCell == 'g' && newGrid[row][col] == 'G') ||
                                    (nextCell == 'p' && newGrid[row][col] == 'P')) {
                                break;
                            }
                        } else {
                            break;
                        }
                    }

                    if (newRow != row || newCol != col) {
                        char currentBlock = newGrid[row][col];
                        newGrid[row][col] = (initialGrid[row][col] == 'r' || initialGrid[row][col] == 'b' ||
                                initialGrid[row][col] == 'y' || initialGrid[row][col] == 'g' ||
                                initialGrid[row][col] == 'p') ? initialGrid[row][col] : '.';

                        if ((newGrid[newRow][newCol] == 'r' && currentBlock == 'R') ||
                                (newGrid[newRow][newCol] == 'b' && currentBlock == 'B') ||
                                (newGrid[newRow][newCol] == 'y' && currentBlock == 'Y') ||
                                (newGrid[newRow][newCol] == 'g' && currentBlock == 'G') ||
                                (newGrid[newRow][newCol] == 'p' && currentBlock == 'P')) {
                            newGrid[newRow][newCol] = '.';
                        } else {
                            newGrid[newRow][newCol] = currentBlock;
                        }
                    }
                }
            }
        }

        State newState = new State(newGrid, initialGrid, this);
        newState.calculateHeuristic();
        return newState;
    }
    public List<State> getAllPossibleMovesStates() {
        Set<State> possibleStates = new HashSet<>();

        List<int[]> directions = Arrays.asList(
                new int[]{0, -1}, // يسار
                new int[]{0, 1},  // يمين
                new int[]{-1, 0}, // أعلى
                new int[]{1, 0} // أسفل

        );

        //Collections.shuffle(directions);

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
    public void calculateHeuristic() {
        int heuristicValue = 0;

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                char currentBlock = grid[row][col];
                if (currentBlock == 'R') {
                    heuristicValue += manhattanDistance(row, col, initialGrid, 'r');
                } else if (currentBlock == 'B') {
                    heuristicValue += manhattanDistance(row, col, initialGrid, 'b');
                } else if (currentBlock == 'Y') {
                    heuristicValue += manhattanDistance(row, col, initialGrid, 'y');
                } else if (currentBlock == 'G') {
                    heuristicValue += manhattanDistance(row, col, initialGrid, 'g');
                } else if (currentBlock == 'P') {
                    heuristicValue += manhattanDistance(row, col, initialGrid, 'p');
                }
            }
        }

        this.setHeuristicValue(heuristicValue);
    }


    private int manhattanDistance(int row, int col, char[][] initialGrid, char target) {
        for (int i = 0; i < initialGrid.length; i++) {
            for (int j = 0; j < initialGrid[i].length; j++) {
                if (initialGrid[i][j] == target) {
                    return Math.abs(row - i) + Math.abs(col - j);
                }
            }
        }
        return Integer.MAX_VALUE;
    }

}
