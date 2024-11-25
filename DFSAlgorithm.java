import java.util.*;

public class DFSAlgorithm implements SearchAlgorithm {
    @Override
    public List<State> search(State initialState) {
        Stack<State> stack = new Stack<>();
        Set<State> visited = new HashSet<>();
        stack.push(initialState);
        visited.add(initialState);

        int visitedCount = 0;

        while (!stack.isEmpty()) {
            State currentState = stack.pop();
            visitedCount++;


            if (currentState.isGoalState()) {
                System.out.println("Number of visited states (DFS): " + visitedCount);
                printPath(currentState);
                return constructPath(currentState);
            }


            for (State nextState : currentState.getAllPossibleMovesStates()) {
                if (!visited.contains(nextState)) {
                    visited.add(nextState);
                    nextState.setParent(currentState);
                    stack.push(nextState);
                }
            }
        }
        System.out.println("There's no solution for DFS");
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
        System.out.println("DFS path:");

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