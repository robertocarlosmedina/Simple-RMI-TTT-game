import java.rmi.*;
import java.rmi.server.*;

public class TTT extends UnicastRemoteObject implements tttInterface{
    char board[][] = {
            {'1','2','3'}, /* Initial values are reference numbers */
            {'4','5','6'}, /* used to select a vacant square for */
            {'7','8','9'} /* a turn. */
    };
    int nextPlayer = 0;
    int numPlays = 0;

    public TTT() throws RemoteException{
        System.out.println("Novo Servidor instanciado...");
    }

    public String currentBoard() throws RemoteException{
        String s = "\n\n " +
                board[0][0]+" | " +
                board[0][1]+" | " +
                board[0][2]+" " +
                "\n---+---+---\n " +
                board[1][0]+" | " +
                board[1][1]+" | " +
                board[1][2]+" " +
                "\n---+---+---\n " +
                board[2][0]+" | " +
                board[2][1]+" | " +
                board[2][2] + " \n";
        return s;
    }
    public boolean play(int row, int column, int player) throws RemoteException{

        if (!(row >=0 && row <3 && column >= 0 && column < 3))
            return false;
        if (board[row][column] > '9')
            return false;
        if (player != nextPlayer)
            return false;
        if (numPlays == 9)
            return false;
        board[row][column] = (player == 1) ? 'X' : 'O'; /* Insert player symbol */
        nextPlayer = (nextPlayer + 1) % 2;
        numPlays ++;
        return true;

    }
    public int checkWinner() throws RemoteException{
        int i;
        /* Check for a winning line - diagonals first */
        if((board[0][0] == board[1][1] && board[0][0] == board[2][2]) ||
                (board[0][2] == board[1][1] && board[0][2] == board[2][0])) {
            if (board[1][1]=='X')
                return 1;
            else
                return 0;
        }
        else
            /* Check rows and columns for a winning line */
            for(i = 0; i <= 2; i ++){
                if((board[i][0] == board[i][1] && board[i][0] == board[i][2])) {
                    if (board[i][0]=='X')
                        return 1;
                    else
                        return 0;
                }
                if ((board[0][i] == board[1][i] && board[0][i] == board[2][i])) {
                    if (board[0][i]=='X')
                        return 1;
                    else
                        return 0;
                }
            }
        if (numPlays == 9)
            return 2; /* A draw! */
        else
            return -1; /* Game is not over yet */
    }
}
