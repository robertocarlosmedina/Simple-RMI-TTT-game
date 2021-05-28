import java.util.Scanner;
import java.rmi.RemoteException;
public class Game {
    private TTTService ttt;
    private Scanner keyboardSc;
    private int winner = 0;
    private int player = 1;
    public Game(TTTService _ttt) throws RemoteException {

        ttt = _ttt;
        keyboardSc = new Scanner(System.in);

    }
    public int readPlay() throws RemoteException{
        int play;
        do {
            System.out.printf("\nPlayer %d, please enter the number of the square "+ "where you want to place your %c (or 0 to refresh the board): \n", player, (player == 1) ? 'X' : 'O');

            play = keyboardSc.nextInt();

            System.out.println(play);
            if(play == 11){
                System.out.println("foi um 11");
                ttt.removeLastPlay();
                play = 0;
            }

        } while (play > 9 || play < 0);
        return play;
    }
    public void playGame() throws RemoteException{
        int play;
        boolean playAccepted;
        do {
            player = ++player % 2;
            do {
                System.out.println(ttt.currentBoard());
                play = readPlay();
                if (play != 0) {
                    playAccepted = ttt.play( --play / 3, play % 3, player);
                    if (!playAccepted)
                        System.out.println("Invalid play! Try again.");

                } else
                    playAccepted = false;

            } while (!playAccepted);
            winner = ttt.checkWinner();
        } while (winner == -1);
    }

    public void congratulate() throws RemoteException{
        if (winner == 2)
            System.out.printf("\nHow boring, it is a draw\n");
        else
            System.out.printf("\nCongratulations, player %d, YOU ARE THE WINNER!\n",winner);

        ttt.restart();
    }
}