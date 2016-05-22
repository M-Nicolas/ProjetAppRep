package object;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Sarah on 21/05/16.
 */
public class ObjectSharedBase {

    /**
     * Map of all objects and their corresponding keys
     */
    private static HashMap<String, Serializable> keyMap = new HashMap();

    /**
     * List of requested keys ordered
     */
    private static ArrayList<String> orderReqKey = new ArrayList<String>();

    /**
     * Map of the requested keys and their corresponding number of request done by users
     */
    private static HashMap<String, Integer> numberReqKey = new HashMap<String, Integer>();

    /**
     * Default constructor
     */
    public ObjectSharedBase() {}

    /**
     * Method that adds (key, object)
     * @param key
     * @param obj
     */
    public void bind(String key, Serializable obj) {
        orderReqKey.add(key);
        keyMap.put(key, obj);
        numberReqKey.put(key, 0);
    }

    /**
     * Method for looking up for an object with its key
     * @param key
     * @return Serializable object corresponding to the key
     */
    public Serializable lookup(String key) {
        if(keyMap.containsKey(key)) {
            numberReqKey.put(key, numberReqKey.get(key) + 1);
            return keyMap.get(key);
        }
        return null;
    }

    /**
     * Method that returns the latest requested keys
     * @param number
     * @return List of latest requested keys
     */
    public ArrayList<String> latestRequestedKeys (int number) {
        ArrayList<String> list = new ArrayList<String>();
        int size = orderReqKey.size();
        if(number >= size) return null;

        for(int i = size - 1; i > size - number; i--) {
            list.add(orderReqKey.get(i));
        }

        return list;
    }

    /**
     * Method that returns the n most requested keys
     * @param n
     * @return List of most requested keys
     */
    public ArrayList<String> mostRequestedKeys(int n) {
        ArrayList<String> list = new ArrayList<String>();
        int count = n;
        TreeMap<String, Integer> mapSorted = sortHashMap();
        Iterator<String> iterator = mapSorted.keySet().iterator();
        while(iterator.hasNext() && count > 0) {
            String key = iterator.next();
            list.add(key);
            count --;
        }
        return list;
    }

    /**
     * Method that return the latest requested key
     * @return String key
     */
    public String latestRequestedKey () {
        return orderReqKey.get(orderReqKey.size() - 1);
    }

    /**
     * Method that returns the most requested key
     * @return key
     */
    public String mostRequestedKey() {
        return sortHashMap().firstKey();
    }

    /**
     * Method that returns a map classified in ascending order
     * @return TreeMap
     */
    private TreeMap<String, Integer> sortHashMap() {
        TreeMap<String, Integer> sortedNumberReqKey = new TreeMap<String, Integer>(new Comparator<String>() {
            public int compare(String o1, String o2) {
                if(numberReqKey.get(o1) >= numberReqKey.get(o2) ) {
                    System.out.println(numberReqKey.get(o1) + numberReqKey.get(o2));
                    return -1;
                }
                else return 1;
            }
        });

        sortedNumberReqKey.putAll(numberReqKey);
        return sortedNumberReqKey;
    }
}


