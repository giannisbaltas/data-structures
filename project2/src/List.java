
/**
 * Single-link List. Uses {@link Node} for list nodes.
 */
public class List implements ListInterface {

    private Node head = null;
	private Node tail = null;

    /**
     * Default constructor
     */
    public List() {
    }

    /**
     * Determine whether list is empty
     *
     * @return true if list is empty
     */
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Inserts the data at the front of the list
     *
     * @param data the inserted data
     */
    
    public void insertAtFront(int data) {
        Node n = new Node(data);

        if (isEmpty()) {
            head = n;
            tail = n;
        } else {
            n.setNext(head);
            head = n;
        }
    }
	
 
    @Override
    public String toString() {
        if (isEmpty()) {
            return "List is empty :(";
        }

        Node current = head;

        StringBuilder ret = new StringBuilder();

        // while not at end of list, output current node's data
        

        while (current != null) {
            ret.append(current.data);

            if (current.getNext() != null)
                ret.append(" ");

            current = current.next;
        }

        ret.append(" ");

        return ret.toString();
    }
}
