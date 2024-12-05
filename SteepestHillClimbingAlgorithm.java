import java.util.*;

public class SteepestHillClimbingAlgorithm implements SearchAlgorithm {
    @Override
    public List<State> search(State initialState) {
        return searchWithSteepestAscent(initialState);
    }

    public List<State> searchWithSteepestAscent(State initialState) {
        AlgorithmLogger logger = new AlgorithmLogger();
        State currentState = initialState;
        int visitedCount = 0;
        long startTime = System.nanoTime();

        currentState.calculateHeuristic();
        System.out.println("Initial state heuristic: " + currentState.getHeuristicValue());

        while (!currentState.isGoalState()) {
            System.out.println("\nCurrent State:");
            System.out.println(currentState);
            System.out.println("Current Heuristic: " + currentState.getHeuristicValue());

            visitedCount++;
            logger.incrementVisitedNodes();


            List<State> neighbors = currentState.getAllPossibleMovesStates();
            if (neighbors.isEmpty()) {
                System.out.println("No neighbors found! Stuck at local maximum.");
                break;
            }

            System.out.println("Evaluating neighbors...");
            for (State neighbor : neighbors) {
                neighbor.calculateHeuristic();
                System.out.println("Neighbor heuristic: " + neighbor.getHeuristicValue());
            }


            State bestNeighbor = neighbors.stream()
                    .min(Comparator.comparingInt(State::getHeuristicValue))
                    .orElse(null);

            if (bestNeighbor == null) {
                System.out.println("No better neighbor found! Stuck at local maximum.");
                break;
            }

            System.out.println("Best neighbor heuristic: " + bestNeighbor.getHeuristicValue());


            if (bestNeighbor.getHeuristicValue() >= currentState.getHeuristicValue()) {
                System.out.println("No improvement! Stuck at local maximum.");
                break;
            }
            bestNeighbor.setParent(currentState);
            currentState = bestNeighbor;
        }


        if (currentState.isGoalState()) {
            long endTime = System.nanoTime();
            logger.setExecutionTime(startTime, endTime);
            logger.setVisitedNodes(visitedCount);
            logger.setSolutionPathNodes(constructPath(currentState).size() - 1);
            logger.calculateMemoryUsage();
            logger.saveLogToFile("steepest_hill_climbing_log.log");

            System.out.println("\n=== Goal Reached! ===");
            printPath(currentState);
            return constructPath(currentState);
        }

        long endTime = System.nanoTime();
        logger.setExecutionTime(startTime, endTime);
        logger.setVisitedNodes(visitedCount);
        logger.calculateMemoryUsage();
        logger.saveLogToFile("steepest_hill_climbing_no_solution.log");

        System.out.println("\nNo solution found using Steepest Hill Climbing.");
        return null;
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
        System.out.println("\nSteepest Hill Climbing Path:");

        int moveCount = 0;
        for (State state : path) {
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
