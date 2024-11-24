import java.util.*;

public class UCSAlgorithm implements SearchAlgorithm {
    @Override
    public List<State> search(State initialState) {
        PriorityQueue<State> queue = new PriorityQueue<>(Comparator.comparingInt(State::getTotalCost));
        Map<State, Integer> visited = new HashMap<>();
        initialState.setTotalCost(0);
        queue.offer(initialState);

        int visitedCount = 0;

        while (!queue.isEmpty()) {
            State currentState = queue.poll();
            visitedCount++;

            if (currentState.isGoalState()) {
                System.out.println("Number of visited states (UCS): " + visitedCount);
                printPath(currentState);
                return constructPath(currentState);
            }

            if (!visited.containsKey(currentState) || visited.get(currentState) > currentState.getTotalCost()) {
                visited.put(currentState, currentState.getTotalCost());

                for (State nextState : currentState.getAllPossibleMovesStates()) {
                    nextState.setTotalCost(currentState.getTotalCost() + 1);
                    nextState.setParent(currentState);
                    queue.offer(nextState);
                }
            }
        }
        System.out.println("There is no solution for UCS");
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
        System.out.println("UCS path:");

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