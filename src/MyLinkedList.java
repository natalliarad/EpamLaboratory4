import java.util.*;

public class MyLinkedList<E> implements List<E> {

  int size = 0;
  Node<E> first;
  Node<E> last;

  public MyLinkedList() {

  }

  public MyLinkedList(Collection<? extends E> collection) {
    this();
    addAll(collection);
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size() == 0;
  }

  @Override
  public boolean contains(Object o) {
    return indexOf(o) != -1;
  }

  @Override
  public Iterator<E> iterator() {
    return null;
  }

  @Override
  public Object[] toArray() {
    Object[] newArray = new Object[size];
    int index = 0;
    for (Node<E> node = first; node != null; node = node.next) {
      newArray[index++] = node.item;
    }
    return newArray;
  }

  //TODO Implement this method
  @Override
  public <T> T[] toArray(T[] a) {
    return null;
  }

  @Override
  public boolean add(E e) {
    addLast(e);
    return true;
  }

  @Override
  public boolean remove(Object o) {
    if (o == null) {
      for (Node<E> node = first; node != null; node = node.next) {
        if (node.item == null) {
          removeLinks(node);
          return true;
        }
      }
    } else {
      for (Node<E> node = first; node != null; node = node.next) {
        if (o.equals(node.item)) {
          removeLinks(node);
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    return false;
  }

  @Override
  public boolean addAll(Collection<? extends E> c) {
    return addAll(size, c);
  }

  @Override
  public boolean addAll(int index, Collection<? extends E> c) {
    checkPositionIndex(index);
    Object[] temporaryArray = c.toArray();
    int lengthTemporaryArray = temporaryArray.length;
    if (lengthTemporaryArray == 0) {
      return false;
    }

    Node<E> previous;
    Node<E> subsequent;
    if (index == size) {
      subsequent = null;
      previous = last;
    } else {
      subsequent = node(index);
      previous = subsequent.prev;
    }

    for (Object o : temporaryArray) {
      E element = (E) o;
      Node<E> newNode = new Node<>(element, null, previous);
      if (previous == null) {
        first = newNode;
      } else {
        previous.next = newNode;
      }
      previous = newNode;
    }

    if (subsequent == null) {
      last = previous;
    } else {
      previous.next = subsequent;
      subsequent.prev = previous;
    }
    size += lengthTemporaryArray;
    return true;
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    return false;
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    return false;
  }

  @Override
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

  @Override
  public E get(int index) {
    checkElementPositionIndex(index);
    return node(index).item;
  }

  @Override
  public E set(int index, E element) {
    checkElementPositionIndex(index);
    Node<E> setNode = node(index);
    E oldNodeValue = setNode.item;
    setNode.item = element;
    return oldNodeValue;
  }

  @Override
  public void add(int index, E element) {
    checkPositionIndex(index);
    if (index == size) {
      addLast(element);
    } else {
      addBefore(element, node(index));
    }
  }

  @Override
  public E remove(int index) {
    checkElementPositionIndex(index);
    return removeLinks(node(index));
  }

  @Override
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

  @Override
  public int lastIndexOf(Object o) {
    int index = size;
    if (o == null) {
      for (Node<E> i = last; i != null; i = i.prev) {
        index--;
        if (i.item == null) {
          return index;
        }
      }
    } else {
      for (Node<E> i = last; i != null; i = i.prev) {
        index--;
        if (o.equals(i.item)) {
          return index;
        }
      }
    }
    return -1;
  }

  @Override
  public ListIterator<E> listIterator() {
    if (size == 0) {
      throw new IndexOutOfBoundsException("Your MyLinkedList has no elements.");
    }
    return listIterator(0);
  }

  //TODO Create new class for implementation methods of ListIterator for Linked List
  @Override
  public ListIterator<E> listIterator(int index) {
    checkPositionIndex(index);
    return new ListIter(index);
  }

  @Override
  public List<E> subList(int fromIndex, int toIndex) {
    return null;
  }

  private boolean isElementIndex(int index) {
    return index >= 0 && index < size;
  }

  private boolean isPositionIndex(int index) {
    return index >= 0 && index <= size;
  }

  private void checkElementPositionIndex(int index) {
    if (!isElementIndex(index)) {
      throw new IndexOutOfBoundsException(
          "Index: " + index + ". Size of the MyLinkedList: " + size + ".");
    }
  }

  private void checkPositionIndex(int index) {
    if (!isPositionIndex(index)) {
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

  private void addLast(E element) {
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

  private void addBefore(E element, Node<E> subsequent) {
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

  private E removeLinks(Node<E> node) {
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

  private class ListIter implements ListIterator<E> {

    private Node<E> returnedNodeLast;
    private Node<E> nextNode;
    private int nextNodeIndex;

    ListIter(int index) {
      if (index == size) {
        nextNode = null;
      } else {
        nextNode = node(index);
      }
      nextNodeIndex = index;
    }

    @Override
    public boolean hasNext() {
      return nextNodeIndex < size;
    }

    @Override
    public E next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      returnedNodeLast = nextNode;
      nextNode = nextNode.next;
      nextNodeIndex++;
      return returnedNodeLast.item;
    }

    @Override
    public boolean hasPrevious() {
      return nextNodeIndex > 0;
    }

    @Override
    public E previous() {
      if (!hasPrevious()) {
        throw new NoSuchElementException();
      }
      if (nextNode == null) {
        returnedNodeLast = nextNode = last;
      } else {
        returnedNodeLast = nextNode = nextNode.prev;
      }
      nextNodeIndex--;
      return returnedNodeLast.item;
    }

    @Override
    public int nextIndex() {
      return nextNodeIndex;
    }

    @Override
    public int previousIndex() {
      return nextNodeIndex - 1;
    }

    @Override
    public void remove() {
      if (returnedNodeLast == null) {
        throw new IllegalStateException();
      }
      Node<E> lastNextNode = returnedNodeLast.next;
      removeLinks(returnedNodeLast);
      if (nextNode == returnedNodeLast) {
        nextNode = lastNextNode;
      } else {
        nextNodeIndex--;
      }
      returnedNodeLast = null;
    }

    @Override
    public void set(E e) {
      if (returnedNodeLast == null) {
        throw new IllegalStateException();
      }
      returnedNodeLast.item = e;
    }

    @Override
    public void add(E e) {
      returnedNodeLast = null;
      if (nextNode == null) {
        addLast(e);
      } else {
        addBefore(e, nextNode);
      }
      nextNodeIndex++;
    }
  }

  public static void main(String[] args) {
    MyLinkedList<Integer> myLinkedList = new MyLinkedList<Integer>();
    System.out
        .println("Size of the LinkedList before the element adding: " + myLinkedList.size);
    for (int i = 0; i < 13; i++) {
      myLinkedList.add(i);
    }
    myLinkedList.add(3, 4);
    System.out.println("Size of array after the element adding: " + myLinkedList.size());
    System.out.println("Get the element with index = 1 -> " + myLinkedList.get(1));
    myLinkedList.set(2, 100000);
    System.out.println(myLinkedList.get(2) + " - index 1 - set to 100000 and get it.");
    MyLinkedList<String> list1 = new MyLinkedList<>();
    list1.add("One");
    list1.add("Two");
    list1.add("Three");
    System.out.println(list1.get(1));
//        list1.remove(1);
//        System.out.println(list1.get(1));
    list1.clear();
    System.out.println(
        "Print the size of the MyLinkedList after deleting all elements - " + list1.size());
  }
}
