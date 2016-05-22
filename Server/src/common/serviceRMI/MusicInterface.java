package common.serviceRMI;

import common.JMSConnectionInformations;
import common.serializableitem.Album;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * An Interface of a market service allowing the marketing of music
 * @author Nicolas MEURGUES
 *
 */
public interface MusicInterface extends Remote {

    /**
     * Allows a user to buy an album by giving it's artist and it's name, and have it delivered
     * at it's given adress
     * Return the id you'll have to insert if you want to track your purchase
     * @param artist
     * @param album
     * @param adress
     * @return tracking_ID
     * @throws RemoteException
     */
    public String buyAlbum(String artist, String album, String adress) throws RemoteException;

    /**
     * Return the list of Artist in the given style
     * @param style
     * @return artistList
     * @throws RemoteException
     */
    public List<String> getArtistByStyle(String style) throws RemoteException;

    /**
     * Return the list of Albums made by the given artist
     * @param artist
     * @return albumList
     * @throws RemoteException
     */
    public List<String> getAlbumByArtist(String artist) throws RemoteException;

    /**
     * Get the infos about an Album by giving it's name and it's artist
     * @param artist
     * @param album
     * @return album infos
     * @throws RemoteException
     */
    public Album getAlbumInfos(String artist, String album) throws RemoteException;

    /**
     * allows a customer to track an order by giving it's ID
     * @param trackID
     * @return state of the order
     * @throws RemoteException
     */
    public String trackOrder(String trackID) throws RemoteException;

    /**
     * A method changing the state of an order
     * @param state
     * @param id
     * @return
     * @throws RemoteException
     */
    public boolean changeState(String state, int id) throws RemoteException;

    /**
     * Allows a client to ask for the creation of a Queue in order to communicate with the server
     * @return Queue Information
     * @throws RemoteException
     */
    public JMSConnectionInformations abonnement() throws RemoteException;
}