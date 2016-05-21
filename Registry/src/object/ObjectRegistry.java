package object;


import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

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
        objectSharedBase.add(key, obj);
        System.out.println("Object binded");
    }

    public Serializable lookup(String key) throws RemoteException {
        System.out.println("Looking up for the object corresponding to the key " + key + " ...");
        return objectSharedBase.lookup(key);
    }
}
