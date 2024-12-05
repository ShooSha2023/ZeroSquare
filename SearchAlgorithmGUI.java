import javax.swing.*;
import java.awt.*;
import java.util.List;

//لرؤية كيف تعمل الخوارزميات دون التأثير على كلاس اللاعب ورؤية الحركات المتاحة

public class SearchAlgorithmGUI extends JFrame {
    private State game;
    private JButton[][] buttonGrid;
    private final int GRID_SIZE =12;

    public SearchAlgorithmGUI() {
        char[][] initialGrid = {
//                لون واحد
                // 1 -->8
//                {'.', '.', '.', '.','.','.','.','.'},
//                {'1', '1', '1', '1','1','1','1','1'},
//                {'1', '.', '.', '1','.','.','.','1'},
//                {'1', 'R', '.', '.','.','.','.','1'},
//                {'1', '.', '.', '.','.','.','.','1'},
//                {'1', '1', '1', '1','r','1','1','1'},
//                {'.', '.', '.', '1','1','1','.','.'},
//                {'.', '.', '.', '.','.','.','.','.'},

                //2 -->13
//                  {'.', '.', '.', '.','.','.','.','.','.','.','.','.','.'},
//                  {'.', '.', '.', '.','.','.','.','.','.','.','.','.','.'},
//                  {'.', '.', '.', '.','.','.','.','.','.','.','.','.','.'},
//                  {'.', '.', '1', '1','1','1','1','1','1','1','1','1','1'},
//                  {'.', '.', '1', 'R','.','.','.','.','.','.','.','.','1'},
//                  {'1', '1', '1', '.','.','1','1','1','1','1','1','.','1'},
//                  {'1', '.', '.', '.','.','.','r','.','.','.','1','.','1'},
//                  {'1', '.', '1', '.','.','.','.','.','.','.','1','.','1'},
//                  {'1', '.', '.', '.','.','.','.','.','.','.','.','.','1'},
//                  {'1', '1', '1', '1','.','.','.','.','.','.','.','.','1'},
//                  {'.', '.', '.', '1','1','1','1','1','1','1','1','1','1'},
//                  {'.', '.', '.', '.','.','.','.','.','.','.','.','.','.'},
//                  {'.', '.', '.', '.','.','.','.','.','.','.','.','.','.'},

                //لونين


                //المرحلة 20
//                {'1', '1', '1', '1', '1', '1', '1', '1', '1'},
//                {'1', '1', '1', '1', '1', '1', '1', '1', '1'},
//                {'1', '1', '1', '1', '1', '1', '1', '1', '1'},
//                {'1', '1', '1', '1', 'R', 'y', '1', '1', '1'},
//                {'1', 'b', 'P', 'B', '.', '.', '.', 'Y', '1'},
//                {'1', 'G', 'p', '1', 'r', '.', '1', 'g', '1'},
//                {'1', '1', '1', '1', '1', '1', '1', '1', '1'},
//                {'1', '1', '1', '1', '1', '1', '1', '1', '1'},
//                {'1', '1', '1', '1', '1', '1', '1', '1', '1'},
                //2 --> 11
//                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
//                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
//                {'.', '1', '1', '1', '1', '1', '.', '.', '.', '.', '.'},
//                {'1', '1', 'R', '.', '.', '1', '1', '1', '1', '1', '.'},
//                {'1', '1', '.', '.', '.', '1', '1', 'b', '.', '1', '.'},
//                {'1', '.', '.', '.', '.', '.', '.', '.', '.', '1', '1'},
//                {'1', '.', '.', '.', '1', '1', '1', '.', '.', 'r', '1'},
//                {'1', '.', '.', '.', '.', '.', '.', '.', '.', '1', '1'},
//                {'1', '1', 'B', '.', '1', '1', '1', '1', '1', '1', '.'},
//                {'.', '1', '1', '1', '1', '.', '.', '.', '.', '.', '.'},
//                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},



//               //المرحلة 10
                  {'1', '1', '1', '1','1','1','1','1','1','1','1','1'},
                  {'1', '1', '1', '1','1','1','1','1','1','1','1','1'},
                  {'1', '1', '1', '1','1','1','1','1','1','1','1','1'},
                  {'1', '1', '1', '.','.','.','.','.','.','1','1','1'},
                  {'1', '1', '.', '.','1','.','b','1','.','.','1','1'},
                  {'1', '.', '.', '1','.','r','.','.','1','.','.','1'},
                  {'1', '.', '.', '.','.','1','.','.','1','1','.','1'},
                  {'1', 'R', '1', '.','1','1','.','.','.','1','B','1'},
                  {'1', '.', '.', '.','1','.','.','.','.','.','.','1'},
                  {'1', '1', '1', '1','1','1','1','1','1','1','1','1'},
                  {'1', '1', '1', '1','1','1','1','1','1','1','1','1'},
                  {'1', '1', '1', '1','1','1','1','1','1','1','1','1'},


                //3-->8
//                {'1', '1', '1', '1','1','1','1','1'},
//                {'1', '1', '1', '1','1','1','1','1'},
//                {'1', '1', '1', '1','1','1','1','1'},
//                {'1', '1', 'y', '1','1','1','1','1'},
//                {'1', 'Y', 'R', 'B','.','.','.','1'},
//                {'1', 'b', '1', 'r','1','1','1','1'},
//                {'1', '1', '1', '1','1','1','1','1'},
//                {'1', '1', '1', '1','1','1','1','1'},
                     //المرحلة 30
//                {'1', '1', '1', '1','1','1','1','1','1','1','1','1','1','1','1','1'},
//                {'1', '1', '1', '1','1','1','1','1','1','1','1','1','1','1','1','1'},
//                {'1', '1', '1', '1','1','1','1','1','1','1','1','1','1','1','1','1'},
//                {'1', '1', '1', '1','1','1','1','1','1','1','1','1','1','1','1','1'},
//                {'1', '1', '1', '1','1','1','1','1','1','1','1','1','1','1','1','1'},
//                {'1', '1', '1', '1','1','1','1','1','1','1','1','1','1','1','1','1'},
//                {'1', '1', '1', '1','1','.','.','p','1','1','1','y','1','1','g','1'},
//                {'1', 'R', 'B', 'G','.','.','1','.','.','.','.','.','.','.','.','1'},
//                {'1', 'Y', 'P', '.','.','.','.','.','.','.','.','.','1','.','.','1'},
//                {'1', '1', '1', '1','1','r','1','1','b','1','1','1','1','1','1','1'},
//                {'1', '1', '1', '1','1','1','1','1','1','1','1','1','1','1','1','1'},
//                {'1', '1', '1', '1','1','1','1','1','1','1','1','1','1','1','1','1'},
//                {'1', '1', '1', '1','1','1','1','1','1','1','1','1','1','1','1','1'},
//                {'1', '1', '1', '1','1','1','1','1','1','1','1','1','1','1','1','1'},
//                {'1', '1', '1', '1','1','1','1','1','1','1','1','1','1','1','1','1'},
//                {'1', '1', '1', '1','1','1','1','1','1','1','1','1','1','1','1','1'},
        };

                game = new State(initialGrid);
                setTitle("Zero Squares Game");
                setSize(800, 800);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setLocationRelativeTo(null);

                setLayout(new BorderLayout());


                JPanel gridPanel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
                buttonGrid = new JButton[GRID_SIZE][GRID_SIZE];
                for (int row = 0; row < GRID_SIZE; row++) {
                    for (int col = 0; col < GRID_SIZE; col++) {
                        buttonGrid[row][col] = new JButton();
                        buttonGrid[row][col].setFocusable(false);
                        gridPanel.add(buttonGrid[row][col]);
                    }
                }
                updateGrid();


                JPanel controlPanel = new JPanel();
                controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
                JButton bfsButton = new JButton("BFS");
                JButton dfsButton = new JButton("DFS");
                JButton ucsButton = new JButton("UCS");
                JButton recursiveDFSButton = new JButton("Recursive DFS");
                JButton astarButton = new JButton("A*");
                JButton hillClimbingButton = new JButton("Hill Climbing");
                JButton steepestHillClimbingButton = new JButton("Steepest Hill Climbing");

                controlPanel.add(bfsButton);
                controlPanel.add(dfsButton);
                controlPanel.add(ucsButton);
                controlPanel.add(recursiveDFSButton);
                controlPanel.add(astarButton);
                controlPanel.add(hillClimbingButton);
                controlPanel.add(steepestHillClimbingButton);

                add(gridPanel, BorderLayout.CENTER);
                add(controlPanel, BorderLayout.SOUTH);

                bfsButton.addActionListener(e -> solveWithBFS());
                dfsButton.addActionListener(e -> solveWithDFS());
                ucsButton.addActionListener(e -> solveWithUCS());
                recursiveDFSButton.addActionListener(e -> solveWithRecursiveDFS());
                astarButton.addActionListener(e -> solveWithAStar());
                hillClimbingButton.addActionListener(e -> solveWithHillClimbing());
                steepestHillClimbingButton.addActionListener(e -> solveWithSteepestHillClimbing());

                setFocusable(true);
            }

            private void updateGrid() {
                for (int row = 0; row < GRID_SIZE; row++) {
                    for (int col = 0; col < GRID_SIZE; col++) {
                        char cell = game.grid[row][col];
                        JButton button = buttonGrid[row][col];
                        switch (cell) {
                            case '1' -> button.setBackground(Color.GRAY);
                            case 'R' -> button.setBackground(Color.RED);
                            case 'B' -> button.setBackground(Color.BLUE);
                            case 'Y' -> button.setBackground(Color.YELLOW);
                            case 'G' -> button.setBackground(Color.GREEN);
                            case 'P' -> button.setBackground(Color.PINK);
                            case 'g' -> button.setBackground(new Color(200, 255, 200));
                            case 'p' -> button.setBackground(new Color(255, 200, 230));
                            case 'r' -> button.setBackground(new Color(255, 150, 150));
                            case 'b' -> button.setBackground(new Color(150, 200, 255));
                            case 'y' -> button.setBackground(new Color(255, 255, 150));
                            default -> button.setBackground(Color.WHITE);
                        }
                    }
                }
            }

            private void solveWithBFS() {
                SearchAlgorithm bfs = new BFSAlgorithm();
                List<State> path = bfs.search(game);
            }

            private void solveWithDFS() {
                SearchAlgorithm dfs = new DFSAlgorithm();
                List<State> path = dfs.search(game);
            }

            private void solveWithRecursiveDFS() {
                SearchAlgorithm recursiveDFS = new DFSRecursiveAlgorithm();
                List<State> path = recursiveDFS.search(game);
            }

            private void solveWithUCS() {
                SearchAlgorithm ucs = new UCSAlgorithm();
                List<State> path = ucs.search(game);
            }

            private void solveWithAStar() {
                SearchAlgorithm aStar = new AStarAlgorithm();
                List<State> path = aStar.search(game);
            }

            private void solveWithHillClimbing() {
                SearchAlgorithm hillClimbing = new HillClimbingAlgorithm();
                List<State> path = hillClimbing.search(game);
            }

            private void solveWithSteepestHillClimbing() {
                SearchAlgorithm hillClimbing = new SteepestHillClimbingAlgorithm();
                List<State> path = hillClimbing.search(game);
            }

            public static void main(String[] args) {
                SwingUtilities.invokeLater(() -> {
                    SearchAlgorithmGUI gui = new SearchAlgorithmGUI();
                    gui.setVisible(true);
                });
            }
        }
