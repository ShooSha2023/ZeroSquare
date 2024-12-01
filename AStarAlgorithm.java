import java.util.*;

public class AStarAlgorithm implements SearchAlgorithm {
    @Override
    public List<State> search(State startState) {
        PriorityQueue<State> openSet = new PriorityQueue<>(Comparator.comparingInt(State::getTotalCost));
        Set<State> closedSet = new HashSet<>();
        openSet.add(startState);

        int visitedCount = 0;

        while (!openSet.isEmpty()) {
            State current = openSet.poll();
            visitedCount++;


            if (current.isGoalState()) {
                System.out.println("Number of visited states (A*): " + visitedCount);
                printPath(current);
                return constructPath(current);
            }

            closedSet.add(current);

            for (State neighbor : current.getAllPossibleMovesStates()) {
                if (closedSet.contains(neighbor)) {
                    continue;
                }

                int tentativeCost = current.getTotalCost() + 1;
                boolean isBetterPath = false;

                if (!openSet.contains(neighbor)) {
                    openSet.add(neighbor);
                    isBetterPath = true;
                } else if (tentativeCost < neighbor.getTotalCost()) {
                    isBetterPath = true;
                }

                if (isBetterPath) {
                    neighbor.setParent(current);
                    neighbor.setTotalCost(tentativeCost + heuristic(neighbor));
                }
            }
        }

        System.out.println("There's no solution for A*");
        return null;
    }

    private int heuristic(State state) {
        int heuristicValue = 0;

        char[][] grid = state.getGrid();
        char[][] initialGrid = state.initialGrid;

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == 'R') {
                    heuristicValue += manhattanDistance(row, col, initialGrid, 'r');
                } else if (grid[row][col] == 'B') {
                    heuristicValue += manhattanDistance(row, col, initialGrid, 'b');
                }
            }
        }

        return heuristicValue;
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

    private List<State> constructPath(State goalState) {
        List<State> path = new ArrayList<>();
        for (State state = goalState; state != null; state = state.getParent()) {
            path.add(state);
        }
        Collections.reverse(path);
        return path;
    }

    private void printPath(State goalState) {
        List<State> path = constructPath(goalState);
        System.out.println("A* path:");

        int moveCount = 0;
        for (State state : path) {
            System.out.println("Move " + moveCount + " (Cost: " + state.getTotalCost() + "):");
            System.out.println(state); // Print state
            pause(500);
            moveCount++;
        }

        System.out.println("Number of moves to reach the goal: " + (moveCount - 1));
    }

    private void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
