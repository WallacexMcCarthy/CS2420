package assign09;

import java.util.List;

public class HashTable<K, V> implements Map<K, V> {
    /**
     * Removes all mappings from this map.
     * <p>
     * O(table length) for a quadratic-probing hash table
     */
    @Override
    public void clear() {

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
        return List.of();
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
        return false;
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
        return 0;
    }
}
