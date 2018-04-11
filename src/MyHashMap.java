import java.util.*;

public class MyHashMap<K, V> extends AbstractMap<K, V> implements Map<K, V> {

  private static final int INITIAL_CAPACITY = 16;
  private static final int MAXIMUM_CAPACITY = 2 ^ 30;
  private static final float DEFAULT_LOAD_FACTOR = 0.75f;
  private final float loadFactor;
  /*
   * Data structure to hold Entries
   */
  private Entry<K, V>[] elementData;
  /*
   * Data structure to hold Entries while the resizing
   */
  private Entry<K, V>[] resizingElementData;
  private int size;
  private int threshold;
  /*
   * Count of Entries
   */
  private int elementEntryCount;
  Set<K> keySet;
  Collection<V> collectionOfValues;

  public MyHashMap(int initialCapacity, float loadFactor) {
    if (initialCapacity < 0) {
      throw new IllegalArgumentException(
          "Incorrect initial capacity of the MyHashMap<K, V>: " + initialCapacity);
    }
    if (initialCapacity > MAXIMUM_CAPACITY) {
      initialCapacity = MAXIMUM_CAPACITY;
    }
    if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
      throw new IllegalArgumentException(
          "Incorrect load factor of the MyHashMap<K, V>: " + loadFactor);
    }
    int capacity = 1;
    while (capacity < initialCapacity) {
      capacity = capacity ^ 2;
    }

    this.loadFactor = loadFactor;
    threshold = (int) (capacity * loadFactor);
    elementData = new Entry[capacity];
  }

  public MyHashMap(int initialCapacity) {
    this(initialCapacity, DEFAULT_LOAD_FACTOR);
  }

  public MyHashMap() {
    this.loadFactor = DEFAULT_LOAD_FACTOR;
    threshold = (int) (INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
    elementData = new Entry[INITIAL_CAPACITY];
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public boolean containsKey(Object key) {
    return getEntryKey((K) key) != null;
  }

  @Override
  public boolean containsValue(Object value) {
    Entry<K, V>[] table = elementData;
    if (value == null) {
      for (int i = 0; i < table.length; i++) {
        for (Entry<K, V> element = table[i]; element != null; element = element.next) {
          if (element.value == null) {
            return true;
          }
        }
      }
    }
    for (int i = 0; i < table.length; i++) {
      for (Entry<K, V> element = table[i]; element != null; element = element.next) {
        if (value.equals(element.value)) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public V get(Object key) {
    if (key == null) {
      for (Entry<K, V> element = elementData[0]; element != null; element = element.next) {
        if (element.key == null) {
          return element.value;
        }
      }
    }
    int hash = key.hashCode();
    for (Entry<K, V> element = elementData[indexFor(hash, elementData.length)]; element != null;
        element = element.next) {
      K k;
      if (element.hash == hash && (element.key == key || key.equals(element.key))) {
        return element.value;
      }
    }
    return null;
  }

  @Override
  public V put(K key, V value) {
    if (key == null) {
      for (Entry<K, V> element = elementData[0]; element != null; element = element.next) {
        if (element.key == null) {
          V oldValue = element.value;
          element.value = value;
          return oldValue;
        }
      }
      addEntry(0, null, value, 0);
    }
    return null;
  }

  @Override
  public V remove(Object key) {
    return null;
  }

  @Override
  public void putAll(Map<? extends K, ? extends V> m) {

  }

  @Override
  public void clear() {

  }

  @Override
  public Set<K> keySet() {
    return null;
  }

  @Override
  public Collection<V> values() {
    return null;
  }

  @Override
  public Set<Map.Entry<K, V>> entrySet() {
    return null;
  }


  private Entry<K, V> getEntryKey(K key) {
    int hash;
    if (key == null) {
      hash = 0;
    } else {
      hash = key.hashCode();
    }
    for (Entry<K, V> element = elementData[indexFor(hash, elementData.length)]; element != null;
        element = element.next) {
      K k;
      if (element.hash == hash && (element.key == key || (key != null && key
          .equals(element.key)))) {
        return element;
      }
    }
    return null;
  }

  /**
   * Returns index for hash code.
   *
   * @param hash - hash code
   * @param length - lenght of the table elementData
   * @return index for hash
   */
  private int indexFor(int hash, int length) {
    return hash & (length - 1);
  }

  private void addEntry(int hash, K key, V value, int indexFor) {
    Entry<K, V> element = elementData[indexFor];
    elementData[indexFor] = new Entry<K, V>(hash, key, value, element);
    if (size++ >= threshold) {
      resize(2 * elementData.length);
    }
  }

  private void resize(int newCapacity) {
    Entry[] oldElementData = elementData;
    int oldCapacity = oldElementData.length;
    if (oldCapacity == MAXIMUM_CAPACITY) {
      threshold = Integer.MAX_VALUE;
      return;
    }
    Entry[] newElementData = new Entry[newCapacity];
    for (int i = 0; i < oldElementData.length; i++) {
      Entry<K, V> element = oldElementData[i];
      if (element != null) {
        oldElementData[i] = null;
        do {
          Entry<K, V> next = element.next;
          int j = indexFor(element.hash, newCapacity);
          element.next = newElementData[i];
          newElementData[i] = element;
          element = next;
        } while (element != null);
      }
    }
    elementData = newElementData;
    threshold = (int) (newCapacity * loadFactor);
  }

  static class Entry<K, V> implements Map.Entry<K, V> {

    final K key;
    final int hash;
    V value;
    Entry<K, V> next;

    public Entry(int hash, K key, V value, Entry<K, V> next) {
      this.hash = hash;
      this.key = key;
      this.value = value;
      this.next = next;
    }

    @Override
    public K getKey() {
      return key;
    }

    @Override
    public V getValue() {
      return value;
    }

    @Override
    public V setValue(V v) {
      V oldValue = value;
      value = v;
      return oldValue;
    }

    @Override
    public boolean equals(Object obj) {
      if (!(obj instanceof Map.Entry)) {
        return false;
      }
      Map.Entry<K, V> element = (Map.Entry<K, V>) obj;
      K k1 = getKey();
      K k2 = element.getKey();
      if (k1 == k2 || (k1 != null && k1.equals(k2))) {
        V v1 = getValue();
        V v2 = element.getValue();
        if (v1 == v2 || (v1 != null && v1.equals(v2))) {
          return true;
        }
      }
      return false;
    }

    @Override
    public int hashCode() {
      return (key == null ? 0 : key.hashCode()) ^ (value == null ? 0 : value.hashCode());
    }

    @Override
    public String toString() {
      return getKey() + " = " + getValue();
    }
  }

}
