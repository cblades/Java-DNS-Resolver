/**
 * Extends the functionality of the java.util.List interface to
 * provide for tracking a postition within the list.
 *
 * @author Chris Blades
 * @version 13/3/2010
 */
public interface IndexedList<E> {
    /**
     * Returns the current position inside this list.
     *
     * @return the current position inside this list.
     */
    public int getPosition();

    /**
     * Sets the position within the list
     *
     * @param index the new position of this list
     */
    public void setPosition(int index);

    /**
     * Returns the element at the current position
     * in the list.
     *
     * @return the element at the current position in the list.
     */
    public E getNext();
}
