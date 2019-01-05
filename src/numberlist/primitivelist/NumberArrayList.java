package numberlist.primitivelist;

import numberlist.InvalidIndexException;

/**
 * This program contains a partially-filled array of primitive long values to
 * implement a list structure. The NumberArrayList is able to grow twice its
 * size when elements are added.
 *
 * @author Verena Girgis
 * @author Raden Roy Pradana
 * @author Seunghyeon Hwang
 *
 * @version 3/11/2018
 */
class NumberArrayList {

    //fields
    private long[] list;
    private int count;

    /**
     * Default Constructor. Creates a new array called list of long values and
     * sets the initial count of the elements in the stack to zero.
     */
    public NumberArrayList() {
        list = new long[10];
        count = 0;
    }

    /**
     * This method adds to the list by parsing the Double value to long.
     *
     * @param index The index of which number should be added to in the internal
     * array.
     * @param value The value to add.
     * @throws numberlist.InvalidIndexException if there is an invalid index in a home-grown array
     */
    public void add(int index, long value) throws InvalidIndexException {
        if (index < 0 || index > count) {
            throw new InvalidIndexException(0, count, index);
        }

        if (count == list.length) {
            long[] newList = new long[list.length * 2];
            System.arraycopy(list, 0, newList, 0, list.length);
            list = newList;
        }

        for (int i = count - 1; i >= index; i--) {
            list[i + 1] = list[i];
        }

        list[index] = value;
        count++;
    }

    /**
     * Removing the item at the index given.
     *
     * @param index The index at which the number will be removed.
     * @throws numberlist.InvalidIndexException if there is an invalid index in a home-grown array
     */
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
     * Finds the index of the given value and removes it from the internal
     * array.
     *
     * @param value The value to be removed
     */
    public void remove(long value) {
        int index = -1;
        for (int i = 0; i < count; i++) {
            if (list[i] == value) {
                index = i;
                break;
            }
        }

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
    public long get(int index) throws InvalidIndexException {
        if (index < 0 || index >= count) {
            throw new InvalidIndexException(0, count - 1, index);
        }

        return list[index];
    }

    /**
     * finds the specified value using linear search
     *
     * @param value The value it must find
     * @return the index of the value given
     *
     */
    public int find(long value) {
        for (int i = 0; i < count; i++) {
            if (list[i] == value) {
                return i;
            }
        }

        return -1;
    }

    /**
     * gives the actual amount filled in the array.
     *
     * @return gives the size of the current array.
     */
    public int size() {
        return count;
    }

    /**
     *  Sets the value of the ArrayList at the specified index.
     * 
     * @param index the specific place you would like it inserted
     * @param newValue the value you would like inserted
     * @throws InvalidIndexException if there is an invalid index in a home-grown array
     */
    public void set(int index, long newValue) throws InvalidIndexException {
        if (index < 0 || index >= count) {
            throw new InvalidIndexException(0, count - 1, index);
        }

        list[index] = newValue;
    }

    /**
     * This method provides a string representation of the current list.
     *
     * @return the string form of the NumberArrayList.
     */
    @Override
    public String toString() {
        StringBuffer string = new StringBuffer();
        for (int i = 0; i < count; i++) {
            string.append(list[i] + " ");
        }
        return string.toString();
    }
}
