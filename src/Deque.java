import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> head;
    private Node<Item> tail;
    private int length = 0;


    public Deque() {                         // construct an empty deque
        this.head = new Node<>();
        this.tail = new Node<>();
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

        //Create the node to be inserted
        Node<Item> node = new Node<>(item, null, null);
        if (length == 0) {
            node.setPrev(head);
            node.setNext(tail);
            head.setNext(node);
            tail.setPrev(node);
        } else {
            head.getNext().setPrev(node);
            node.setPrev(head);
            node.setNext(head.getNext());
            head.setNext(node);
        }
        length++;
    }

    public void addLast(Item item) {           // add the item to the end
        if (item == null) {
            throw new IllegalArgumentException("Should not add null element");
        }
        Node<Item> node = new Node<>(item, null, null);
        if (length == 0) {
            node.setPrev(head);
            node.setNext(tail);
            head.setNext(node);
            tail.setPrev(node);
        } else {
            node.setPrev(tail.getPrev());
            node.setNext(tail);
            tail.getPrev().setNext(node);
            tail.setPrev(node);
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
        Node<Item> current;

        public DequeIterator() {
            this.current = head.getNext();
        }

        @Override
        public boolean hasNext() {
            return current != null && current.getValue() != null;
        }

        @Override
        public Item next() {
            Item value = current.getValue();
            current = current.getNext();
            return value;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {   // unit testing (optional)
        Deque<String> deque = new Deque<>();
        System.out.println(deque.isEmpty());
        deque.addFirst("test_1");
        deque.addFirst("test_2");
        deque.addFirst("test_3");
        deque.addLast("test_4");
        deque.addLast("test_5");
        deque.addLast("test_6");
        System.out.println(deque.size());
        Iterator<String> dIterator = deque.iterator();
        while (dIterator.hasNext()) {
            System.out.println(dIterator.next());
        }

        deque.removeFirst();
        deque.removeFirst();
        deque.removeLast();
        deque.removeLast();
        System.out.println(deque.size());
        dIterator = deque.iterator();
        while (dIterator.hasNext()) {
            System.out.println(dIterator.next());
        }
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

