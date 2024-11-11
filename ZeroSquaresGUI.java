import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ZeroSquaresGUI extends JFrame {
    private State game;
    private JButton[][] buttonGrid;
    private final int GRID_SIZE = 11;

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
                        game=game.movePlayer(1, 0);
                        break;
                    case KeyEvent.VK_A:
                       game= game.movePlayer(0, -1);
                        break;
                    case KeyEvent.VK_D:
                        game=game.movePlayer(0, 1);
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
                    case '1' -> button.setBackground(Color.GRAY); // جدار
                    case 'R' -> button.setBackground(Color.RED);   // قطعة حمراء
                    case 'B' -> button.setBackground(Color.BLUE);  // قطعة زرقاء
                    case 'r' -> button.setBackground(Color.PINK);  // هدف القطعة الحمراء
                    case 'b' -> button.setBackground(Color.CYAN);  // هدف القطعة الزرقاء
                    default -> button.setBackground(Color.WHITE);  // خلية فارغة
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
