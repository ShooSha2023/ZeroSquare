import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AlgorithmLogger {
    private int visitedNodes;
    private int solutionPathNodes;
    private long executionTime;
    private long memoryUsage;

    public AlgorithmLogger() {
        this.visitedNodes = 0;
        this.solutionPathNodes = 0;
        this.executionTime = 0;
        this.memoryUsage = 0;
    }

    public void incrementVisitedNodes() {
        visitedNodes++;
    }
    public void setVisitedNodes(int count) {
        this.visitedNodes = count;}

    public void setSolutionPathNodes(int count) {
        this.solutionPathNodes = count;
    }

    public void setExecutionTime(long startTime, long endTime) {
        this.executionTime = endTime - startTime;
    }

    public void calculateMemoryUsage() {
        Runtime runtime = Runtime.getRuntime();
        this.memoryUsage = runtime.totalMemory() - runtime.freeMemory();
    }

    public void saveLogToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("Visited Nodes: " + visitedNodes + "\n");
            writer.write("Solution Path Nodes: " + solutionPathNodes + "\n");
            writer.write("Execution Time (ms): " + (executionTime / 1_000_000) + "\n");
            writer.write("Memory Usage (KB): " + (memoryUsage / 1024) + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
