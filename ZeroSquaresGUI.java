import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ZeroSquaresGUI extends JFrame {
    private State game;
    private JButton[][] buttonGrid;
    private final int GRID_SIZE = 16;

    public ZeroSquaresGUI() {

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
                //              {'.', '.', '.', '.', '1', '1', '1', '1', '1'},
//                {'.', '.', '.', '.', '1', '.', '.', '.', '1'},
//                {'1', '1', '1', '1', '1', 'r', '1', '.', '1'},
//                {'1', '.', '.', '.', 'R', '.', '1', '.', '1'},
//                {'1', '.', '.', 'b', '1', 'B', '1', '.', '1'},
//                {'1', '.', '.', '.', '.', '.', '.', '.', '1'},
//                {'1', '.', '.', '.', '.', '.', '.', '.', '1'},
//                {'1', '1', '1', '1', '1', '1', '1', '1', '1'},
//                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},

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

//                {'1', '1', '1', '1','1','1','1','1'},
//                {'1', '1', '1', '1','1','1','1','1'},
//                {'1', '1', '1', '1','1','1','1','1'},
//                {'1', '1', 'y', '1','1','1','1','1'},
//                {'1', 'Y', 'R', 'B','.','.','.','1'},
//                {'1', 'b', '1', 'r','1','1','1','1'},
//                {'1', '1', '1', '1','1','1','1','1'},
//                {'1', '1', '1', '1','1','1','1','1'},








                {'1', '1', '1', '1','1','1','1','1','1','1','1','1','1','1','1','1'},
                {'1', '1', '1', '1','1','1','1','1','1','1','1','1','1','1','1','1'},
                {'1', '1', '1', '1','1','1','1','1','1','1','1','1','1','1','1','1'},
                {'1', '1', '1', '1','1','1','1','1','1','1','1','1','1','1','1','1'},
                {'1', '1', '1', '1','1','1','1','1','1','1','1','1','1','1','1','1'},
                {'1', '1', '1', '1','1','1','1','1','1','1','1','1','1','1','1','1'},
                {'1', '1', '1', '1','1','.','.','p','1','1','1','y','1','1','g','1'},
                {'1', 'R', 'B', 'G','.','.','1','.','.','.','.','.','.','.','.','1'},
                {'1', 'Y', 'P', '.','.','.','.','.','.','.','.','.','1','.','.','1'},
                {'1', '1', '1', '1','1','r','1','1','b','1','1','1','1','1','1','1'},
                {'1', '1', '1', '1','1','1','1','1','1','1','1','1','1','1','1','1'},
                {'1', '1', '1', '1','1','1','1','1','1','1','1','1','1','1','1','1'},
                {'1', '1', '1', '1','1','1','1','1','1','1','1','1','1','1','1','1'},
                {'1', '1', '1', '1','1','1','1','1','1','1','1','1','1','1','1','1'},
                {'1', '1', '1', '1','1','1','1','1','1','1','1','1','1','1','1','1'},
                {'1', '1', '1', '1','1','1','1','1','1','1','1','1','1','1','1','1'},


        };

        game = new State(initialGrid);

        setTitle("Zero Squares Game");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));
        buttonGrid = new JButton[GRID_SIZE][GRID_SIZE];

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                buttonGrid[row][col] = new JButton();
                buttonGrid[row][col].setFocusable(false);
                add(buttonGrid[row][col]);
            }
        }

        updateGrid();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W:
                        game = game.movePlayer(-1, 0);
                        break;
                    case KeyEvent.VK_S:
                        game = game.movePlayer(1, 0);
                        break;
                    case KeyEvent.VK_A:
                        game = game.movePlayer(0, -1);
                        break;
                    case KeyEvent.VK_D:
                        game = game.movePlayer(0, 1);
                        break;
                    default:
                        return;
                }

                updateGrid();
                printPossibleGrid();

                if (game.isGoalState()) {
                    JOptionPane.showMessageDialog(null, "Congratulations, you win!");
                }
            }
        });

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

    private void printPossibleGrid() {
        List<State> possibleStates = game.getAllPossibleMovesStates();
        int stateNum = 0;
        for (State state : possibleStates) {
            System.out.println("The grid with possible move " + (stateNum + 1));
            for (char[] row : state.getGrid()) {
                for (char cell : row) {
                    System.out.print(cell + " ");
                }
                System.out.println();
            }
            System.out.println("---------");
            stateNum++;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ZeroSquaresGUI gui = new ZeroSquaresGUI();
            gui.setVisible(true);
        });
    }
}
