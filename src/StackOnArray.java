/**
 * Parametrized implementation of the Stack in the java on the base of Array.
 *
 * @author Natallia Radaman
 * @since 03-2018
 */
public class StackOnArray<T> {

  private final static int DEFAULT_CAPACITY = 16;
  private T[] arrayForStack;
  private int size;
  public int top;

  /**
   * Constructor of the user-define capacity queue
   *
   * @param size - size of the queue
   */
  public StackOnArray(int size) {
    this.size = size;
    arrayForStack = (T[]) new Object[size];
    top = -1;
  }

  /**
   * Constructor of the default capacity queue
   */
  public StackOnArray() {
    this(DEFAULT_CAPACITY);
  }

  /**
   * Add new element in stack
   */
  public void addElementInStack(T element) {
    if (!isFull()) {
      arrayForStack[++top] = element;
    }
  }

  /**
   * Get the element from the top of the stack
   */
  public T getElementInStack() {
    if (!isEmpty()) {
      return arrayForStack[top--];
    }
    return null;
  }

  /**
   * Read the element from the topmof the stack
   */
  public T readTopElement() {
    return arrayForStack[top];
  }

  /**
   * Check is a StackOnArray empty or not.
   *
   * @return true if StackOnArray is empty.
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Check is a StackOnArray full or not.
   *
   * @return true if StackOnArray is full.
   */
  public boolean isFull() {
    return (top == size - 1);
  }
}
