import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> head;
    private Node<Item> tail;
    private int length = 0;


    public Deque() {                         // construct an empty deque
        this.head = null;
        this.tail = null;
    }

    public boolean isEmpty() {                 // is the deque empty?
        return length == 0;
    }

    public int size() {                        // return the number of items on the deque
        return length;
    }

    public void addFirst(Item item) {          // add the item to the front
        if (item == null) {
            throw new IllegalArgumentException("Should not add null element");
        }
        Node<Item> node = new Node<>(item, null, head);
        if (head == null || head.getNext() == null) {
            head = new Node<>(null, node, null);
        } else {
            node.setNext(head.getNext());
            node.setPrev(head);
            head.setNext(node);
        }

        if (tail == null || tail.getPrev() == null) {
            tail = new Node<>(null, null, node);
        }
        length++;
    }

    public void addLast(Item item) {           // add the item to the end
        if (item == null) {
            throw new IllegalArgumentException("Should not add null element");
        }
        Node<Item> node = new Node<>(item, tail, null);
        if (tail == null || tail.getPrev() == null) {
            tail = new Node<>(null, null, node);
        } else {
            tail.getPrev().setNext(node);
            tail.setPrev(node);
        }

        if (head == null || head.getNext() == null) {
            head = new Node<>(null, node, null);
        }
        length++;
    }

    public Item removeFirst() {                // remove and return the item from the front
        Item item = null;
        if (length <= 0) {
            throw new NoSuchElementException("The Deque is empty");
        } else if (length == 1) {
            item = head.getNext().getValue();
            head.setNext(null);
            tail.setPrev(null);
        } else {
            item = head.getNext().getValue();
            head.setNext(head.getNext().getNext());
            head.getNext().setPrev(head);
        }
        length--;
        return item;
    }

    public Item removeLast() {                 // remove and return the item from the end
        Item item = null;
        if (length <= 0) {
            throw new NoSuchElementException("The Deque is empty");
        } else if (length == 1) {
            item = tail.getPrev().getValue();
            head.setNext(null);
            tail.setPrev(null);
        } else {
            item = tail.getPrev().getValue();
            tail.setPrev(tail.getPrev().getPrev());
            tail.getPrev().setNext(tail);
        }
        length--;
        return item;
    }

    public Iterator<Item> iterator() {         // return an iterator over items in order from front to end
        return new DequeIterator();
    }

    class DequeIterator implements Iterator<Item> {
        Node<Item> current = new Node<>();

        public DequeIterator() {
            this.current = head.getNext();
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item node = current.getValue();
            current = current.getNext();
            return node;
        }
    }

    public static void main(String[] args) {   // unit testing (optional)


    }
}

class Node<Item> {
    private Item value;
    private Node<Item> next;
    private Node<Item> prev;

    public Node() {
        this.value = null;
        this.next = null;
        this.prev = null;
    }

    //Constructor has no return type
    public Node(Item value, Node<Item> next, Node<Item> prev) {
        this.value = value;
        this.next = next;
        this.prev = prev;
    }

    public Item getValue() {
        return value;
    }

    public Node<Item> getNext() {
        return next;
    }

    public Node<Item> getPrev() {
        return prev;
    }

    public void setValue(Item value) {
        this.value = value;
    }

    public void setNext(Node<Item> next) {
        this.next = next;
    }

    public void setPrev(Node<Item> prev) {
        this.prev = prev;
    }
}

