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
import java.util.List;

/**
 * Created by nicolas on 22/05/16.
 */
public class Market extends UnicastRemoteObject implements MusicInterface {

    JMSConnectionInformations infoJMSLibrairy;
    private ServerJMS jms;
    private ObjectRegistryInterface objectRI;
    private List<String> albumList = new ArrayList<>();
    private MessageProducer prod = null;

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

        albumList.add("Opeth Delive");
        objectRI.bind("Opeth Delive", album1);

        albumList.add("Fleshgod King");
        objectRI.bind("Fleshgod King", album2);

    }
}
