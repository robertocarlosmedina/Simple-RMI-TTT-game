import java.rmi.*;
public interface TTTService extends Remote {
    String currentBoard() throws RemoteException;
    boolean play(int row, int column, int player) throws RemoteException;
    int checkWinner() throws RemoteException;
    void restart() throws RemoteException;
    void removeLastPlay() throws RemoteException;
}