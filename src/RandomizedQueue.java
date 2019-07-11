import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> head;
    private Node<Item> tail;
    private int length = 0;


    public Deque() {                         // construct an empty deque
        head = null;
        tail = null;
    }

    public boolean isEmpty() {                 // is the deque empty?
        return length == 0;
    }

    public int size() {                        // return the number of items on the deque
        return length;
    }

    public void addFirst(Item item) {          // add the item to the front
        Node<Item> node = new Node<>(item, null);
        if (head == null || head.next == null) {
            head = new Node<>(null, node);
        } else {
            node.next = head.next;
            head.next = node;
        }

        if (tail == null || tail.next == null) {
            tail = new Node<>(null, node);
        }
    }

    public void addLast(Item item) {           // add the item to the end
        Node<Item> node = new Node<>(item, null);
        if (tail == null || tail.next == null) {
            tail = new Node<>(null, node);
        } else {
            tail.next.next = node;
            tail.next = node;
        }

        if (head == null || head.next == null) {
            head.next = node;
        }
    }

    public Item removeFirst() {                // remove and return the item from the front
        

    }

    public Item removeLast() {                 // remove and return the item from the end

    }

    public Iterator<Item> iterator() {         // return an iterator over items in order from front to end

    }

    public static void main(String[] args) {   // unit testing (optional)

    }
}

class Node<Item> {
    private Item value;
    private Node<Item> next;

    //Constructor has no return type
    public Node(Item value, Node<Item> node) {
        this.value = value;
        this.next = node;
    }
}

