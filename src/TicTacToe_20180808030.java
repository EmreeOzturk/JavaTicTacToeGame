import java.util.Scanner;

public class TicTacToe_20180808030 {
    public static void main(String[] args) {
        TicTacToeLab1 board = new TicTacToeLab1();
        Scanner scanner =new Scanner(System.in);
        System.out.println("Welcome to TicTacToe game");
        while (board.isAvailablePositionExists()){
            System.out.println("Select position for player "+board.getCurrentPlayer());
            System.out.print("X : ");
            int posX = scanner.nextInt();
            System.out.print("Y : ");
            int posY = scanner.nextInt();
            if(board.putMark(posX,posY)){
                return;
            }
        }
    }
}
class TicTacToeLab1 {
    public static final int X = 1, O = -1, EMPTY = 0;
    private int[][] board ;
    private int currentPlayer;

    private final static int[][][] winnerIndices = {
            {{0,0}, {0,1}, {0,2}},
            {{1,0}, {1,1}, {1,2}},
            {{2,0}, {2,1}, {2,2}},

            {{0,0}, {1,0}, {2,0}},
            {{0,1}, {1,1}, {2,1}},
            {{0,2}, {1,2}, {2,2}},

            {{0,0}, {1,1}, {2,2}},
            {{0,2}, {1,1}, {2,0}},
    };

    public TicTacToeLab1() {
        this.board = new int[3][3];
        this.currentPlayer = X;
    }

    public boolean putMark(int i, int j) throws IllegalArgumentException {
        if ((i < 0) || (i > 2) || (j < 0) || (j > 2))
            throw new IllegalArgumentException("Invalid board position");
        if(!isAvailablePositionExists()){
            throw new IllegalArgumentException("Not available any position game ended in a draw");
        }
        if (board[i][j] != EMPTY)
            throw new IllegalArgumentException("Board position occupied");

        board[i][j] = currentPlayer;
        if(isWinner(currentPlayer)){
            printBoard();
            System.out.println("Player "+ getWinner()+" is winner");
            return true;
        }
        printBoard();
        currentPlayer = -1 * currentPlayer;
        return false;


    }
    public String getCurrentPlayer(){
        switch (this.currentPlayer) {
            case 1 -> {
                return "X";
            }
            case -1 -> {
                return "O";
            }
        }
        return null;
    }

    public boolean isWinner(int player) {

        for(int[][] positions: winnerIndices){
            int sum = 0;
            for(int[] position: positions){
                sum += board[position[0]][position[1]];
            }
            if(sum == player *3)
                return true;
        }
        return false;
    }


    public int getWinner(){
        if (isWinner(X))
            return X;
        else if (isWinner(O))
            return O;
        else
            return EMPTY;
    }

    public void printBoard() {
        for(int[] line: this.board){
            for(int cell: line){
                if(cell == X){
                    System.out.print("X");
                }
                else if(cell == O){
                    System.out.print("O");
                }
                else if(cell == EMPTY){
                    System.out.print("-");
                }
            }
            System.out.println();
        }
    }


    public boolean isAvailablePositionExists(){
        for(int[] line: this.board){
            for(int cell: line){
                if(cell == 0)
                    return true;
            }
        }
        return false;
    }
}
