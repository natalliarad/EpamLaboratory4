public class ExampleOfLists {

  public static void main(String[] args) {
    System.out.println("\n\nExample of the ArrayList\n");
    WithoutImplArrayList<Integer> list = new WithoutImplArrayList<>();
    System.out.println("Size of array before the element adding: " + list.size());
    for (int i = 0; i < 20; i++) {
      list.add(i);
    }
    list.add(1, 100);
    System.out.println("Size of array after the element adding: " + list.size());
    System.out.println(list.get(1) + " - get the element with index = 1");
    list.set(1, 200);
    System.out.println(list.get(1) + " - index 1 - set to 200 and get it.");

    WithoutImplArrayList<String> list1 = new WithoutImplArrayList<String>(30);
    list1.add("One");
    list1.add("Two");
    list1.add("Three");
    System.out.println(list1.get(1));
    list1.remove(1);
    System.out.println(list1.get(1));
    list1.clear();
    System.out.println(
        "Print the size of the WithoutImplArrayList after deleting all elements - " + list1.size());

    System.out.println("\n\nExample of the LinkedList\n");
    WithoutImplLinkedList<Integer> myLinkedList = new WithoutImplLinkedList<Integer>();
    System.out.println("Size of the LinkedList before the element adding: " + myLinkedList.size);
    for (int i = 0; i < 13; i++) {
      myLinkedList.addLast(i);
    }
    System.out.println("Size of array after the element adding: " + myLinkedList.size());
    System.out.println("Get the element with index = 1 -> " + myLinkedList.get(1));
    myLinkedList.set(2, 100000);
    System.out.println(myLinkedList.get(2) + " - index 1 - set to 100000 and get it.");

    WithoutImplLinkedList<String> list2 = new WithoutImplLinkedList<>();
    list2.addLast("One");
    list2.addLast("Two");
    list2.addLast("Three");
    System.out.println(list2.get(1));
    list2.remove(1);
    System.out.println(list2.get(1));
    list2.clear();
    System.out.println(
        "Print the size of the MyLinkedList after deleting all elements - " + list2.size());

    System.out.println("\n\nExample of the HashMap\n");
    WithoutImplHashMap<Integer, String> hashMap = new WithoutImplHashMap<Integer, String>();
    hashMap.putElement(1, "Солнце");
    hashMap.putElement(2, "Венера");
    hashMap.putElement(2, "Меркурий");
    hashMap.putElement(3, "Земля");
    hashMap.putElement(4, "Марс");
    hashMap.printHashMap();
    System.out.println("\nУдалим элемент с индексом 2.\n");
    hashMap.deleteElement(2);
    hashMap.printHashMap();

    System.out.println("\n\nExample of the Queue that is built on the base of the array\n");
    QueueOnArray<Integer> arrayQueue = new QueueOnArray<Integer>(25);
    arrayQueue.enQueue(1);
    arrayQueue.enQueue(2);
    arrayQueue.enQueue(10);
    System.out.println("Количество элементов в массиве - " + arrayQueue.count());
    System.out.println("Забираем тот, который пришел первым: " + arrayQueue.deQueue());
    System.out.println("Забираем тот, который пришел вторым: " + arrayQueue.deQueue());
    System.out.println("Забираем тот, который пришел третьим: " + arrayQueue.deQueue());
    //System.out.println("Ой, элементы закончились:" + arrayQueue.deQueue());
    QueueOnArray<String> arrayQueue1 = new QueueOnArray<String>(20);
    arrayQueue1.enQueue("One");
    arrayQueue1.enQueue("Two");
    arrayQueue1.enQueue("la-la-la");
    System.out.println("Количество элементов в массиве - " + arrayQueue1.count());
    System.out.println("Забираем тот, который пришел первым: " + arrayQueue1.deQueue());
    System.out.println("Забираем тот, который пришел вторым: " + arrayQueue1.deQueue());
    System.out.println("Забираем тот, который пришел третьим: " + arrayQueue1.deQueue());

    System.out.println("\n\nExample of the Queue that is built on the base of the list\n");
    QueueOnList<Integer> queue = new QueueOnList<>();
    for (int i = 0; i < 10; i++) {
      queue.enQueue(i);
    }
    while (queue.size > 0) {
      int el = queue.deQueue();
      System.out.println("Элемент = " + el);
      System.out.println("Размер очереди = " + queue.size);
    }

    System.out.println("\n\nExample of the Stack that is built on the base of the array\n");
    StackOnArray<Integer> stack = new StackOnArray<>(12);
    System.out.println("Добавляем элементы в стек...\n");
    stack.addElementInStack(1);
    System.out.println(stack.readTopElement() + "\n");
    stack.addElementInStack(4);
    System.out.println(stack.readTopElement() + "\n");
    stack.addElementInStack(7);
    System.out.println(stack.readTopElement() + "\n");
    stack.addElementInStack(95);
    System.out.println(stack.readTopElement() + "\n");
    System.out.println("Достаем из стека элементы: ");
    while (!stack.isEmpty() && stack.top >= 0) {
      int element = stack.getElementInStack();
      System.out.println(element + "\n");
    }
  }
}
