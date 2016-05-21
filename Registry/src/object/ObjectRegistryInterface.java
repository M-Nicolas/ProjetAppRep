package object;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Sarah on 21/05/16.
 */
public interface ObjectRegistryInterface extends Remote {

    void bind(String key, Serializable obj) throws RemoteException;

    Serializable lookup(String key) throws RemoteException;
}
