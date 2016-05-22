package serveur;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;

/**
 * Created by Sarah on 22/05/16.
 */
public class ServerJMS {

    private javax.jms.Connection connect = null;
    private javax.jms.Session sendSession = null;
    InitialContext context = null;

    public void connection(String url, String login, String password){
        try {

            Hashtable properties = new Hashtable();

            properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            properties.put(Context.PROVIDER_URL, url);

            context = new InitialContext(properties);


            javax.jms.ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");

            connect = factory.createConnection();

            connect.start();

            sendSession = connect.createSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);

        } catch (NamingException e) {
            System.out.println("Probleme connection JMS → "+e);
            e.printStackTrace();
        } catch (JMSException e) {
            System.out.println("Probleme connection JMS → "+e);
            e.printStackTrace();
        }
    }

    public MessageProducer initQueue(String nom){
        try{
            Queue queue = (Queue) context.lookup("dynamicQueues/"+nom);
            return sendSession.createProducer(queue);
        }
        catch(JMSException e){
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }

        return null;

    }

    public void sendMessage(MessageProducer sender, String service, HashMap<String, String> listMessage){
        try {
            MapMessage mess = sendSession.createMapMessage();
            mess.setString("service",service);

            Iterator<String> it = listMessage.keySet().iterator();

            for(;it.hasNext();){
                String cle = it.next();
                mess.setString(cle, listMessage.get(cle));
            }

            mess.setStringProperty("typeMess", "important");
            sender.send(mess);

        } catch (JMSException e) {
        }

    }
}