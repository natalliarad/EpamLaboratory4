/**
 * Parametrized implementation of the collection Queue in the java on the base of List.
 *
 * @author Natallia Radaman
 * @since 03-2018
 */
public class QueueOnList<T> {

  private ObjectListQueue<T> head = null;
  private ObjectListQueue<T> tail = null;
  public int size = 0;

  /**
   * Add new element in the queue
   *
   * @param newElement - an element for adding
   */
  public void enQueue(T newElement) {
    ObjectListQueue<T> element = new ObjectListQueue<>();
    element.setElementObject(newElement);
    if (head == null) {
      head = element;
    } else {
      tail.setNext(element);
    }
    tail = element;
    size++;
  }

  /**
   * Take the first-go element from the queue
   */
  public T deQueue() {
    if (size == 0) {
      return null;
    }
    T element = head.getElementObject();
    head = head.getNext();
    /*
     * If size of the queue was 1, we should set link on the tail to 0.
     */
    if (head == null) {
      tail = null;
    }
    size--;
    return element;
  }

  /**
   * Inner class of the List
   */
  private class ObjectListQueue<T> {

    private T elementObject;
    private ObjectListQueue<T> next;

    public T getElementObject() {
      return elementObject;
    }

    public void setElementObject(T elementObject) {
      this.elementObject = elementObject;
    }

    public ObjectListQueue<T> getNext() {
      return next;
    }

    public void setNext(ObjectListQueue<T> next) {
      this.next = next;
    }
  }
}
