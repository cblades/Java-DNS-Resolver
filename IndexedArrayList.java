import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * Implementation of a list that keeps track of a position within
 * itself  underlying ArrayList and keeping track of a position 
 * within that list.
 *
 * @author Chris Blades
 * @version 13/3/2010
 */
public class IndexedArrayList<E> {
   /** Underlying List */
   private List<E> list;

   /** used to keep track of our position within the list */
   private int index;
    

    /**
     * Create a new empty IndexedArrayList with position = 0.
     *
     * /
   public IndexedArrayList() {
       index = 0;
       list  = new ArrayList<E>(0);
   }
   
   /**
    * Creates a new IndexedArrayList with the given List and sets
    * position = 0.
    *
    * @param toAdd the list to add to this list
    */ 
   public IndexedArrayList(E[] toAdd) {
       list = new ArrayList<E>(toAdd.length);
       for (int i = 0; i < toAdd.length; i++) {
            list.add(toAdd[i]);
       }
       index = 0;
   }

   /**
    * Returns the current position within the list
    *
    * @return the position within the list
    */
    public int getPosition() {
        return this.index;
    }

    /**
     * Sets the position within thie list.
     *
     * @param index new position within the list
     */
    public void setPosition(int index) {
        this.index = index;
    }

    /**
     * Gets the element at the current position in the list and increments
     * the position or wraps back to the beginning of the list if position
     * is greater than the length of the list.
     *
     * @return the next element in the list
     */
    public E getNext() {
        E next = list.get(index);
        index = (index + 1) % list.size();
        return next;
    }

    /**
     * Reverses the position within the list by 1.
     */
    public void rewind() {
        index--;
    }

//---------List----------
   /**
    * Adds an element to the list at the specified index
    *
    * @param index where to add the element
    * @param element the element to add to the list
    */
   public void add(int index, E element) {
       list.add(index, element);
   }

    /**
     * Adds a new element to the end of the list.
     *
     * @param element the object to add to the list
     */
   public boolean add(E o) {
       return list.add(o);
   }

    /**
     * copies the elements of the given collection onto the end of this list.
     *
     * @param c collection of elements to add
     */
   public boolean addAll(Collection<E> c) {
       return list.addAll(c);
   }

    /**
     * Deletes all elements from the list and resets the position to 0
     */
   public void clear() {
       list.clear();
        this.index = 0;
   }

   /**
    * Returns wether or not this list is empty.
    *
    * @return true if this list is empty
    */
   public boolean isEmpty() {
       return list.isEmpty();
   }

    /**
     * Removes the element at the specified position in the list
     *
     * @param index the index of the element to remove
     */
   public Object remove(int index) {
       return list.remove(index);
   }

    /**
     * Returns the number of elements in the list
     *
     * @param the number of elements in the list
     */
    public int size() {
        return list.size();
    }
}
