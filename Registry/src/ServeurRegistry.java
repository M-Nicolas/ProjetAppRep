import object.ObjectRegistry;

import java.rmi.Naming;

/**
 * Created by Sarah on 21/05/16.
 */
public class ServeurRegistry {

    //public ServeurRegistry() {}

    public void initialize() {
        try {
            System.out.println("Object in creation...");

            if(System.getSecurityManager() == null){
                System.out.println("init SecurityManager");
                System.setSecurityManager(new SecurityManager());
            }

            ObjectRegistry objectRegistry = new ObjectRegistry();

            Naming.rebind("rmi://localhost:4001/ObjectRegistry", objectRegistry);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        ServeurRegistry serveurRegistry = new ServeurRegistry();
        serveurRegistry.initialize();
    }
}
