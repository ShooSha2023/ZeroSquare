import java.util.*;

public class AStarAlgorithm implements SearchAlgorithm {
    @Override
    public List<State> search(State startState) {
        AlgorithmLogger logger = new AlgorithmLogger();
        PriorityQueue<State> openSet = new PriorityQueue<>(Comparator.comparingInt(State::getTotalCost));
        Set<State> closedSet = new HashSet<>();
        openSet.add(startState);

        long startTime = System.nanoTime();

        while (!openSet.isEmpty()) {
            State current = openSet.poll();
            logger.incrementVisitedNodes();

            if (current.isGoalState()) {
                long endTime = System.nanoTime();
                logger.setExecutionTime(startTime, endTime);
                logger.setSolutionPathNodes(constructPath(current).size());
                logger.calculateMemoryUsage();
                logger.saveLogToFile("astar_log.log");

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

        long endTime = System.nanoTime();
        logger.setExecutionTime(startTime, endTime);
        logger.calculateMemoryUsage();
        logger.saveLogToFile("astar_log_no_solution.log");

        System.out.println("There's no solution for A*");
        return null;
    }

    private int heuristic(State state) {
        int heuristicValue = 0;
        char[][] grid = state.getGrid();
        char[][] initialGrid = state.initialGrid;

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                char currentBlock = grid[row][col];
                switch (currentBlock) {
                    case 'R':
                        heuristicValue += manhattanDistance(row, col, initialGrid, 'r');
                        break;
                    case 'B':
                        heuristicValue += manhattanDistance(row, col, initialGrid, 'b');
                        break;
                    case 'Y':
                        heuristicValue += manhattanDistance(row, col, initialGrid, 'y');
                        break;
                    case 'G':
                        heuristicValue += manhattanDistance(row, col, initialGrid, 'g');
                        break;
                    case 'P':
                        heuristicValue += manhattanDistance(row, col, initialGrid, 'p');
                        break;
                    default:
                        break;
                }
            }
        }
        return heuristicValue;
    }
//private int heuristic(State state) {
//    int heuristicValue = 0;
//    char[][] grid = state.getGrid();
//    char[][] initialGrid = state.initialGrid;
//
//
//    Map<Character, Character> dependencies = new HashMap<>();
//    dependencies.put('B', 'R');
//    dependencies.put('Y', 'G');
//
//    System.out.println("===== حساب المانهاتن مع الاعتمادية =====");
//
//    for (int row = 0; row < grid.length; row++) {
//        for (int col = 0; col < grid[row].length; col++) {
//            char currentBlock = grid[row][col];
//            int distance = 0;
//
//            switch (currentBlock) {
//                case 'R':
//                case 'G':
//                case 'P':
//                    distance = manhattanDistance(row, col, initialGrid, Character.toLowerCase(currentBlock));
//                    heuristicValue += distance;
//                    System.out.printf("%c في الموقع (%d, %d) -> هدفها (%c): مسافة = %d\n",
//                            currentBlock, row, col, Character.toLowerCase(currentBlock), distance);
//                    break;
//
//                case 'B':
//                case 'Y':
//                    char dependency = dependencies.get(currentBlock);
//                    if (dependency != 0 && isDependencyMet(grid, initialGrid, dependency)) {
//
//                        distance = manhattanDistance(row, col, initialGrid, Character.toLowerCase(currentBlock));
//                        heuristicValue += distance;
//                        System.out.printf("%c في الموقع (%d, %d) -> هدفها (%c): مسافة = %d (التبعية محققة)\n",
//                                currentBlock, row, col, Character.toLowerCase(currentBlock), distance);
//                    } else {
//
//                        distance = 50;
//                        heuristicValue += distance;
//                        System.out.printf("%c في الموقع (%d, %d) -> هدفها (%c): مسافة = %d (التبعية غير محققة)\n",
//                                currentBlock, row, col, Character.toLowerCase(currentBlock), distance);
//                    }
//                    break;
//
//                default:
//                    break;
//            }
//        }
//    }
//
//    System.out.println("=======================================");
//    System.out.printf("إجمالي قيمة المانهاتن: %d\n", heuristicValue);
//    return heuristicValue;
//}
//
//    private boolean isDependencyMet(char[][] grid, char[][] initialGrid, char dependency) {
//
//        for (int i = 0; i < grid.length; i++) {
//            for (int j = 0; j < grid[i].length; j++) {
//                if (grid[i][j] == dependency && initialGrid[i][j] == Character.toLowerCase(dependency)) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
    private int manhattanDistance(int row, int col, char[][] initialGrid, char target) {
        for (int i = 0; i < initialGrid.length; i++) {
            for (int j = 0; j < initialGrid[i].length; j++) {
                if (initialGrid[i][j] == target) {
                    return Math.abs(row - i) + Math.abs(col - j);
                }
            }
        }
        return 0;
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
            System.out.println(state);
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
