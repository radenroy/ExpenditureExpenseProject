package numberlist.objectlist;

import java.io.Serializable;
import numberlist.InvalidIndexException;

/**
 * This class contains a partially-filled array of Object type used to implement
 * an array list structure. The ObjectArrayList is able to grow twice its size
 * when elements are added.
 *
 * This class inherits from ObjectList, an abstract class, and implements its
 * abstract methods. Please note that the size has moved to the Parent class in
 * order to be accessible to the ObjectLinkedList as well.
 *
 * @author Verena Girgis
 * @author Raden Roy Pradana
 * @author Seunghyeon Hwang
 *
 * @version 3/11/2018
 */
public class ObjectArrayList extends ObjectList implements Copiable, Serializable {

    //fields
    private Object[] list;

    /**
     * Default Constructor. Creates a new array of Objects called list and sets
     * the initial count of the elements in the stack to zero.
     */
    public ObjectArrayList() {
        list = new Object[10];
        count = 0;
    }

    /**
     * This method adds an object to the array of Objects at a specified index.
     * If the index does not fall within the count (size), then ignore. If the
     * array size is equal or greater than the length, double the array size. If
     * the array is not too small, start with the highest element and move every
     * element up by 1.
     *
     * @param index where the value will be placed.
     * @param object The value to added to the array.
     * @throws numberlist.InvalidIndexException if there is an invalid index in a home-grown array
     * @throws numberlist.objectlist.UncopiableException if the object is not copiable
     */
    @Override
    public void add(int index, Object object) throws InvalidIndexException, UncopiableException {
        if (index < 0 || index > count) {
            throw new InvalidIndexException(0, count, index);
        }

        if (!(object instanceof Copiable)) {
            throw new UncopiableException(object);
        }

        if (count >= list.length) {
            //the array is too small so double it
            Object[] newList = new Object[list.length * 2];
            System.arraycopy(list, 0, newList, 0, list.length);
            list = newList;
        }

        //the array is not too small
        for (int i = count - 1; i >= index; i--) {
            list[i + 1] = list[i];
        }
        list[index] = object;
        count++;
    }

    /**
     * This method removes the item at the index given by starting at index then
     * moving the one above down into that place. If the index doesn't fall
     * within the count (size), the request is ignored.
     *
     * @param index The index at which the number will be removed.
     */
    @Override
    public void removeAt(int index) throws InvalidIndexException {
        if (index < 0 || index >= count) {
            throw new InvalidIndexException(0, count - 1, index);
        }

        for (int i = index; i < list.length - 1; i++) {
            list[i] = list[i + 1];
        }

        count--;
    }

    /**
     * This method removes a specified obj (value) from the array. If the value
     * is not in the list, the method does nothing. If the value is in the list,
     * it removes the first instance of that value.
     *
     * @param object the value to be removed.
     */
    @Override
    public void remove(Object object) {
        int index = -1;
        for (int i = 0; i < count; i++) {
            if (list[i] == object) {
                index = i;
                break;
            }
        }

        //should we be doing this?
        if (index < 0) {
            return;
        }

        for (int i = index; i < count - 1; i++) {
            list[i] = list[i + 1];
        }
        count--;

    }

    /**
     * gives the value at the index of the internal array.
     *
     * @param index the index of the value
     * @return the value at the index
     * @throws numberlist.InvalidIndexException if there is an invalid index in a home-grown array
     */
    @Override
    public Object get(int index) throws InvalidIndexException {
        if (index < 0 || index >= count) {
            throw new InvalidIndexException(0, count - 1, index);
        }
        return list[index];
    }

    /**
     * This method finds the specified value in the array using linear search.
     *
     * @param object The value it must find
     * @return the index of the first occurrence of the value given or -1 if not
     * found.
     */
    @Override
    public int find(Object object) {

        for (int i = 0; i < count; i++) {
            if (list[i].toString().equals(object.toString())) {
                return i;

            }
        }
        return -1;
    }

    /**
     * This method provides a string representation of the current list.
     *
     * @return the string form of the complex number.
     */
    @Override
    public String toString() {
        StringBuffer string = new StringBuffer();
        for (int i = 0; i < count; i++) {
            string.append(list[i] + " ");
        }
        return string.toString();
    }

    /**
     * Sets the object to the specified index of the ArrayList .
     * 
     * @param index the specific index
     * @param obj the object to be put at the specific index
     * @throws InvalidIndexException if there is an invalid index in a home-grown array
     */
    public void set(int index, Object obj) throws InvalidIndexException {
        if (index < 0 || index >= count) {
            throw new InvalidIndexException(0, count - 1, index);
        }

        list[index] = obj;
    }

    /**
     * Copies each element in the array list.
     *
     * @return the new List to be used for the Copiable interface.
     */
    @Override
    public Copiable deepCopy() {
        ObjectArrayList newList = new ObjectArrayList();
        for (int i = 0; i < count; i++) {
            try {
                newList.add(i, ((Copiable) list[i]).deepCopy());
            } catch (InvalidIndexException exp) {
                System.out.println("InvalidIndexException thrown in deepCopy ObjectArrayList");
            } catch (UncopiableException exp) {
                System.out.println("UncopiableException thrown in deepCopy ObjectArrayList");
            }
        }
        return newList;
    }
}
