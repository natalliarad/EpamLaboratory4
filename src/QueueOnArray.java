import java.util.NoSuchElementException;

/**
 * Parametrized implementation of the collection Queue in the java on the base of Array.
 *
 * @author Natallia Radaman
 * @since 03-2018
 */
public class QueueOnArray<T> {

  private static final int DEFAULT_CAPACITY = 16;
  private int head;
  private int tail;
  /*
   * To store elements of the queue
   */
  private T[] array;
  private int capacity;
  private int size;

  /**
   * Constructor of the user-define capacity queue
   *
   * @param capacity - size of the queue
   */
  public QueueOnArray(int capacity) {
    this.capacity = capacity;
    this.array = (T[]) new Object[DEFAULT_CAPACITY];
    this.size = 0;
    this.head = -1;
    this.tail = 0;
  }

  /**
   * Constructor of the default capacity queue
   */
  public QueueOnArray() {
    this(DEFAULT_CAPACITY);
  }

  /**
   * Check is a QueueOnArray empty or not.
   *
   * @return true if QueueOnArray is empty.
   */
  private boolean isEmpty() {
    return size == 0;
  }

  /**
   * Add new element in the queue
   *
   * @param newElement - an element for adding
   */
  public void enQueue(T newElement) {
    if (size == capacity) {
      T[] newQueue = (T[]) new Object[2 * capacity];
      System.arraycopy(array, 0, newQueue, 0, array.length);
      array = newQueue;
      capacity *= 2;
    }
    size++;
    array[tail++ % capacity] = newElement;
  }

  /**
   * Take the first-go element from the queue
   */
  public T deQueue() {
    if (size == 0) {
      throw new NoSuchElementException();
    }
    size--;
    return array[++head % capacity];
  }

  /**
   * Size of the queue
   */
  public int count() {
    return size;
  }
}
