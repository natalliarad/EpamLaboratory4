import java.util.Collection;

/**
 * WithoutImplLinkedList - is a doubly linked list, which can contain different objects, including
 * null. It has methods: {@link #size()}, {@link #isEmpty()},  {@link #addLast(E element)}, {@link
 * #addBefore(Object, Node)}, {@link #contains(Object)}, {@link #get(int)}, {@link #set(int,
 * Object)}, {@link #remove(Object o)}, {@link #clear()}s.
 *
 * @param <E> is a parameter type of Objects in the list.
 * @author Natallia Radaman
 * @since 03-2018
 */
public class WithoutImplLinkedList<E> {

  int size = 0;
  Node<E> first;
  Node<E> last;

  /**
   * Construct an empty WithoutImplLinkedList.
   */
  public WithoutImplLinkedList() {

  }

  /**
   * Return the size of the array
   */
  public int size() {
    return size;
  }

  /**
   * Check is a WithoutImplLinkedList empty or not.
   *
   * @return true if WithoutImplArrayList is empty.
   */
  public boolean isEmpty() {
    return size() == 0;
  }

  /**
   * Add an element to the last position of the WithoutImplLinkedList.
   *
   * @param element - an Object, which is included in the linkedList
   */
  public void addLast(E element) {
    Node<E> lastElement = last;
    Node<E> newNode = new Node<>(element, null, lastElement);
    last = newNode;
    if (lastElement == null) {
      first = newNode;
    } else {
      lastElement.next = newNode;
    }
    size++;
  }

  /**
   * Add an element before some element of the WithoutImplLinkedList.
   *
   * @param element - an Object, which is included in the linkedList
   * @param subsequent - the Object, before which you want to insert an element.
   */
  public void addBefore(E element, Node<E> subsequent) {
    Node<E> previous = subsequent.prev;
    Node<E> newNodeElement = new Node<>(element, subsequent, previous);
    subsequent.prev = newNodeElement;
    if (previous == null) {
      first = newNodeElement;
    } else {
      previous.next = newNodeElement;
    }
    size++;
  }

  /**
   * Check if the index is in the boundaries, and return the corresponding element.
   *
   * @param index - an index of the needed element
   * @return item of the node with the corresponding index.
   */
  public E get(int index) {
    checkPositionElementIndex(index);
    return node(index).item;
  }

  /**
   * Check if the index is in the boundaries, and set the element in place with a specified index.
   *
   * @param index - an index where you want to place an element
   * @return old value of the item
   */
  public E set(int index, E element) {
    checkPositionElementIndex(index);
    Node<E> setNode = node(index);
    E oldNodeValue = setNode.item;
    setNode.item = element;
    return oldNodeValue;
  }

  /**
   * Deletes an object if it is contained in an array.
   *
   * @param o - an Object that has to be deleted.
   */
  public boolean remove(Object o) {
    if (o == null) {
      for (Node<E> node = first; node != null; node = node.next) {
        if (node.item == null) {
          removeLinksOfNodes(node);
          return true;
        }
      }
    } else {
      for (Node<E> node = first; node != null; node = node.next) {
        if (o.equals(node.item)) {
          removeLinksOfNodes(node);
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Delete links on the previous and snext nodes of the Object which has to be deleted.
   */
  private E removeLinksOfNodes(Node<E> node) {
    E element = node.item;
    Node<E> next = node.next;
    Node<E> prev = node.prev;

    if (prev == null) {
      first = next;
    } else {
      prev.next = next;
      node.prev = null;
    }

    if (next == null) {
      last = prev;
    } else {
      next.prev = prev;
      node.next = null;
    }

    node.item = null;
    size--;
    return element;
  }

  /**
   * Removes all elements of an WithoutImplLinkedList
   */
  public void clear() {
    for (Node<E> x = first; x != null; ) {
      Node<E> next = x.next;
      x.item = null;
      x.next = null;
      x.prev = null;
      x = next;
    }
    first = null;
    last = null;
    size = 0;
  }

  /**
   * Check if the WithoutImplArrayList contains this object.
   *
   * @param o - an object which is checked.
   * @return true if the object contains in the WithoutImplArrayList.
   */
  public boolean contains(Object o) {
    return indexOf(o) != -1;
  }

  /**
   * Check if an object is in the WithoutImplLinkedList
   *
   * @return int >= 0 if there is a accordance, -1 - if this object is not in the
   * WithoutImplLinkedList
   */
  public int indexOf(Object o) {
    int index = 0;
    if (o == null) {
      for (Node<E> i = first; i != null; i = i.next) {
        if (i.item == null) {
          return index;
        }
        index++;
      }
    } else {
      for (Node<E> i = first; i != null; i = i.next) {
        if (o.equals(i.item)) {
          return index;
        }
        index++;
      }
    }
    return -1;
  }

  /**
   * Check index of the element.
   */
  private void checkPositionElementIndex(int index) {
    if (!(index >= 0 && index < size)) {
      throw new IndexOutOfBoundsException(
          "Index: " + index + ". Size of the MyLinkedList: " + size + ".");
    }
  }

  Node<E> node(int index) {
    if (index < (size >> 1)) {
      Node<E> x = first;
      for (int i = 0; i < index; i++) {
        x = x.next;
        return x;
      }
    } else {
      Node<E> x = last;
      for (int i = size - 1; i > index; i--) {
        x = x.prev;
        return x;
      }
    }
    return null;
  }

  private static class Node<E> {

    E item;
    Node<E> next;
    Node<E> prev;

    public Node(E item, Node<E> next, Node<E> prev) {
      this.item = item;
      this.next = next;
      this.prev = prev;
    }
  }
}
