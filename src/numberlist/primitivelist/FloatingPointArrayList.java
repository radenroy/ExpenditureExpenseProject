package numberlist.primitivelist;

import java.util.logging.Level;
import java.util.logging.Logger;
import numberlist.InvalidIndexException;

/**
 * This class uses an internal NumberArrayList to make floating point numbers.
 *
 * @author Verena Girgis
 * @author Raden Roy Pradana
 * @author Seunghyeon Hwang
 *
 * @version 3/11/2018
 */
class FloatingPointArrayList {

    private final NumberArrayList list;

    /**
     * Default constructor. Note: Since Doubles and Longs both take 64 bits in
     * memory, this conversion is possible.
     */
    public FloatingPointArrayList() {
        list = new NumberArrayList();
    }

    /**
     * This method adds to the list by parsing the Double value to long.
     *
     * @param index the index of the number to be added to in the internal
     * array.
     * @param value the value to add.
     * @throws numberlist.InvalidIndexException if there is an invalid index in a home-grown array
     */
    public void add(int index, double value) throws InvalidIndexException {
        list.add(index, Double.doubleToRawLongBits(value));
    }

    /**
     * This method removes the item at the index given.
     *
     * @param index the index at which the number will be removed.
     * @throws numberlist.InvalidIndexException if there is an invalid index in a home-grown array
     */
    public void removeAt(int index) throws InvalidIndexException {
        list.removeAt(index);
    }

    /**
     * Finds the index of the given value and removes it from the internal
     * array.
     *
     * @param value The value to be removed
     */
    public void remove(double value) {
        list.remove(Double.doubleToRawLongBits(value));
    }

    /**
     * This method gives the value at the index of the internal array.
     *
     * @param index the index of the value.
     * @return the value at the index as a double.
     * @throws numberlist.InvalidIndexException if there is an invalid index in a home-grown array
     */
    public double get(int index) throws InvalidIndexException {
        return Double.longBitsToDouble(list.get(index));
    }

    /**
     * This method finds the specified value using linear search.
     *
     * @param value the value it must find.
     * @return the index of the value given.
     *
     */
    public int find(double value) {
        return list.find(Double.doubleToRawLongBits(value));
    }

    /**
     * This method gives the actual amount filled in the array.
     *
     * @return gives the size of the current array.
     */
    public int size() {
        return list.size();
    }

    /**
     * This method provides a string representation of the current list. A
     * complete re-implementation is needed to make up for the Double to Long
     * conversion. This method calls the get for FloatingPointArrayList,
     * allowing the conversion from long to double.
     *
     * @return the string form of the FloatingPointArrayList.
     */
    @Override
    public String toString() {
        StringBuffer string = new StringBuffer();
        for (int i = 0; i < size(); i++) {
            try {
                string.append(Double.longBitsToDouble(list.get(i)) + " ");
            } catch (InvalidIndexException ex) {

            }
        }
        return string.toString();
    }
}
