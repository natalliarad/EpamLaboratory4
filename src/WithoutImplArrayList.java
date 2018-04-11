import java.util.*;

/**
 * WithoutImplArrayList - is a resizable ArrayList, which can contain different objects, including
 * null. It has methods: {@link #size()}, {@link #isEmpty()},  {@link #remove(int)}, {@link
 * #remove(Object)}, {@link #contains(Object)}, {@link #get(int)}, {@link #set(int, Object)}, {@link
 * #add(Object)}, {@link #add(int, Object)}, {@link #clear()}s.
 *
 * @param <E> is a parameter type of Objects in the list.
 * @author Natallia Radaman
 * @since 03-2018
 */
public class WithoutImplArrayList<E> {

  private final int INIT_ARRAY_CAPACITY = 16;
  private final int RATE_ARRAY_CAPACITY = 3;
  private int size;
  private E[] arrayData;

  /**
   * Construct an empty WithoutImplArrayList with capacity = 16.
   */
  public WithoutImplArrayList() {
    arrayData = (E[]) new Object[INIT_ARRAY_CAPACITY];
  }

  /**
   * Construct an empty WithoutImplArrayList with custom defined capacity
   *
   * @param capacity - estimated quantity of elements.
   */
  public WithoutImplArrayList(int capacity) {
    if (capacity < 0) {
      throw new IllegalArgumentException("Illegal capacity (minus): " + capacity);
    } else {
      arrayData = (E[]) new Object[capacity];
    }
  }

  /**
   * Return the size of the array
   */
  public int size() {
    return size;
  }

  /**
   * Check is a WithoutImplArrayList empty or not.
   *
   * @return true if WithoutImplArrayList is empty.
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Check a capacity of the c. <p> If the capacity of the WithoutImplArrayList is not enough, then
   * method increases the capacity to the required size.
   *
   * @param minCapacity - minimum WithoutImplArrayList capacity required
   */
  private void confirmationCapacity(int minCapacity) {
    int oldCapacity = arrayData.length;
    if (minCapacity > oldCapacity) {
      int newCapacity = (oldCapacity * RATE_ARRAY_CAPACITY) / 2 + 1;
      if (newCapacity < minCapacity) {
        newCapacity = minCapacity;
      }
      arrayData = Arrays.copyOf(arrayData, newCapacity);
    }
  }

  /**
   * To check if the index goes beyond the bounds of a WithoutImplArrayList
   *
   * @param index - index of an element in the WithoutImplArrayList
   */
  private void checkRangeArray(int index) {
    if (index > size || index < 0) {
      throw new IndexOutOfBoundsException("Index " + index +
          "isn\'t allowed. Size of the WithoutImplArrayList: " + size);
    }
  }

  /**
   * To check whether the indexes go beyond the boundaries of the WithoutImplArrayList, as well as
   * the correctness of the initial and final indices.
   *
   * @param fromIndex - starting index of the subList.
   * @param toIndex - ending index of the subList.
   * @param size - size of the WithoutImplArrayList
   */
  private void sublistCheckArrayRange(int fromIndex, int toIndex, int size) {
    if (fromIndex < 0) {
      throw new IndexOutOfBoundsException("FromIndex is less than 0: " + fromIndex);
    }
    if (toIndex > size) {
      throw new IndexOutOfBoundsException("The toIndex (" + toIndex +
          ") is more than the size of WithoutImplArrayList (" + size + ").");
    }
    if (fromIndex > toIndex) {
      throw new IllegalArgumentException(
          "fromIndex (" + fromIndex + ") > toIndex (" + toIndex + ").");
    }
  }

  /**
   * Delete an item with a specific index
   *
   * @param index - index of the item to be deleted.
   * @return true if the element has been deleted.
   */
  private boolean remove(int index) {
    checkRangeArray(index);
    int nmbChange = size - index - 1;
    if (nmbChange > 0) {
      System.arraycopy(arrayData, index + 1, arrayData, index, nmbChange);
      return true;
    }
    arrayData[--size] = null;
    return false;
  }

  /**
   * Deletes an object if it is contained in an array.
   */
  public boolean remove(Object o) {
    if (o == null) {
      for (int index = 0; index < size; index++) {
        if (arrayData[index] == null) {
          remove(index);
          return true;
        }
      }
    } else {
      for (int index = 0; index < size; index++) {
        if (o.equals(arrayData[index])) {
          remove(index);
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Check if the WithoutImplArrayList contains this object.
   *
   * @param o - an object which is checked.
   * @return true if the object contains in the WithoutImplArrayList.
   */
  public boolean contains(Object o) {
    return indexOf(o) >= 0;
  }

  /**
   * Check if an object is in the WithoutImplArrayList
   *
   * @return int >= 0 if there is a accordance, -1 - if this object is not in the
   * WithoutImplArrayList
   */
  public int indexOf(Object o) {
    if (o == null) {
      for (int i = 0; i < size; i++) {
        if (arrayData[i] == null) {
          return i;
        }
      }
    } else {
      for (int i = 0; i < size; i++) {
        if (o.equals(arrayData[i])) {
          return i;
        }
      }
    }
    return -1;
  }

  /**
   * Check if the index is in the array boundaries, and return the corresponding element.
   *
   * @param index - an index of the needed element
   * @return element from the WithoutImplArrayList with index
   */
  public E get(int index) {
    checkRangeArray(index);
    return (E) arrayData[index];
  }

  /**
   * Check if the index is in the array boundaries, and set the element in place in an array with a
   * specified index.
   *
   * @param index - an index where you want to place an element
   * @return an element
   */
  public E set(int index, E element) {
    checkRangeArray(index);
    arrayData[index] = element;
    return (E) element;
  }

  /**
   * Checks the capacity of the WithoutImplArrayList, optionally increments it and adds an element
   * to the end of the array.
   *
   * @param e - new element of the WithoutImplArrayList
   * @return true - an element is added
   */
  public boolean add(E e) {
    confirmationCapacity(size + 1);
    arrayData[size++] = e;
    return true;
  }

  /**
   * Checks the capacity of the WithoutImplArrayList, optionally increments it and adds an element
   * to the array[index].
   */
  public void add(int index, E element) {
    checkRangeArray(index);
    confirmationCapacity(size + 1);
    System.arraycopy(arrayData, index, arrayData, index + 1, size - index);
    arrayData[index] = element;
    size++;
  }

  /**
   * Removes all elements of an WithoutImplArrayList
   */
  public void clear() {
    for (int i = 0; i < size; i++) {
      arrayData[i] = null;
    }
    size = 0;
  }
}
