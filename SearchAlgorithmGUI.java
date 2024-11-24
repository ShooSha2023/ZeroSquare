import javax.swing.*;
import java.awt.*;
import java.util.List;

//لرؤية كيف تعمل الخوارزميات دون التأثير على كلاس اللاعب ورؤية الحركات المتاحة

public class SearchAlgorithmGUI extends JFrame {
    private State game;
    private JButton[][] buttonGrid;
    private final int GRID_SIZE = 11;

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


                //1 --> 9
//                {'.', '.', '.', '.', '1', '1', '1', '1', '1'},
//                {'.', '.', '.', '.', '1', '.', '.', '.', '1'},
//                {'1', '1', '1', '1', '1', 'r', '1', '.', '1'},
//                {'1', '.', '.', '.', 'R', '.', '1', '.', '1'},
//                {'1', '.', '.', 'b', '1', 'B', '1', '.', '1'},
//                {'1', '.', '.', '.', '.', '.', '.', '.', '1'},
//                {'1', '.', '.', '.', '.', '.', '.', '.', '1'},
//                {'1', '1', '1', '1', '1', '1', '1', '1', '1'},
//                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},

                //2 --> 11
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '1', '1', '1', '1', '1', '.', '.', '.', '.', '.'},
                {'1', '1', 'R', '.', '.', '1', '1', '1', '1', '1', '.'},
                {'1', '1', '.', '.', '.', '1', '1', 'b', '.', '1', '.'},
                {'1', '.', '.', '.', '.', '.', '.', '.', '.', '1', '1'},
                {'1', '.', '.', '.', '1', '1', '1', '.', '.', 'r', '1'},
                {'1', '.', '.', '.', '.', '.', '.', '.', '.', '1', '1'},
                {'1', '1', 'B', '.', '1', '1', '1', '1', '1', '1', '.'},
                {'.', '1', '1', '1', '1', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
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
        JButton bfsButton = new JButton("Solve with BFS");
        JButton dfsButton = new JButton("Solve with DFS");
        JButton ucsButton = new JButton("Solve with UCS"); // زر UCS
        controlPanel.add(bfsButton);
        controlPanel.add(dfsButton);
        controlPanel.add(ucsButton);

        add(gridPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        bfsButton.addActionListener(e -> solveWithBFS());
        dfsButton.addActionListener(e -> solveWithDFS());
        ucsButton.addActionListener(e -> solveWithUCS());

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
                    case 'r' -> button.setBackground(Color.PINK);
                    case 'b' -> button.setBackground(Color.CYAN);
                    default -> button.setBackground(Color.WHITE);
                }
            }
        }
    }

    private void solveWithBFS() {
        SearchAlgorithm bfs = new BFSAlgorithm();
        java.util.List<State> path = bfs.search(game);
    }

    private void solveWithDFS() {
        SearchAlgorithm dfs = new DFSAlgorithm();
        List<State> path = dfs.search(game);
    }
    private void solveWithUCS() {
        SearchAlgorithm ucs = new UCSAlgorithm(); // استخدام خوارزمية UCS
        List<State> path = ucs.search(game);
        // [عرض المسار أو تحديث واجهة المستخدم]
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SearchAlgorithmGUI gui = new SearchAlgorithmGUI();
            gui.setVisible(true);
        });
    }
}