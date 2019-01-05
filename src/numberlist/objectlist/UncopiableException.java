package numberlist.objectlist;

/**
 * This is a custom exception class which handle the un-copiable object.
 *
 * @author Verena Girgis
 * @author Raden Roy Pradana
 * @author Seunghyeon Hwang
 * @version 3/11/2018
 */
public class UncopiableException extends Exception {

    public UncopiableException(Object object) {
        super(object + " is not copiable.");
    }
}
