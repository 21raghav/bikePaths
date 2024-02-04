


public class DLStack<T> implements DLStackADT<T> {
    private DoubleLinkedNode<T> top;// Reference to the top node of the stack
    private int numItems; // The number of items in the stack

    public DLStack() {
        top = null;
        numItems = 0;
    }
    
    public void push(T dataItem) {
        DoubleLinkedNode<T> newNode = new DoubleLinkedNode<>(dataItem);
        if (isEmpty()) {
            top = newNode;
        } else {
            newNode.setNext(top);
            top.setPrevious(newNode);
            top = newNode;
        }
        numItems++;
    }
   // Pops the item at the k-th position from the top of the stack and returns it
    public T pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException("The stack is empty.");
        }

        T data = top.getElement();
        if (top.getNext() != null) {
            top.getNext().setPrevious(null);
        }
        top = top.getNext();
        numItems--;
        return data;
    }
    
    public T peek() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException("The stack is empty.");
        }

        return top.getElement();
    }

    public T pop(int k) throws InvalidItemException {
        if (k <= 0 || k > numItems) {
            throw new InvalidItemException("Invalid position: " + k);
        }

        DoubleLinkedNode<T> current = top;
        for (int i = 0; i < k - 1; i++) {
            current = current.getNext();
        }
        T data = current.getElement();
        DoubleLinkedNode<T> prevNode = current.getPrevious();
        DoubleLinkedNode<T> nextNode = current.getNext();

        if (prevNode != null) {
            prevNode.setNext(nextNode);
        } else {
            top = nextNode;
        }

        if (nextNode != null) {
            nextNode.setPrevious(prevNode);
        }

        numItems--;
        return data;
    }
    //Checks if the stack is empty

    public boolean isEmpty() {
        return numItems == 0;
    }
//Returns the number of items in the stack
    public int size() {
        return numItems;
    }
//Returns the top node of the stack
    public DoubleLinkedNode<T> getTop() {
        return top;
    }
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        DoubleLinkedNode<T> current = top;

        while (current != null) {
            sb.append(current.getElement());
            current = current.getNext();
            if (current != null) {
                sb.append(" ");
            }
        }

        sb.append("]");
        return sb.toString();
    }

    


}