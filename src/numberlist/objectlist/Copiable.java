package numberlist.objectlist;

import numberlist.InvalidIndexException;

/**
 * The interface to describe the behavior of being copiable as a deepCopy for an
 * object.
 *
 * @author Verena Girgis
 * @author Raden Roy Pradana
 * @author Seunghyeon Hwang
 * @version 3/11/2018
 */
public interface Copiable {

    /**
     * On implementation, will make a deep copy. This includes making a new
     * address to whatever object is being passed, including th parameters
     * within the object itself.
     *
     * @return a deep copy of the implemented object
     */
    Copiable deepCopy();
}
