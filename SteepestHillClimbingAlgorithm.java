import java.util.*;

public class SteepestHillClimbingAlgorithm implements SearchAlgorithm {
    @Override
    public List<State> search(State initialState) {

        initialState.calculateHeuristic();

        State currentState = initialState;
        int visitedCount = 0;

        while (true) {
            visitedCount++;
            List<State> neighbors = currentState.getAllPossibleMovesStates();


            for (State neighbor : neighbors) {
                neighbor.calculateHeuristic();
            }


            State bestNeighbor = neighbors.stream()
                    .min(Comparator.comparingInt(State::getHeuristicValue))
                    .orElse(null);


            if (bestNeighbor == null || bestNeighbor.getHeuristicValue() >= currentState.getHeuristicValue()) {
                break;
            }


            bestNeighbor.setParent(currentState);
            currentState = bestNeighbor;


            if (currentState.isGoalState()) {
                System.out.println("Number of visited states (Steepest Ascent Hill Climbing): " + visitedCount);
                printPath(currentState);
                return constructPath(currentState);
            }
        }

        System.out.println("No solution found using Steepest Ascent Hill Climbing");
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
        System.out.println("Steepest Ascent Hill Climbing path:");

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
