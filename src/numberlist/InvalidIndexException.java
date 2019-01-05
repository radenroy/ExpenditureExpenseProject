package numberlist;

/**
 * This class represent a custom exception which made to handle index error.
 *
 * @author Raden Roy Pradana
 * @author Seunghyeon Hwang
 * @author Verena Girgis
 * @version 3/11/2018
 */
public class InvalidIndexException extends Exception {

    private int lowestIndex;
    private int highestIndex;
    private int index;

    /**
     * Constructor that accept lowest index, highest index allowed, and the
     * specific index of the List which you are trying to test and handle.
     *
     *
     *
     * @param lowestIndex the lowest index
     * @param highestIndex the highest index allowed
     * @param index the index received as a parameter
     */
    public InvalidIndexException(int lowestIndex, int highestIndex, int index) {
        super(index + " is not a valid index.");
        this.lowestIndex = lowestIndex;
        this.highestIndex = highestIndex;
        this.index = index;
    }

    /**
     * This is a getter method which gives the lowest index of the list we are
     * testing.
     *
     * @return the lowest index
     */
    public int getLowestIndex() {
        return lowestIndex;
    }

    /**
     * This is a getter method which gives the highest index of the list we are
     * testing.
     *
     * @return the highest index.
     */
    public int getHighestIndex() {
        return highestIndex;
    }

    /**
     * This is a getter method which gives the index which we want to handle.
     *
     * @return the index
     */
    public int getIndex() {
        return index;
    }

}
