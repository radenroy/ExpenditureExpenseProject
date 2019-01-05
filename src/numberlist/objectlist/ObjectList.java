package numberlist.objectlist;

import numberlist.InvalidIndexException;

/**
 * This is an abstract class. It defines the structure a class needs to have in
 * order to be a list that can carry objects.
 *
 * @author Verena Girgis
 * @author Raden Roy Pradana
 * @author Seunghyeon Hwang
 *
 * @version 3/11/2018
 */
abstract class ObjectList {

    //fields
    int count;

    /**
     * This abstract method adds an object at a specified index.
     *
     * @param index the index you wish to insert at.
     * @param obj the object you wish to insert.
     * @throws numberlist.objectlist.UncopiableException if the object is not copiable
     * @throws numberlist.InvalidIndexException if there is an invalid index in a home-grown array
     */
    abstract void add(int index, Object obj) throws InvalidIndexException, UncopiableException;

    /**
     * This abstract method removes the object at the specified index.
     *
     * @param index remove at a specified index.
     * @throws numberlist.InvalidIndexException if there is an invalid index in a home-grown array
     */
    abstract void removeAt(int index) throws InvalidIndexException;

    /**
     * This abstract method finds the first index of the specified object and
     * removes it.
     *
     * @param obj remove the first instance of this object
     * @throws numberlist.InvalidIndexException if there is an invalid index in a home-grown array
     */
    abstract void remove(Object obj) throws InvalidIndexException;

    /**
     * This abstract method returns the object at the specified index.
     *
     * @param index where the object is.
     * @throws numberlist.InvalidIndexException if there is an invalid index in a home-grown array
     * @return the object at the index.
     */
    abstract Object get(int index) throws InvalidIndexException;

    /**
     * This abstract method finds the first occurrence of the object and returns
     * the index.
     *
     * @param obj the object you are looking for.
     * @return the index found at or indicate that it was not found.
     */
    abstract int find(Object obj);

    /**
     * This abstract method gives the size of the list specified in the concrete
     * class.
     *
     * @return the size of the list.
     */
    public int size() {
        return count;
    }
}
