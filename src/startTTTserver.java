import java.rmi.*;
public class startTTTserver {
        public static void main(String argv[])
        {
            try
            {
                System.out.println("Arrancando servidor...");
                Naming.rebind("startTTTserver", new TTT() );
            }
            catch (Exception e)
            {
                System.out.println("Ocorreu um problema no arranque do servidor.\n"+e.toString());
            }
        }
    }
