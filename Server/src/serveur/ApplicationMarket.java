package serveur;

import common.InfoJMSConnection;
import common.ObjectRegistryInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by Sarah on 22/05/16.
 */
public class ApplicationMarket {

    private InfoJMSConnection infoJMSMarket = new InfoJMSConnection();

    public static void main(String[] args) {
        ApplicationMarket server = new ApplicationMarket();

        System.out.println("Creating server application market ...");
        server.initApplicationMarket();
    }

    public void initApplicationMarket(){
        try {

            /**
             * On init les info du JMS
             */
            infoJMSMarket.setUrl("tcp://localhost:61616");
            infoJMSMarket.setLogin("user");
            infoJMSMarket.setPassword("user");
            infoJMSMarket.setNom("queueMarket");

            ServerJMS jms = new ServerJMS();
            jms.connection(infoJMSMarket.getUrl(), infoJMSMarket.getLogin(), infoJMSMarket.getPassword());

            ObjectRegistryInterface myRMI = (ObjectRegistryInterface) Naming.lookup("rmi://localhost:4100/ObjectRegistrty");


            Market market = new Market(myRMI, infoJMSMarket, jms);
            Naming.rebind("rmi://localhost:1101/Service",(MusicInterface) market);
            myRMI.rebind("Market", market);


        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

}
