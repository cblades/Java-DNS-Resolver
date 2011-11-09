import java.util.ArrayList;

/**
 * A simple mapping of keys to values that preserves the order in which the
 * key value pairs are added
 *
 * @author Chris Blades
 * @version 16/3/2010
 */
public class Map<K, V> {
    /** List of keys */
    private ArrayList<K> keys;
    
    /** List of values */
    private ArrayList<V> values;

    /**
     * Creates a new Map with no key-value pairs
     */
    public Map() {
        this.keys = new ArrayList<K>();
        this.values = new ArrayList<V>();
    }

    /**
     * Inserts a new key-value pair.
     *
     * @param key the new key to add
     * @param value the new value to add
     */
    public void put(K key, V value) {
        keys.add(key);
        values.add(value);
    }

    /**
     * Removes all key-value pairs from the Map
     */
    public void clear() {
        keys.clear();
        values.clear();
    }

    /**
     * Returns a stirng containing all key-value pairs in this Map
     *
     * @string description of all key-value pairs in this Map
     */
    public String toString() {
        String description = "";
        for (int i = 0; i < keys.size(); i++) {
            description += " {" + keys.get(i) + " ==> " + values.get(i) +"} ";
        }
        return description;
    }
}
