package client;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by Sarah on 22/05/16.
 */
public class ClientJMS implements javax.jms.MessageListener{

    private ArrayList<Message> listMessage = new ArrayList<>();


    private javax.jms.Connection connect = null;
    private javax.jms.Session session = null;
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
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void init(String nom, ArrayList<Message> listMessage){
        try{

            this.listMessage = listMessage;
            session = connect.createSession(false,javax.jms.Session.AUTO_ACKNOWLEDGE);
            Queue queue = (Queue) context.lookup("dynamicQueues/" + nom);
            javax.jms.MessageConsumer qReceiver = session.createConsumer(queue,"typeMess = 'important'");
            qReceiver.setMessageListener(this);

        }
        catch(JMSException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onMessage(Message message) {

        try {
            listMessage.add(message);

            String service = ((MapMessage)message).getString("service");

            if(service.equals("Market")){
                String user = ((MapMessage)message).getString("user");
                System.out.println("\nMessage JMS :");
                if(user != null){
                    System.out.println("  For the user : " + user);
                    System.out.println("  " + ((MapMessage)message).getString("message"));
                }
                else{
                    System.out.println("  For everybody : ");
                    System.out.print(((MapMessage)message).getString("message"));
                }

                System.out.println("\nType a command !\n");

            }
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}

