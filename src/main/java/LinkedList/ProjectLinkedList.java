package LinkedList;

public class ProjectLinkedList<T> {
    private class  Node {
        T data;
        Node next;
        Node(T data) {
            this.data = data;
        }
    }

    private Node head;
    private int size;

    public void add(T data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        }
        else {
            Node current = head;
            while (current.next != null)
                current = current.next;
            current.next = newNode;
        }
        size++;
    }

    public void clear(){
        head = null;
        size = 0;
    }

    public boolean remove(T data) {
        if (head == null) {
            return false;
        }
        if (head.data.equals(data)) {
            head = head.next;
            size--;
            return true;
        }
        Node previous = head;
        Node current = head.next;

        while (current != null) {
            if (current.data.equals(data)) {
                previous.next = current.next;
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    public int  size() {
        return size;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
        Node current = head;
        for (int i = 0; i < index; i++)
            current = current.next;

        return current.data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        Node current = head;
        while (current != null) {
            sb.append(current.data);
            if(current.next != null)
                sb.append(",");
            current = current.next;
        }
        sb.append(")");
        return sb.toString();
    }
}
