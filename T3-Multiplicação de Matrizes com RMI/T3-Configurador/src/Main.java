import application.ConfiguradorImp;

/**
 * Created by nando on 6/7/2017.
 */
public class Main {
    public static void main (String[] args)
    {

        if (args.length != 4) {
            System.out.println("Quantidade de paremtros");
            System.exit(1);
        }

        String produtorIP = args[0];
        Integer produtorPorta = new Integer(args[1]);

        String consumidorIP = args[2];
        Integer consumidorPorta = new Integer(args[3]);

        ConfiguradorImp server =  new ConfiguradorImp(produtorIP, consumidorIP, produtorPorta, consumidorPorta);

        try {
            server.configurarServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
