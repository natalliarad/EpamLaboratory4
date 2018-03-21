import java.util.*;

public class MyArrayList<E> implements List<E> {
    private final int INIT_CAPACITY = 16;
    private final int RATE_CAPACITY = 3;
    private int size;
    private E[] arrayData;

    /**
     * Construct an empty MyArrayList with capacity = 16.
     */
    public MyArrayList() {
        super();
        arrayData = (E[]) new Object[INIT_CAPACITY];
    }

    /**
     * Construct an empty MyArrayList with custom defined capacity
     *
     * @param capacity - estimated quantity of elements.
     */
    public MyArrayList(int capacity) {
        super();
        if (capacity < 0) {
            throw new IllegalArgumentException("Illegal capacity (minus): " + capacity);
        } else {
            arrayData = (E[]) new Object[capacity];
        }
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
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int cursor;

            @Override
            public boolean hasNext() {
                if (size >= arrayData.length) {
                    return false;
                }
                return true;
            }

            @Override
            public E next() {
                int i = cursor;
                if (i >= size) {
                    throw new NoSuchElementException();
                }
                E[] newArrayData = arrayData;
                if (i > newArrayData.length) {
                    throw new ConcurrentModificationException();
                }
                cursor = i + 1;
                return (E) newArrayData[i];
            }
        };
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(arrayData, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            return (T[]) Arrays.copyOf(arrayData, size, a.getClass());
        System.arraycopy(arrayData, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    @Override
    public boolean add(E e) {
        confirmationCapacity(size + 1);
        arrayData[size++] = e;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (int index = 0; index < size; index++) {
                if (arrayData[index] == null) {
                    elementRemove(index);
                    return true;
                }
            }
        } else {
            for (int index = 0; index < size; index++) {
                if (o.equals(arrayData[index])) {
                    elementRemove(index);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Iterator<?> e = c.iterator();
        while (e.hasNext())
            if (!contains(e.next()))
                return false;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Object[] newArray = c.toArray();
        int lengthNewArray = newArray.length;
        confirmationCapacity(size + lengthNewArray);
        System.arraycopy(newArray, 0, arrayData, size, lengthNewArray);
        size += lengthNewArray;
        return lengthNewArray != 0;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        checkArrayRange(index);
        Object[] newArrayToAdd = c.toArray();
        int lengthNewArrayToAdd = newArrayToAdd.length;
        confirmationCapacity(lengthNewArrayToAdd);
        int numberToMove = size - index;
        if (numberToMove > 0) {
            System.arraycopy(arrayData, index, arrayData, index + numberToMove, numberToMove);
        }
        System.arraycopy(newArrayToAdd, 0, arrayData, index, numberToMove);
        size += numberToMove;
        return numberToMove != 0;
    }

    //TODO This method (removeAll) is not already overridden.
    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    //TODO This method (retainAll) is not already overridden.
    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            arrayData[i] = null;
        }
        size = 0;
    }

    @Override
    public E get(int index) {
        checkArrayRange(index);
        return (E) arrayData[index];
    }

    @Override
    public E set(int index, E element) {
        checkArrayRange(index);
        arrayData[index] = element;
        return (E) element;
    }

    @Override
    public void add(int index, E element) {
        checkArrayRange(index);
        confirmationCapacity(size + 1);
        System.arraycopy(arrayData, index, arrayData, index + 1, size - index);
        arrayData[index] = element;
        size++;
    }

    @Override
    public E remove(int index) {
        elementRemove(index);
        return null;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (arrayData[i] == null)
                    return i;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(arrayData[i]))
                    return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (arrayData[i] == null)
                    return i;
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (o.equals(arrayData[i]))
                    return i;
            }
        }
        return -1;
    }

    //TODO ListIterator<E> listIterator() is not already overridden.
    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    //TODO ListIterator<E> listIterator(int index) is not already overridden.
    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }


    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        sublistCheckArrayRange(fromIndex, toIndex, size);
        MyArrayList<E> subList = new MyArrayList<E>(toIndex - fromIndex);
        int indexSubList = 0;
        for (int i = fromIndex; i < toIndex; i++) {
            subList.set(indexSubList, arrayData[i]);
            indexSubList++;
        }
        return subList;
    }

    /**
     * Check a capacity of the MyArrayList.
     * <p>
     * If the capacity of the MyArrayList is not enough, then method increases the capacity to the required size.
     * @param minCapacity - minimum MyArrayList capacity required
     */
    private void confirmationCapacity(int minCapacity) {
        int oldCapacity = arrayData.length;
        if (minCapacity > oldCapacity) {
            int newCapacity = (oldCapacity * RATE_CAPACITY) / 2 + 1;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            arrayData = Arrays.copyOf(arrayData, newCapacity);
        }
    }

    /**
     * To check if the index goes beyond the bounds of a MyListArray
     * @param index - index of an element in the MyListArray
     */
    private void checkArrayRange(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index " + index + "isn\'t allowed. Size of the MyArrayList: " + size);
        }
    }

    /**
     * To check whether the indexes go beyond the boundaries of the MyListArray, as well as the correctness
     * of the initial and final indices.
     * @param fromIndex - starting index of the subList.
     * @param toIndex - ending index of the subList.
     * @param size - size of the MyArrayList
     */
    private void sublistCheckArrayRange(int fromIndex, int toIndex, int size) {
        if (fromIndex < 0)
            throw new IndexOutOfBoundsException("FromIndex is less than 0: " + fromIndex);
        if (toIndex > size)
            throw new IndexOutOfBoundsException("The toIndex (" + toIndex + ") is more than the size of MyArraylist ("
                    + size + ").");
        if (fromIndex > toIndex)
            throw new IllegalArgumentException("fromIndex (" + fromIndex + ") > toIndex (" + toIndex + ").");
    }

    /**
     * Remove an element from the MyListArray
     * @param index - index of element that has to be deleted.
     * @return true if the element has been deleted.
     */
    private boolean elementRemove(int index) {
        checkArrayRange(index);
        int numberChange = size - index - 1;
        if (numberChange > 0) {
            System.arraycopy(arrayData, index + 1, arrayData, index, numberChange);
            return true;
        }
        arrayData[--size] = null;
        return false;
    }

    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<Integer>();
        System.out.println("Size of array before the element adding: " + list.size());
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        list.add(1, 100);
        System.out.println("Size of array after the element adding: " + list.size());
        System.out.println(list.get(1)+ " - get the element with index = 1");
        list.set(1, 200);
        System.out.println(list.get(1) + " - index 1 - set to 200 and get it.");
        MyArrayList<String> list1 = new MyArrayList<String>();
        list1.add("One");
        list1.add("Two");
        list1.add("Three");
        System.out.println(list1.get(1));
        list1.remove(1);
        System.out.println(list1.get(1));
        list1.clear();
        System.out.println("Print the size of the MyListArray after deleting all elements - " + list1.size());
    }
}
