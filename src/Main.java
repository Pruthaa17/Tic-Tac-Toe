
        import java.awt.*;
        import java.awt.event.*;

public class Main extends Frame implements ActionListener {

    private final char[][] board;
    private char currentPlayer;
    private final Button[][] buttons;

    public Main() {
        board = new char[3][3];
        currentPlayer = 'X';
        buttons = new Button[3][3];

        setLayout(new GridLayout(3, 3));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
                buttons[i][j] = new Button(" ");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 48));
                buttons[i][j].addActionListener(this);                                         //
                buttons[i][j].setBackground(Color.black);
                add(buttons[i][j]);
            }
        }
        setTitle("Tic Tac Toe");
        setSize(400, 300);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        Button clickedButton = (Button) e.getSource();
        int row = -1, col = -1;

        for (int i = 0; i < 3; i++)          // Find the clicked button's position
            for (int j = 0; j < 3; j++)
                if (buttons[i][j] == clickedButton) {
                    row = i;
                    col = j;
                    break;
                }

        if (row != -1 && col != -1 && board[row][col] == ' ') {
            board[row][col] = currentPlayer;
            clickedButton.setLabel(String.valueOf(currentPlayer));
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            clickedButton.setEnabled(false);

            char winner = checkWinner();
            if (winner != ' ')
                if(winner == 'T'){
                    showMessage("Tie !!");
                    disableAllButtons();}
                else{
                    showMessage("Player " + winner + " wins!");
                    disableAllButtons();}
        }
    }

    private char checkWinner() {
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][0] == board[i][2]) || (board[0][i] != ' ' && board[0][i] == board[1][i] && board[0][i] == board[2][i]))
                return board[i][0];
        }

        if ((board[0][0] != ' ' && board[0][0] == board[1][1] && board[0][0] == board[2][2]))
            return board[0][0];
        if ((board[0][2] != ' ' && board[0][2] == board[1][1] && board[0][2] == board[2][0]))
            return board[0][2];
        if (isBoardFull())
            return 'T';
        return ' ';
    }

    private void disableAllButtons() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
                buttons[i][j].setBackground(Color.GRAY);
            }
    }
    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ')
                    return false;
        return true;
    }

    private void showMessage(String message) {
        Frame frame = new Frame("Game Over");
        Label label = new Label(message);
        label.setFont(new Font("Arial", Font.PLAIN, 18));
        frame.add(label);
        frame.setSize(250, 100);
        frame.setBackground(Color.LIGHT_GRAY);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        new Main();
    }
}
