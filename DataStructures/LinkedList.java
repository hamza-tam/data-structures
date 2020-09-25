package DataStructures;

public class LinkedList<T> {
  private int size;

  private class Node {
    public T element;
    public Node next;
  }

  private Node head;

  public int size() {
    return size;
  }

  public LinkedList() {
    this.size = 0;
    this.head = null;
  }

  public boolean contains(T element) {
    Node current = head;
    while (current != null) {
      if (current.element.equals(element))
        return true;
      current = current.next;
    }
    return false;
  } 

  public int indexOf(T element) {
    Node current = head;
    int index = 0;
    while (current != null) {
      if (current.element.equals(element))
        return index;
      current = current.next;
      ++index;
    }
    return -1;
  }

  public int lastIndexOf(T element) {
    Node current = head;
    int index = 0;
    int found = -1;
    while (current != null) {
      if (current.element.equals(element))
        found = index;
      current = current.next;
      ++index;
    }
    return found;
  }

  public boolean add(int index, T element) {
    if (index >= this.size && index < 0) 
      return false;

    Node newNode = new Node();
    newNode.element = element;

    if (index == 0) {
      return addFirst(element);
    }
    
    Node parent = findParentNode(index);
    newNode.next = parent.next;
    parent.next = newNode;
    ++size;
    return true;
  }

  private Node findParentNode(int index) {
    Node parent = head;
    int _index = 0;
    while (index != _index + 1) {
      parent = parent.next;
      ++_index;
    }
    return parent;
  }

  public boolean addFirst(T element) {
    Node newHead = new Node();
    newHead.element = element;
    newHead.next = head;
    head = newHead;
    ++size;
    return true;
  }

  public boolean addLast(T element) {
    Node lastNode = this.getLastNode();
    lastNode.next = new Node();
    lastNode.next.element = element;
    lastNode.next.next = null;
    ++size;
    return true;
  }

  public T getFirst() {
    return head.element;
  }

  public T getLast() {
    return getLastNode().element;
  }

  private Node getLastNode() {
    Node current = head;
    if (current == null) 
      return null;

    while (current.next != null) 
      current = current.next;

    return current;
  }

  public boolean remove(int index) {
    if (index >= this.size && index < 0) 
      return false;

    if (index == 0) 
      return removeFirst();

    Node parent = findParentNode(index);
    parent.next = parent.next.next;
    --size;
    return true;
  }

  public boolean remove(T element) {
    // TODO Fix potential null pointer exception
    if (head == null)
      return false;

    if (head.element.equals(element)) {
      head = head.next;
      --size;
      return true;
    }

    Node parent = head;
    while (!parent.next.element.equals(element) && parent.next != null)
      parent = parent.next;

    if (parent.next == null)
      return false;

    parent.next = parent.next.next;
    --size;
    return true;
  }

  public boolean removeFirst() {
    if (head == null) 
      return false;
    head = head.next;
    --size;
    return true;
  }

  public boolean removeLast() {
    if (size == 0)
      return false;
    else if (size == 1) 
      return removeFirst();

    Node parent = findParentNode(size - 1); 
    parent.next = null;
    --size;
    return true;
  }

  public boolean set(int index, T element) {
    if (index >= this.size && size < 0)
      return false;
    if (index == 0)
      head.element = element;
    Node parent = findParentNode(index);
    parent.next.element = element; 
    return true;
  }
}
