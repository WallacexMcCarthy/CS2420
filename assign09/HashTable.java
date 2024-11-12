package assign09;

import java.util.ArrayList;
import java.util.List;

/**
 * this class is a HashTable data structure that contains MapEntry objects, effectively making it a HashMap
 * it uses quadratic probing to resolve collisions
 * @param <K> the generic type for the keys in the map
 * @param <V> the generic type for the values in the map
 * @version 11/12/2024
 * @author Wallace McCarthy and Isaac Buehner
 */
public class HashTable<K, V> implements Map<K, V> {
    private Object[] table;
    private int size;
    private double loadFactor = 0.5;
    private final int[] primes = new int[]{11,23,47,97,197,397,797,1597,3203,6421,12853,25717,51437,102877,205759,411527,823117,1646237,3292489,6584983,13169977,26339969,52679969,105359939,210719881,421439783,842879579,1685759167};
    private int currentPrimeIndex = 0;
    private int collisions = 0;
    public HashTable(){
        this.table = new Object[primes[currentPrimeIndex]];
        this.size = 0;
    }

    /**
     * Removes all mappings from this map.
     * <p>
     * O(table length) for a quadratic-probing hash table
     */
    @Override
    public void clear() {
        this.size = 0;
        this.currentPrimeIndex = 0;
        this.table = new Object[primes[currentPrimeIndex]];
    }

    /**
     * Determines whether this map contains the specified key.
     * <p>
     * O(1) for a quadratic-probing hash table
     *
     * @param key - the key being searched for
     * @return true if this map contains the key, false otherwise
     */
    @Override
    public boolean containsKey(K key) {
        int index = key.hashCode() % primes[currentPrimeIndex];
        int quadratic = 0;
        while(getFromArray(index + (quadratic * quadratic)) != null){
            if(getFromArray(index + (quadratic * quadratic)).getKey().equals(key)){
                return true;
            }else{
                quadratic++;
            }
        }
        return false;
    }

    /**
     * Determines whether this map contains the specified value.
     * <p>
     * O(table length) for a quadratic-probing hash table
     *
     * @param value - the value being searched for
     * @return true if this map contains one or more keys to the specified value,
     * false otherwise
     */
    @Override
    public boolean containsValue(V value) {
        for (int i = 0; i < table.length; i++) {
            if(getFromArray(i) != null){
                if(getFromArray(i).getValue().equals(value)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns a list view of the mappings contained in this map, where the ordering of
     * mapping in the list is insignificant.
     * <p>
     * O(table length) for a quadratic-probing hash table
     *
     * @return a List object containing all mapping (i.e., entries) in this map
     */
    @Override
    public List<MapEntry<K, V>> entries() {
        List<MapEntry<K, V>> out = new ArrayList<>();
        for (int i = 0; i < table.length; i++) {
            if(getFromArray(i) != null){
                out.add(getFromArray(i));
            }
        }
        return out;
    }

    /**
     * Gets the value to which the specified key is mapped.
     * <p>
     * O(1) for a quadratic-probing hash table
     *
     * @param key - the key for which to get the mapped value
     * @return the value to which the specified key is mapped, or null if this map
     * contains no mapping for the key
     */
    @Override
    public V get(K key) {
        int index = key.hashCode() % primes[currentPrimeIndex];
        int quadratic = 0;
        while(getFromArray(index + (quadratic * quadratic)) != null){
            if(getFromArray(index + (quadratic * quadratic)).getKey().equals(key)){
                return getFromArray(index + (quadratic * quadratic)).getValue();
            }else{
                quadratic++;
            }
        }
        return null;
    }

    /**
     * Determines whether this map contains any mappings.
     * <p>
     * O(1) for a quadratic-probing hash table
     *
     * @return true if this map contains no mappings, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * (I.e., if the key already exists in this map, resets the value;
     * otherwise adds the specified key-value pair.)
     * <p>
     * O(1) for a quadratic-probing hash table
     *
     * @param key   - the key for which to update the value (if exists)
     *              or to be added to the table
     * @param value - the value to be mapped to the key
     * @return the previous value associated with key, or null if there was no
     * mapping for key
     */
    @Override
    public V put(K key, V value) {
        int index = key.hashCode() % primes[currentPrimeIndex];
        int quadratic = 0;
        while(getFromArray((index + (quadratic * quadratic)) % table.length) != null){
            if(getFromArray((index + (quadratic * quadratic)) % table.length).getKey().equals(key)){
                V out = getFromArray((index + (quadratic * quadratic)) % table.length).getValue();
                getFromArray((index + (quadratic * quadratic)) % table.length).setValue(value);
                collisions++;
                if(((double)size + 1) / table.length >= loadFactor){
                    this.size = 0;
                    int coll = collisions;
                    rehash();
                    collisions = coll;
                }
                return out;
            }else{
                collisions++;
                quadratic++;
            }
        }
        table[(index + (quadratic * quadratic)) % table.length] = new MapEntry<>(key, value);
        size++;
        if(((double)size) / table.length >= loadFactor){
            this.size = 0;
            rehash();
        }
        return null;
    }

    /**
     * Removes the mapping for a key from this map if it is present.
     * <p>
     * O(1) for a quadratic-probing hash table
     *
     * @param key - the key to be removed
     * @return the previous value associated with key, or null if there was no
     * mapping for key
     */
    @Override
    public V remove(K key) {
        int index = key.hashCode() % primes[currentPrimeIndex];
        int quadratic = 0;
        while(getFromArray(index + (quadratic * quadratic)) != null){
            if(getFromArray(index + (quadratic * quadratic)).getKey().equals(key)){
                V out = getFromArray(index + (quadratic * quadratic)).getValue();
                table[index + (quadratic * quadratic)] = null;
                size--;
                return out;
            }else{
                quadratic++;
            }
        }
        return null;
    }

    /**
     * Determines the number of mappings in this map.
     * <p>
     * O(1) for a quadratic-probing hash table
     *
     * @return the number of mappings in this map
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * this method is a helper to get items from the backing array so that unchecked cast warnings are avoided
     * in other methods
     * @param i the index of the item to get
     * @return the item at the index
     */
    @SuppressWarnings("unchecked")
    private MapEntry<K, V> getFromArray(int i) {
        return (MapEntry<K, V>)table[i];
    }

    /**
     * this method is a helper to resize the backing array and rehash all the items back into the new sized array
     */
    private void rehash(){
        List<MapEntry<K,V>> entries = entries();
        currentPrimeIndex++;
        table = new Object[primes[currentPrimeIndex]];
        for(MapEntry<K,V> entry : entries){
            int index = entry.getKey().hashCode() % primes[currentPrimeIndex];
            int quadratic = 0;
            while(getFromArray((index + (quadratic * quadratic)) % table.length) != null){
                quadratic++;
            }
            table[(index + (quadratic * quadratic)) % table.length] = entry;
        }
    }

    public int getCollisions(){
        return this.collisions;
    }
}
