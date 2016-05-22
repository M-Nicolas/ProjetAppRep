package common.serviceRMI;

import common.JMSConnectionInformations;
import common.ObjectRegistryInterface;
import common.serializableitem.Album;
import serveur.ServerJMS;

import javax.jms.MessageProducer;
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
    private List<Album> albumList = new ArrayList<>();
    private MessageProducer prod = null;
}
