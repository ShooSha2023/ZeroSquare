import java.util.*;

public class BFSAlgorithm implements SearchAlgorithm {
    @Override
    public List<State> search(State initialState) {
        AlgorithmLogger logger = new AlgorithmLogger();
        Queue<State> queue = new LinkedList<>();
        Set<State> visited = new HashSet<>();
        queue.add(initialState);
        visited.add(initialState);

        int visitedCount = 0;
        long startTime = System.nanoTime();

        while (!queue.isEmpty()) {
            State currentState = queue.poll();
            visitedCount++;
            logger.incrementVisitedNodes();

            if (currentState.isGoalState()) {
                long endTime = System.nanoTime();
                logger.setExecutionTime(startTime, endTime);
                logger.setVisitedNodes(visitedCount);
                logger.setSolutionPathNodes(constructPath(currentState).size()-1);
                logger.calculateMemoryUsage();
                logger.saveLogToFile("bfs_log.log");

                System.out.println("Number of visited states (BFS): " + visitedCount);
                printPath(currentState);
                return constructPath(currentState);
            }


            for (State nextState : currentState.getAllPossibleMovesStates()) {
                if (!visited.contains(nextState)) {
                    visited.add(nextState);
                    nextState.setParent(currentState);
                    queue.add(nextState);
                }
            }
        }


        long endTime = System.nanoTime();
        logger.setExecutionTime(startTime, endTime);
        logger.calculateMemoryUsage();
        logger.saveLogToFile("bfs_log_no_solution.log");

        System.out.println("There is no solution for BFS");
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
        System.out.println("BFS path:");

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
