/**
 * WithoutImplHashMap - is an implimentation of the hash map. It has methods: {@link
 * #putElement(Object, Object)}, {@link #getElementbyKey(Object)} )},  {@link
 * #deleteElement(Object)}, {@link #printHashMap()}.
 *
 * @param <K> is a parameter type of key in the hash map.
 * @param <V> is a parameter type of value in the hash map.
 * @author Natallia Radaman
 * @since 03-2018
 */
public class WithoutImplHashMap<K, V> {

  private static final int INITIAL_CAPACITY = 10000;
  private int size;
  public Entry<K, V> entry[];

  /**
   * Constructor of the user-define capacity entry
   *
   * @param size - size of the entry.
   */
  public WithoutImplHashMap(int size) {
    this.size = size;
    this.entry = new Entry[size];
  }

  /**
   * Constructor of the default capacity entry
   */
  public WithoutImplHashMap() {
    this(INITIAL_CAPACITY);
  }

  /**
   * Count hash for the particular key
   */
  private int hashFunction(K key) {
    if (key == null) {
      return 0;
    } else {
      int hash = 7;
      String strKey = key.toString();
      for (int i = 0; i < strKey.length(); i++) {
        hash = hash * 31 + strKey.charAt(i);
      }
      return hash;
    }
  }

  /**
   * Put element in the hash map.
   */
  public void putElement(K key, V value) {
    Entry<K, V> entryNew = new Entry<>(key, value);
    int position = hashFunction(key);
    if (entry[position] == null) {
      entry[position] = entryNew;
    } else {
      Entry root, current;
      root = current = entry[position];
      while (current != null) {
        if (current.getKey() == entryNew.getKey()) {
          current.setValue(entryNew.getValue());
          break;
        } else {
          root = current;
          current = current.getNext();
        }
      }
      root.setNext(entryNew);
    }

  }

  /**
   * Get elemet by its key.
   *
   * @param key - key of th element.
   */
  public void getElementbyKey(K key) {
    int position = hashFunction(key);
    Entry current = entry[position];
    int found = 0;
    while (current != null) {
      if (current.getKey() == key) {
        System.out.println("Key " + key + " Exists with value:" + current.getValue());
        found = 1;
        break;
      }
      current = current.getNext();
    }
    if (found == 0) {
      System.out.println("Key " + key + " does not exist");
    }
  }

  /**
   * Delete elements in the HashMap by its key
   *
   * @param key - key of the element for deleting.
   */
  public void deleteElement(K key) {
    int position = hashFunction(key);
    Entry current, root;
    current = root = entry[position];
    int deleted = 0;
    while (current != null) {
      if (current.getKey() == key) {
        if (root == current) {
          entry[position] = null;
          deleted = 1;
          break;
        } else {
          root.setNext(current.getNext());
          deleted = 1;
          System.out.print("Record deleted");
          break;
        }
      } else {
        root = current;
        current = current.getNext();
      }
    }
    if (deleted == 0) {
      System.out.println("No Record found with key " + key);
    }
  }

  /**
   * Print hash map.
   */
  public void printHashMap() {
    for (int i = 0; i < entry.length; i++) {
      Entry current = entry[i];
      while (current != null) {
        System.out.println("Key: " + current.getKey() + ".    Value: " + current.getValue());
        current = current.getNext();
      }
    }
  }

  class Entry<K, V> {

    private Entry next;
    private K key;
    private V value;

    Entry(K key, V value) {
      this.key = key;
      this.value = value;
      this.next = null;
    }

    public K getKey() {
      return this.key;
    }

    public V getValue() {
      return this.value;
    }

    public void setNext(Entry next) {
      this.next = next;
    }

    public Entry getNext() {
      return this.next;
    }

    public void setValue(V value) {
      this.value = value;
    }
  }
}
