import object.ObjectRegistry;

import java.rmi.Naming;

/**
 * Created by Sarah on 21/05/16.
 */
public class ServeurRegistry {

    public ServeurRegistry() {}

    private void initialize() {
        try {
            System.out.println("Object in creation...");

            ObjectRegistry objectRegistry = new ObjectRegistry();

            Naming.rebind("rmi://localhost:4000/ObjectRegistry", objectRegistry);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        ServeurRegistry serveurRegistry = new ServeurRegistry();
        serveurRegistry.initialize();
    }
}
