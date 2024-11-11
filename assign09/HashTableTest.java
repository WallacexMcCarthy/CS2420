package assign09;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HashTableTest {
    HashTable<Integer, String> integerHashTable;
    HashTable<Integer, Double> emptyTable;
    @BeforeEach
    public void setUp() {
        integerHashTable = new HashTable<>();
        emptyTable = new HashTable<>();
        integerHashTable.put(0, "0");
        integerHashTable.put(1, "1");
        integerHashTable.put(2, "2");
        integerHashTable.put(3, "3");
        integerHashTable.put(4, "4");
    }

    @Test
    public void testIsEmptyTrue() {
        assertTrue(emptyTable.isEmpty());
    }

    @Test
    public void testIsEmptyFalse() {
        assertFalse(integerHashTable.isEmpty());
    }

    @Test
    public void testSize() {
        assertEquals(5, integerHashTable.size());
    }

    @Test
    public void testSizeOnEmpty() {
        assertEquals(0, emptyTable.size());
    }

    @Test
    public void testClear() {
        assertEquals(5, integerHashTable.size());
        integerHashTable.clear();
        assertEquals(0, integerHashTable.size());
    }

    @Test
    public void testClearOnEmpty() {
        assertEquals(0, emptyTable.size());
        emptyTable.clear();
        assertEquals(0, emptyTable.size());
    }

    @Test
    public void containsKeyTrue() {
        for (int i = 0; i < 4; i++) {
            assertTrue(integerHashTable.containsKey(i));
        }
    }

    @Test
    public void containsKeyFalse() {
        assertFalse(integerHashTable.containsKey(5));
    }

    @Test
    public void containsKeyEmpty() {
        assertFalse(emptyTable.containsKey(0));
    }

    @Test
    public void containsValueTrue() {
        for (int i = 0; i < 4; i++) {
            assertTrue(integerHashTable.containsValue("" + i));
        }
    }

    @Test
    public void containsValueFalse() {
        assertFalse(integerHashTable.containsValue("5"));
    }

    @Test
    public void containsValueEmpty() {
        assertFalse(emptyTable.containsValue(0.0));
    }

    @Test
    public void testEntries() {
        List<MapEntry<Integer, String>> list = integerHashTable.entries();
        for (int i = 0; i < 4; i++) {
            assertEquals(i, list.get(i).getKey());
        }
        assertEquals(5, list.size());
    }

    @Test
    public void testEntriesEmpty() {
        List<MapEntry<Integer, Double>> list = emptyTable.entries();
        assertEquals(0, list.size());
    }

    @Test
    public void testGet() {
        for (int i = 0; i < 4; i++) {
            assertEquals("" + i, integerHashTable.get(i));
        }
    }

    @Test
    public void testGetNull() {
        assertNull(integerHashTable.get(5));
    }

    @Test
    public void testGetEmpty() {
        assertNull(emptyTable.get(0));
    }

    @Test
    public void testPutNull() {
        assertNull(integerHashTable.put(5, "5"));
    }

    @Test
    public void testPutAlreadyInTable() {
        String value = integerHashTable.put(4, "Four");
        assertEquals("4", value);
        assertEquals("Four", integerHashTable.get(4));
    }

    @Test
    public void testPutUntilResize() {
        for (int i = 5; i < 30; i++) {
            integerHashTable.put(i, "" + i);
        }
        assertEquals(30, integerHashTable.size());
        List<MapEntry<Integer, String>> list = integerHashTable.entries();
        for (int i = 0; i < 30; i++) {
            assertEquals(i, list.get(i).getKey());
        }
    }

}
