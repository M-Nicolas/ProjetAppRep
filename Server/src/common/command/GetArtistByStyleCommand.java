package common.command;

import common.serviceRMI.MusicInterface;

import javax.jms.JMSException;
import javax.jms.Message;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by nicolas on 22/05/16.
 */
public class GetArtistByStyleCommand {

    private String style;
    private MusicInterface market;
    private List<Message> messageList;

    public GetArtistByStyleCommand(String style, MusicInterface market, List<Message> messageList) {
        this.style = style;
        this.market = market;
        this.messageList = messageList;
    }

    public void execute() throws RemoteException, JMSException {
        System.out.println("→market.getArtistByStyle("+style + ")");
        List<String> artistList = market.getArtistByStyle(style);

        if (artistList == null) {
            System.out.println("An error occured while you were search for a music style.\n"
                    + "Are you sure that your requested style exist?");
        } else {
            String list = artistList.get(0);

            for (String s: artistList) {
                if (!s.equals(artistList.get(0))) {
                    list += "\n" + s;
                }
            }
            list += "\n";

            System.out.println("Here's a list of artist in the style " +
                    style+ " :\n" + list);
        }
    }
}
