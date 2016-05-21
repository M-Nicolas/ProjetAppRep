package serveur;

import common.ReceiveMessageInterface;
import java.rmi.*;
import java.rmi.registry.*;
import java.net.*;

/**
 * Created by nicolas on 21/05/16.
 */
public class Serveur extends java.rmi.server.UnicastRemoteObject implements ReceiveMessageInterface {
    //Port, adresse et registre du Serveur
    int      thisPort;
    String   thisAddress;
    Registry registry;

    /**
     * On initialise le Serveur et lance l'execution.
     * @throws RemoteException
     */
    public Serveur() throws RemoteException {
        try{
            // on cherche l'adresse de l'hôte
            thisAddress = (InetAddress.getLocalHost()).toString();
        }
        catch(Exception e){
            throw new RemoteException("adresse non récupéré.");
        }
        thisPort=8080;
        System.out.println("cette adresse = "+thisAddress+", port = "+thisPort);
        try{
            // on crée le registre et lui assigne nom et objet
            registry = LocateRegistry.createRegistry(thisPort);
            registry.rebind("serveur", this);
        } catch(RemoteException e){
            throw e;
        }
    }

    /**
     * Méthode invoqué depuis le client distant par le RMI.
     * @param x
     * @throws RemoteException
     */
    public void receiveMessage(String x) throws RemoteException {
        System.out.println(x);
    }

    static public void main(String args[]) {
        try{
            Serveur s=new Serveur();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}