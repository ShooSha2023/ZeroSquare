import java.util.*;

public class BFSAlgorithm implements SearchAlgorithm {
    @Override
    public List<State> search(State initialState) {
        Queue<State> queue = new LinkedList<>();
        Set<State> visited = new HashSet<>();
        queue.add(initialState);
        visited.add(initialState);

        int visitedCount = 0;

        while (!queue.isEmpty()) {
            State currentState = queue.poll();
            visitedCount++;

            if (currentState.isGoalState()) {
                System.out.println("Number of visited states (BFS): " + visitedCount);
                printPath(currentState); // طباعة المسار
                return constructPath(currentState);
            }

            for (State nextState : currentState.getAllPossibleMovesStates()) {
                if (!visited.contains(nextState)) {
                    visited.add(nextState);
                    nextState.setParent(currentState); // تعيين الوالد
                    queue.add(nextState);
                }
            }
        }
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