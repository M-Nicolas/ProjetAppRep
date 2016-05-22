package object;


import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by Sarah on 21/05/16.
 */
public class ObjectRegistry extends UnicastRemoteObject implements ObjectRegistryInterface {

    private ObjectSharedBase objectSharedBase;

    public ObjectRegistry() throws RemoteException {
        super();
        objectSharedBase = new ObjectSharedBase();
    }

    public void bind(String key, Serializable obj) throws RemoteException {
        System.out.println("Binding the object " + obj.toString() + " with the following key: " + key + " ...");
        objectSharedBase.bind(key, obj);
        System.out.println("Object binded");
    }

    public Serializable lookup(String key) throws RemoteException {
        System.out.println("Looking up for the object corresponding to the key " + key + " ...");
        return objectSharedBase.lookup(key);
    }

    public ArrayList<String> latestRequestedKeys(int number) throws RemoteException {
        System.out.println("Latest " + number + " requested keys: ");
        return objectSharedBase.latestRequestedKeys(number);
    }

    public ArrayList<String> mostRequestedKeys(int n) throws RemoteException {
        System.out.println("Keys that have been requested more than " + n + ": ");
        return objectSharedBase.mostRequestedKeys(n);
    }

    public String latestRequestedKey() throws RemoteException {
        System.out.println("The latest requested key: ");
        return objectSharedBase.latestRequestedKey();
    }

    public String mostRequestedKey() throws RemoteException {
        System.out.println("The most requested key: ");
        return objectSharedBase.mostRequestedKey();
    }
}
