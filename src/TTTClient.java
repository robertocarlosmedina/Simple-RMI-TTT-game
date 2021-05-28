import java.rmi.*;
public class TTTClient {
    public static void main(String args[]) throws Exception {
        TTTService tttService = null;
        try{
            tttService = (TTTService) Naming.lookup("rmi://" + "localhost" +":"+3002+"/TTTService");

            System.out.println("Found server");
            Game g = new Game(tttService);
            g.playGame();
            g.congratulate();
        }catch(RemoteException e) {System.out.println("[REMOTE]:" + e.getMessage());
        }catch(Exception e) {System.out.println("[Exception]:" + e.getMessage());}
    }
}