package numberlist.primitivelist;

import numberlist.InvalidIndexException;

/**
 * This class inherits from FloatingPointArrayList class. It contains methods
 * for adding a value to the end of a list, removing all specified values from
 * the list, or returning the index of the last element of a specified value
 * from the list.
 *
 * @author Verena Girgis
 * @author Raden Roy Pradana
 * @author Seunghyeon Hwang
 *
 * @version 3/11/2018
 */
public class RealArrayList extends FloatingPointArrayList {

    /**
     * This method adds to the end of the arrayList by parsing the Double value
     * to long.
     *
     * @param value The value to add.
     * @throws numberlist.InvalidIndexException if there is an invalid index in a home-grown array
     */
    public void add(double value) throws InvalidIndexException {
        this.add(size(), value);
    }

    /**
     * This method finds and removes every instance of an Integer from a list.
     *
     * @param value to remove from the whole list.
     * @throws numberlist.InvalidIndexException if there is an invalid index in a home-grown array
     */
    public void removeAll(double value) throws InvalidIndexException {
        //I need the loop to go over every single one, so if I shift one
        for (int i = size() - 1; i >= 0; i--) {
            if (this.get(i) == value) {
                removeAt(i);
            }
        }
    }

    /**
     * This method finds the last index of the specified value.
     *
     * @param value what index you want to find it at.
     * @return the index where this value is found last.
     */
    public int findLast(double value) {
        for (int i = size() - 1; i >= 0; i--) {
            try {
                if (this.get(i) == value) {
                    return i;
                }
            } catch (InvalidIndexException exp) {
                System.out.println("InvalidIndexException thrown in findLast of RealArrayList");
            }
        }
        return -1;
    }
}
