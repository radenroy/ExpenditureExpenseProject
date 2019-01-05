package numberlist.objectlist;

import numberlist.InvalidIndexException;

/**
 * This class inherits from ObjectList, an abstract class, and implements its
 * abstract methods. Please note that the size has moved to the Parent class in
 * order to be accessible to the ObjectArrayList as well.
 *
 * @author Verena Girgis
 * @author Raden Roy Pradana
 * @author Seunghyeon Hwang
 *
 * @version 3/11/2018
 */
public class ObjectLinkedList extends ObjectList implements Copiable {

    private Node firstNode;

    /**
     * Makes an instance of an object linked list. Please note that the
     * instantiation of firstNode is not necessary, as all class instance
     * variables are already instantiated.
     */
    public ObjectLinkedList() {
        firstNode = null;
    }

    /**
     * This method adds an object at a specified index.
     *
     * @param index the index you wish to insert at.
     * @param obj the object you wish to insert.
     * @throws numberlist.InvalidIndexException if there is an invalid index in a home-grown array
     * @throws numberlist.objectlist.UncopiableException if the object is copiable
     */
    @Override
    public void add(int index, Object obj) throws InvalidIndexException, UncopiableException {
        if (index > count || index < 0) {
            throw new InvalidIndexException(0, count, index);
        }
        if (!(obj instanceof Copiable)) {
            throw new UncopiableException(obj);
        }
        //take care of special case first
        Node newNode = new Node(obj);
        if (index == 0) {
            newNode.setNext(firstNode);
            firstNode = newNode;
        } else {
            Node currentNode = firstNode;
            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.getNext();
            }
            newNode.setNext(currentNode.getNext());
            currentNode.setNext(newNode);
        }
        count++;
    }

    /**
     * This method removes the entire node at the specified index.
     *
     * @param index remove at a specified index.
     * @throws numberlist.InvalidIndexException if there is an invalid index in a home-grown array
     */
    @Override
    public void removeAt(int index) throws InvalidIndexException {
        if (index >= count || index < 0) {
            throw new InvalidIndexException(0, count, index);
        }
        if (index == 0) {
            firstNode = firstNode.getNext();
        } else {
            Node previous = firstNode;
            for (int i = 0; i < index - 1; i++) {
                previous = previous.getNext();
            }
            previous.setNext(previous.getNext().getNext());
        }
        count--;
    }

    /**
     * This method finds the first index of the specified object and removes the
     * node. This node is disposed of by the garbage collector.
     *
     * @param obj remove the first instance of this object.
     */
    @Override
    public void remove(Object obj) {
        if (count == 0 || obj == null) {
            return;
        }
        if (obj.equals(firstNode.getValue())) {
            firstNode = firstNode.getNext();
            count--;
            return;
        }
        Node currentNode = firstNode.getNext();
        Node beforeNode = firstNode;
        while (currentNode != null) {
            if (obj.equals(currentNode.getValue())) {
                beforeNode.setNext(currentNode.getNext());
                count--;
                return;
            }
            beforeNode = currentNode;
            currentNode = currentNode.getNext();
        }
    }

    /**
     * This method returns the object at the specified index.
     *
     * @param index where the object is located.
     * @return the object at the index
     * @throws numberlist.InvalidIndexException if there is an invalid index in a home-grown array
     */
    @Override
    public Object get(int index) throws InvalidIndexException {
        if (index >= count) {
            throw new InvalidIndexException(0, count, index);
        }
        Node currentNode = firstNode;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }
        return currentNode.getValue();
    }

    /**
     * This method finds the first occurrence of the object and returns the
     * index.
     *
     * @param obj the object you are looking for.
     * @return the index found at or -1, indicating that it was not found.
     */
    @Override
    public int find(Object obj) {
        //if there is nothing in the list 
        if (count == 0) {
            return -1;
        }
        Node currentNode = firstNode;
        for (int i = 0; i < count; i++) {
            if (currentNode.getValue().toString().equals(obj.toString())) {
                return i;
            }
            currentNode = currentNode.getNext();
        }
        return -1;
    }

    /**
     * This method gives you the string of the current list.
     *
     * @return the string version of the list.
     */
    @Override
    public String toString() {
        if (count == 0) {
            return "";
        }

        StringBuffer string = new StringBuffer();

        Node currentNode = firstNode;
        for (int i = 0; i < count; i++) {
            string.append(currentNode.getValue() + " ");

            currentNode = currentNode.getNext();
        }
        return string.toString();
    }

    /**
     * Copies each element in the list.
     *
     * @return the new linked list to be used for the Copiable interface
     */
    @Override
    public Copiable deepCopy() {
        for (int i = count - 1; i >= 0; i++) {
            //only add at 0   
        }
        ObjectLinkedList newList = new ObjectLinkedList();

        try {
            for (int i = 0; i < count; i++) {
                Object value = ((Copiable) this.get(i)).deepCopy();

                newList.add(i, value);
            }
        } catch (InvalidIndexException exp) {
            System.out.println("InvalidIndexException thrown in deepCopy ObjectLinkedList");
        } catch (UncopiableException exp) {
            System.out.println("UncopiableException thrown in deepCopy ObjectLinkedList");
        }
        return newList;
    }
}
