package common.command;

import common.serviceRMI.MusicInterface;

import javax.jms.JMSException;
import javax.jms.Message;
import java.rmi.RemoteException;
import java.util.List;

/**
 * The BuyAlbum's command class
 * @author Nicolas MEURGUES
 *
 */
public class BuyAlbumCommand {

    private String artist;
    private String album;
    private String adress;
    private MusicInterface market;
    private List<Message> messageList;

    public BuyAlbumCommand(MusicInterface market, String artist, String album, String adress, List<Message> messageList) {
        super();
        this.artist = artist;
        this.album = album;
        this.adress = adress;
        this.market = market;
        this.messageList = messageList;
    }

    public void execute() throws RemoteException, JMSException {
        System.out.println("→market.buyAlbum("+artist + ", " + album + ", " + adress + ")");
        String albumDatas = market.buyAlbum(artist, album, adress);

        if(album == null) {
            System.out.println("An error occured while you were purchasing your album");
        } else {
            System.out.println("Your album will be delivered to this adress :\n" +
                                adress+ "\n\nHere you have your tracking ID :\n\n" +
                                albumDatas);
        }



    }
}