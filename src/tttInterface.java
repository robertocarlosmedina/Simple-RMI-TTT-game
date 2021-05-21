import java.rmi.*;
public interface tttInterface extends Remote{
    public String currentBoard() throws RemoteException;
    public boolean play(int row, int column, int player) throws RemoteException;
    public int checkWinner() throws RemoteException;
}
