package numberlist.objectlist;

/**
 * The node class, implemented in linked lists to circulate through a linked
 * list.
 *
 * @author Verena Girgis
 * @author Raden Roy Pradana
 * @author Seunghyeon Hwang
 * @version 3/11/2018
 */
public class Node {

    private Node nextNode;
    private Object obj;

    /**
     * Constructor to make a new instance of a Node. Note that this class is
     * used as a part of its own self, meaning that it will call itself within
     * the class.
     *
     * @param obj the specified object from the Object class.
     */
    public Node(Object obj) {
        this.obj = obj;
        nextNode = null;
    }

    /**
     * This method gives the current value of the specified node.
     *
     * @return the value of the specific node at the address.
     */
    public Object getValue() {
        return obj;
    }

    /**
     * This method gives the ability to set the value of the current node.
     *
     * @param obj specifies the value at the current node.
     */
    public void setValue(Object obj) {
        this.obj = obj;
    }

    /**
     * This method gets the next node.
     *
     * @return the node in the next address in the heap.
     */
    public Node getNext() {
        return nextNode;
    }

    /**
     * This method sets the next node to a specified node.
     *
     * @param node the specific node you wish to set a path to.
     */
    public void setNext(Node node) {
        this.nextNode = node;
    }
}
