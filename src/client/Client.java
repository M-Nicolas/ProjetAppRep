package client;

import common.ReceiveMessageInterface;
import java.rmi.*;
import java.rmi.registry.*;

/**
 * Created by nicolas on 21/05/16.
 */
public class Client {

    static public void callMethodRegistry() {
        //TODO : Implémenter une méthode d'appel au registre.
    }

    static public void main(String args[]) {

        ReceiveMessageInterface rmiServer;
        Registry registry;

        //adresse, port et message à envoyer
        String serverAddress="localhost";
        String serverPort="8080";
        String text="yolo";

        System.out.println("on envoie "+text+" a "+serverAddress+":"+serverPort);

        try{
            // on recupere le registre
            registry = LocateRegistry.getRegistry(serverAddress,
                    (new Integer(serverPort)).intValue());
            // on cherche le remote object
            rmiServer= (ReceiveMessageInterface)(registry.lookup("serveur"));
            // on fait appel au remote object
            rmiServer.receiveMessage(text);
        }
        //En cas d'exception, on print la trace
        catch(RemoteException e){
            e.printStackTrace();
        } catch(NotBoundException e){
            e.printStackTrace();
        }
    }
}

