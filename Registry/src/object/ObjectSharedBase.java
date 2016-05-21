package object;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by Sarah on 21/05/16.
 */
public class ObjectSharedBase {

    public ObjectSharedBase() {}

    public static HashMap<String, Serializable> keyMap = new HashMap();

    public void add(String key, Serializable obj) {
        keyMap.put(key, obj);
    }

    public Serializable lookup(String key) {
        if(keyMap.containsKey(key)) {
            return keyMap.get(key);
        }
        return null;
    }
}
