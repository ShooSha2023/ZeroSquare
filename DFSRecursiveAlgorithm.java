import java.util.*;

public class DFSRecursiveAlgorithm implements SearchAlgorithm {
    private Set<State> visited;
    private int visitedCount;

    @Override
    public List<State> search(State initialState) {
        visited = new HashSet<>();
        visitedCount = 0;

        List<State> path = new ArrayList<>();
        if (dfs(initialState, path)) {
            System.out.println("Number of visited states (Recursive DFS): " + visitedCount);
            printPath(path);
            return path;
        }

        System.out.println("There's no solution for Recursive DFS");
        return null;
    }

    private boolean dfs(State currentState, List<State> path) {
        visited.add(currentState);
        visitedCount++;

        if (currentState.isGoalState()) {
            path.add(currentState);
            return true;
        }

        for (State nextState : currentState.getAllPossibleMovesStates()) {
            if (!visited.contains(nextState)) {
                nextState.setParent(currentState);
                if (dfs(nextState, path)) {
                    path.add(currentState);
                    return true;
                }
            }
        }

        return false;
    }

    private void printPath(List<State> path) {
        System.out.println("DFS path:");
        Collections.reverse(path);
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
