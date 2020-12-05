public interface ListInterface<T> {
    /**
     * Inserts the data at the front of the list
     *
     * @param data the inserted data
     */
    void insertAtFront(T data);

    /**
     * Determine whether list is empty
     *
     * @return true if list is empty
     */
    boolean isEmpty();
}
