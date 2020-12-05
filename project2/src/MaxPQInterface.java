/**
 * Priority Queue interface
 *
 */
public interface MaxPQInterface {

    /**
     * Inserts the specified element into this priority queue.
     *
     * @param item
     */
    void insert(Object item);


    /**
     * Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
     *
     * @return the head of the queue
     */
    Object peek();


    /**
     * Retrieves and removes the head of this queue, or returns null if this queue is empty.
     *
     * @return the head of the queue
     */
    Object getMax();
	
	int getSize();
}