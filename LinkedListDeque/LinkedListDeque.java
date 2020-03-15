/** LinkedListDeque is a linked-list-based deque.
 *  Deque is an irregular acronym of double-ended queue.
 *  Double-ended queues are sequence containers with dynamic sizes that can be expanded or contracted on both ends.
 */

public class LinkedListDeque<T> {
    public class Node {
        public Node prev;
        public T item;
        public Node next;

        public Node(Node prev, T item, Node next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    private Node sentinel;
    private int size = 0;

    /** Constructs an empty LinkedListDeque such that it is circular, with the front and back pointers sharing the same
     * sentinel node
     */
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    /** Creates a deep copy of other */
    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;

        for (int i = 0; i < other.size(); i+=1) {
            addLast((T) other.get(i));
        }
    }

    /**  Adds an item of type T to the front of the deque */
    public void addFirst(T item) {
        sentinel.next = new Node(sentinel, item, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size++;
    }

    /** Adds an item of type T to the back of the deque.*/
    public void addLast(T item) {
        sentinel.prev.next = new Node(sentinel.prev, item, sentinel);
        sentinel.prev = sentinel.prev.next;
        size++;
    }

    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return (size == 0);
    }

    /** Prints the items in the deque from first to last, separated by a space. */
    public void printDeque() {
        Node n = sentinel;

        while (n.next != sentinel) {
            n = n.next;
            System.out.print(n.item + " ");
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque. If no such item exists, returns null. */
    public T removeFirst() {
        T first = sentinel.next.item;

        if (size > 0) {
            sentinel.next.next.prev = sentinel;
            sentinel.next = sentinel.next.next;

            size--;
        }

        return first;
    }

    /**  Removes and returns the item at the back of the deque. If no such item exists, returns null. */
    public T removeLast() {
        T last = sentinel.prev.item;

        if (size > 0) {
            sentinel.prev.prev.next = sentinel;
            sentinel.prev = sentinel.prev.prev;
            size--;
        }

        return last;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. */
    public T get(int index) {
        Node n = sentinel;

        if (index >  size - 1 || index < 0) {
            return null;
        }

        while (index >= 0) {
            n = n.next;
            index--;
        }
        return n.item;
    }

    /** Recursive version of get() */
    public T getRecursive(int index) {
        if (index >  size - 1 || index < 0) {
            return null;
        }
        return helpGetReverse(sentinel.next, index);
    }

    /** Helper method of helpGetRecursive */
    public T helpGetReverse(Node n, int i) {
        if (i == 0) {
            return n.item;
        } else {
            return helpGetReverse(n.next, i - 1);
        }
    }
}
