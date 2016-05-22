package common.serviceRMI;

import common.JMSConnectionInformations;
import common.ObjectRegistryInterface;
import common.serializableitem.Album;
import common.serializableitem.Title;
import serveur.ServerJMS;

import javax.jms.MessageProducer;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nicolas on 22/05/16.
 */
public class Market extends UnicastRemoteObject implements MusicInterface {

    JMSConnectionInformations infoJMSLibrairy;
    private ServerJMS jms;
    private ObjectRegistryInterface objectRI;
    private List<String> albumList = new ArrayList<>();
    private List<Album> albumObject = new ArrayList<>();
    private Map<Integer, String> order;
    private Map<Integer, Album> albumOrder;
    private MessageProducer prod = null;
    private static int id = 0;

    public Market(ObjectRegistryInterface objectRI, JMSConnectionInformations infoJMSLibrairy, ServerJMS jms) throws RemoteException {
        super();
        this.jms = jms;
        this.objectRI = objectRI;
        this.infoJMSLibrairy = infoJMSLibrairy;

        //Création de la base de données de livres
        ArrayList<Title> titleList = new ArrayList<>();
        ArrayList<String> performers = new ArrayList<>();
        performers.add("Opeth");
        titleList.add(new Title("Wreath", "", "Opeth", performers));
        titleList.add(new Title("Deliverance", "", "Opeth", performers));
        titleList.add(new Title("A Fair Judgement", "", "Opeth", performers));
        titleList.add(new Title("For Absent Friends", "", "Opeth", performers));
        titleList.add(new Title("Master's Apprentices", "", "Opeth", performers));
        titleList.add(new Title("By the pain I See in Others", "", "Opeth", performers));
        titleList.add(new Title("Windowpane", "", "Opeth", performers));
        titleList.add(new Title("In My Time Of Need", "", "Opeth", performers));
        titleList.add(new Title("Death Whispered a Lullaby", "", "Opeth", performers));
        titleList.add(new Title("Closure", "", "Opeth", performers));
        titleList.add(new Title("Hope Leaves", "", "Opeth", performers));
        titleList.add(new Title("To Rid the Disease", "", "Opeth", performers));
        titleList.add(new Title("Ending Credits", "", "Opeth", performers));
        titleList.add(new Title("Weakness", "", "Opeth", performers));
        Album album1 = new Album("Opeth", "Deliverance & Damnation", "2004", "Progressive Metal", titleList);

        titleList = new ArrayList<>();
        performers = new ArrayList<>();
        performers.add("Fleshgod Apocalypse");
        titleList.add(new Title("Marche Royale", "", "Fleshgod Apocalypse", performers));
        titleList.add(new Title("In Aeternum", "", "Fleshgod Apocalypse", performers));
        titleList.add(new Title("Healing Through War", "", "Fleshgod Apocalypse", performers));
        titleList.add(new Title("The Fool", "", "Fleshgod Apocalypse", performers));
        titleList.add(new Title("Cold As Perfection", "", "Fleshgod Apocalypse", performers));
        titleList.add(new Title("Mitra", "", "Fleshgod Apocalypse", performers));
        titleList.add(new Title("Paramour (Die Leidenschaft Bringt Leiden)", "", "Fleshgod Apocalypse", performers));
        titleList.add(new Title("And The Vulture Beholds", "", "Fleshgod Apocalypse", performers));
        titleList.add(new Title("Gravity", "", "Fleshgod Apocalypse", performers));
        titleList.add(new Title("A Million Deaths", "", "Fleshgod Apocalypse", performers));
        titleList.add(new Title("Syphilis", "", "Fleshgod Apocalypse", performers));
        titleList.add(new Title("King", "", "Fleshgod Apocalypse", performers));
        Album album2 = new Album("Fleshgod Apocalypse", "King", "2015", "Symphonic Black Death Metal", titleList);

        albumList.add("Opeth Deliverance & Damnation");
        albumObject.add(album1);
        objectRI.bind("Opeth Deliverance & Damnation", album1);

        albumList.add("Fleshgod Apocalypse King");
        albumObject.add(album2);
        objectRI.bind("Fleshgod Apocalypse King", album2);
    }

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
    public String buyAlbum(String artist, String album, String adress) throws RemoteException {
        Album albumGotten = null;
        try{
            albumGotten = (Album) objectRI.lookup(artist + " " + album);
        } catch (ClassCastException e) {}

        if(albumGotten == null && prod != null){

            System.out.println("livre null");

            HashMap<String, String> map = new HashMap<>();
            map.put("message", "BuyAlbum : this Album is not available");

            jms.sendMessage(prod, "Market", map);
            return null;
        }

        if(prod != null){
            HashMap<String, String> map = new HashMap<>();
            map.put("message", "BuyAlbum : Album bought, Album name : " + artist + " " + album);
            jms.sendMessage(prod, "Market", map);
        }

        order.put(id, "need processing");
        albumOrder.put(id, albumGotten);

        return "" + (id++);
    }

    /**
     * Return the list of Artist in the given style
     * @param style
     * @return artistList
     * @throws RemoteException
     */
    public List<String> getArtistByStyle(String style) throws RemoteException {
        List<String> listArtist = new ArrayList<String>();
        for (Album a: albumObject) {
            if (a.getStyle().equals(style)) {
                if (!listArtist.contains(a.getArtist())) {
                    listArtist.add(a.getArtist());
                }
            }
        }
        return listArtist;
    }

    /**
     * Return the list of Albums made by the given artist
     * @param artist
     * @return albumList
     * @throws RemoteException
     */
    public List<String> getAlbumByArtist(String artist) throws RemoteException {
        List<String> listAlbum = new ArrayList<String>();
        for (Album a: albumObject) {
            if (a.getArtist().equals(artist)) {
                if (!listAlbum.contains(a.getAlbum())) {
                    listAlbum.add(a.getAlbum());
                }
            }
        }
        return listAlbum;
    }

    /**
     * Get the infos about an Album by giving it's name and it's artist
     * @param artist
     * @param album
     * @return album infos
     * @throws RemoteException
     */
    public Album getAlbumInfos(String artist, String album) throws RemoteException {
        Album albumGotten = null;
        try{
            albumGotten = (Album) objectRI.lookup(artist + " " + album);
            return albumGotten;
        } catch (ClassCastException e) {}
        return null;
    }

    /**
     * allows a customer to track an order by giving it's ID
     * @param trackID
     * @return state of the order
     * @throws RemoteException
     */
    public String trackOrder(String trackID) throws RemoteException {
        return order.get(id);
    }

    /**
     * Allows a client to ask for the creation of a Queue in order to communicate with the server
     * @return Queue Information
     * @throws RemoteException
     */
    public JMSConnectionInformations abonnement() throws RemoteException {
        String nomQueue = "Queue_"+id;
        prod = jms.initQueue(nomQueue);
        infoJMSLibrairy.setNom(nomQueue);
        return infoJMSLibrairy;
    }
}
