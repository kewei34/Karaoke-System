package adt;

import java.util.Iterator;

public class Doubly_LinkedList<T extends Comparable<T>> implements ListInterface<T> {

    private Node firstNode = null;
    private int numberOfEntries = 0;

    @Override
    public int getNumberOfEntries() {
        return numberOfEntries;
    }

    @Override
    public boolean add(T newEntry) {
        Node newNode = new Node(newEntry);

        if (isEmpty()) {//to check the list whether is empty
            firstNode = newNode;
            numberOfEntries++;
        } else {
            Node currentNode = firstNode; 
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }                                   //connect the lastnode and the newnode together after found the last node
            currentNode.next = newNode;
            newNode.previous = currentNode;
            numberOfEntries++;
        }
        return true;

    }

    @Override
    public boolean remove(T entry) {
        Node chosenNode = new Node(entry);
        int counter = 0;
        if (!search(entry) || isEmpty()) {
            return false;   //check if the entry exists in the list and if it is empty
        } else {
            Node currentNode = firstNode;
            do {
                if (currentNode.data.compareTo(chosenNode.data) == 0) {  
                    if (counter == 0 && currentNode.next != null) {  //condition: if the entry is the first node and has nextnode
                        currentNode.next.previous = null;            
                        firstNode = currentNode.next;
                        currentNode.next = null;
                        numberOfEntries--;
                        return true;
                    } else if (counter == 0 && currentNode.next == null) { //condition: if the entry is the first node and does not has nextnode
                        firstNode = null;
                        numberOfEntries--;
                        return true;
                    } else if (currentNode.next == null) { //condition: if the entry is last node
                        currentNode.previous.next = null;
                        currentNode = null;
                        numberOfEntries--;
                        return true;
                    } else {                                    //condition: if the entry is in middle
                        currentNode.next.previous = currentNode.previous;
                        currentNode.previous.next = currentNode.next;
                        currentNode = null;
                        numberOfEntries--;
                        return true;
                    }
                }
                counter++;
                currentNode = currentNode.next;

            } while (currentNode != null);
            return false;
        }
    }

    @Override
    public boolean move(T entry, Integer newPosition) {
        Node chosenNode = new Node(entry);  //condition: the entry must exists in the list, not empty list, positiev value and not more than current entries
        if (!search(entry) || this.isEmpty() || newPosition <= 0 || newPosition > numberOfEntries) {
            return false;
        } else {
            Node currentNode = firstNode;
            do {

                if (currentNode.data.compareTo(chosenNode.data) == 0) {
                    if (currentNode.previous == null) {//condition: firstNode and also moving to the first position
                        if(newPosition == 1){           //action: return true
                            return true;
                        }
                        currentNode.next.previous = null; //condition: firstNode and moving to other position other than one
                        firstNode = currentNode.next;
                        //currentNode.next = null;
                        break;
                    } else if (currentNode.next == null) { //condition: lastNode
                        currentNode.previous.next = null;
                        currentNode.previous = null;
                        break;
                    } else {
                        currentNode.previous.next = currentNode.next; //condition:middle node
                        currentNode.next.previous = currentNode.previous;
                        currentNode.previous = null;
                        currentNode.next = null;
                        break;
                    }
                }
                currentNode = currentNode.next; //move the node to next and check the next one until find the particular node
            } while (currentNode != null);
            Node tempNode = currentNode;
            currentNode = firstNode;  //move current node to the first place
            for (int i = 1; i < newPosition; i++) {
                currentNode = currentNode.next;  //move to the correct position
            }
            if (newPosition == 1) {
                tempNode.next = firstNode;
                firstNode.previous = tempNode;  //condition: if the new position is one
                firstNode = tempNode;
            } else {
                currentNode.previous.next = tempNode;
                tempNode.previous = currentNode.previous;  //condition: if the new position is other than one
                currentNode.previous = tempNode;
                tempNode.next = currentNode;
            }
            return true;
        }
    }

    @Override
    public boolean overwrite(T entry, Integer newPosition) {
        if (isEmpty() || newPosition <= 0 || newPosition > numberOfEntries) {
            return false;
        } else {
            Node currentNode = firstNode;
            for (int i = 1; i < newPosition; i++) {  //find the node in the particular position
                currentNode = currentNode.next;
            }
            currentNode.data = entry; //copy the data into the current node
            return true;
        }
    }

    @Override
    public boolean isEmpty() {
        if (numberOfEntries <= 0) { //check if entries is 0
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean search(T entry) {
        Node currentNode = firstNode;
        boolean entryFound = false;
        while (!entryFound && (currentNode != null)) { //find the entry until the current node dont have next node
            if (entry.equals(currentNode.data)) {
                entryFound = true;
            } else {
                currentNode = currentNode.next;
            }
        }
        return entryFound;
    }

    @Override
    public void display() {
        int index = 1;
        Node currentNode = firstNode;
        if (firstNode != null) {
            while (currentNode.getNext() != null) {
                System.out.print(index + ". " + currentNode.getData() + "\n");
                currentNode = currentNode.getNext();  //display the firstNode data
                index++;
            }
            System.out.print(index + ". " + currentNode.getData());
        }
    }

    @Override
    public Iterator<T> getListData() {
        return new listIterator();  //to the data from the list
    }

    @Override
    public void clear() {
        firstNode = null;
        numberOfEntries = 0;  //to clear the whole list
    }

    private class listIterator implements Iterator<T> {

        private QueueInterface<T> queue = new ArrayQueue<>();

        public listIterator() {
            list(firstNode);
        }

        private void list(Node currentNode) {
            while (currentNode != null) {
                queue.enqueue(currentNode.data);
                currentNode = currentNode.next;
            }
        }

        public boolean hasNext() {
            return !queue.isEmpty();
        }

        public T next() {
            if (!queue.isEmpty()) {
                return queue.dequeue();
            } else {
                return null;
            }
        }

    }

    @Override
    public String toString() {
        Node currentNode = firstNode;
        if (currentNode != null) {
            return "" + currentNode.data;
        } else {
            return "";
        }
    }

    private class Node {

        private T data;
        private Node next;
        private Node previous;  //doubly node

        private Node(T data) {
            this.data = data;
            this.next = null;
            this.previous = null;
        }

        private Node(T data, Node next, Node previous) {
            this.data = data;
            this.next = next;
            this.previous = previous;
        }

        public T getData() {
            return data;
        }

        public Node getNext() {
            return next;
        }

        public Node getPrevious() {
            return previous;
        }

        public void setData(T data) {
            this.data = data;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }

    }

}
