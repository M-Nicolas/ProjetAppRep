package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by nicolas on 21/05/16.
 */
public interface ReceiveMessageInterface extends Remote  {
    void receiveMessage(String x) throws RemoteException;
}