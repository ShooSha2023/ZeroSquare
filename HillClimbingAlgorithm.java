import java.util.*;

public class HillClimbingAlgorithm implements SearchAlgorithm {
    @Override
    public List<State> search(State initialState) {
        return searchWithRandomRestart(initialState, 10);
    }

    public List<State> searchWithRandomRestart(State initialState, int maxRestarts) {
        if (maxRestarts <= 0) {
            throw new IllegalArgumentException("maxRestarts must be greater than zero.");
        }

        AlgorithmLogger logger = new AlgorithmLogger();
        State bestState = null;
        int visitedCount = 0;
        long startTime = System.nanoTime();

        for (int restart = 0; restart < maxRestarts; restart++) {
            System.out.println("========== Restart #" + (restart + 1) + " ==========");
            State currentState = initialState;
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


                State nextState = neighbors.stream()
                        .min(Comparator.comparingInt(State::getHeuristicValue))
                        .orElse(null);

                if (nextState == null) {
                    System.out.println("No better neighbor found! Stuck at local maximum.");
                    break;
                }

                System.out.println("Best neighbor heuristic: " + nextState.getHeuristicValue());


                if (nextState.getHeuristicValue() >= currentState.getHeuristicValue()) {
                    System.out.println("No improvement! Stuck at local maximum.");
                    break;
                }

                nextState.setParent(currentState);
                currentState = nextState;
            }


            if (currentState.isGoalState()) {
                long endTime = System.nanoTime();
                logger.setExecutionTime(startTime, endTime);
                logger.setVisitedNodes(visitedCount);
                logger.setSolutionPathNodes(constructPath(currentState).size() - 1);
                logger.calculateMemoryUsage();
                logger.saveLogToFile("hill_climbing_with_restart_log.log");

                System.out.println("\n=== Goal Reached! ===");
                printPath(currentState);
                return constructPath(currentState);
            }


            if (bestState == null || currentState.getHeuristicValue() < bestState.getHeuristicValue()) {
                bestState = currentState;
                System.out.println("Updated best state heuristic: " + bestState.getHeuristicValue());
            }
        }


        long endTime = System.nanoTime();
        logger.setExecutionTime(startTime, endTime);
        logger.setVisitedNodes(visitedCount);
        logger.calculateMemoryUsage();
        logger.saveLogToFile("hill_climbing_with_restart_log_no_solution.log");

        System.out.println("\nNo solution found using Hill Climbing with Random Restart.");
        if (bestState != null) {
            System.out.println("Returning the best state found with heuristic value: " + bestState.getHeuristicValue());
            return constructPath(bestState);
        }

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
        System.out.println("\nHill Climbing Path:");

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
