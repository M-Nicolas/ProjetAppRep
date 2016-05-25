package common;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Sarah on 21/05/16.
 */
public interface ObjectRegistryInterface extends Remote {

    /**
     * Method that adds (key, object)
     * @param key
     * @param obj
     * @throws RemoteException
     */
    void bind(String key, Serializable obj) throws RemoteException;

    /**
     * Method for looking up for an object with its key
     * @param key
     * @return Serializable object corresponding to the key
     * @throws RemoteException
     */
    Serializable lookup(String key) throws RemoteException;

    /**
     * Method that returns the latest requested keys
     * @param number
     * @return List of latest requested keys
     * @throws RemoteException
     */
    ArrayList<String> latestRequestedKeys(int number) throws RemoteException;

    /**
     * Method that returns the n most requested keys
     * @param n
     * @return List of most requested keys
     * @throws RemoteException
     */
    ArrayList<String> mostRequestedKeys(int n) throws RemoteException;

    /**
     * Method that return the latest requested key
     * @return String key
     * @throws RemoteException
     */
    String latestRequestedKey() throws RemoteException;

    /**
     * Method that returns the most requested key
     * @return key
     * @throws RemoteException
     */
    String mostRequestedKey() throws RemoteException;
}