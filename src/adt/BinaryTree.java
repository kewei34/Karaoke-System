package adt;

// Author : Chong Ke Wei

import java.util.Iterator;

public class BinaryTree<T extends Comparable<T>> implements BinaryTreeInterface<T> {

    private Node root;
    private int numItems = 0;

    public BinaryTree() {
        root = null;
    }

    public BinaryTree(T rootData) {
        root = new Node(rootData);
    }

    @Override
    public int getNumItems() {
        return numItems;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public void makeEmpty() {
        root = null;
        numItems = 0;
    }

    @Override
    public boolean isRepeat(T entry) {
        return search(root, entry) != null;
    }

    private T search(Node currentNode, T entry) {

        if (currentNode != null) {
            T currentEntry = currentNode.data;

            //got this entry
            if (entry.compareTo(currentEntry) == 0) {
                return currentEntry;
            } else if (entry.compareTo(currentEntry) < 0) {
                return search(currentNode.left, entry);
            } else {
                return search(currentNode.right, entry);
            }
        }
        return null;
    }

    @Override
    public boolean add(T entry) {

        if (isEmpty()) {
            root = new Node(entry);
            numItems++;
            return true;
        } //item already exist
        else if (search(root, entry) != null) {
            return false;
        } else {
            return addEntry(root, entry);
        }
    }

    private boolean addEntry(Node currentNode, T entry) {

        // entry matches entry in current node
        if (entry.compareTo(currentNode.data) == 0) {
            currentNode.data = entry;
            return true;
        } // entry less than currentNode, add to left
        else if (entry.compareTo(currentNode.data) < 0) {
            //if left is not empty,continue search
            if (currentNode.hasLeft()) {
                addEntry(currentNode.left, entry);
            } else {
                currentNode.left = new Node(entry);
                numItems++;
                return true;
            }
        } // entry greater than currentNode , add to right
        else {
            if (currentNode.hasRight()) {
                addEntry(currentNode.right, entry);
            } else {
                currentNode.right = new Node(entry);
                numItems++;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean remove(T entry) {
        boolean sucess = false;
        if (search(root, entry) == null) {
            sucess = false;
        } else {
            sucess = removeEntry(entry);
        }
        return sucess;
    }

    private boolean removeEntry(T entry) {

        //use preorder to get correct tree struture
        Iterator it = getPreorderIterator();
        BinaryTree newTree = new BinaryTree();
        T item = null;
        while (it.hasNext()) {
            item = (T) it.next();
            //add the item that not equal to the entry >>skip the entry
            if (item.compareTo(entry) != 0) {
                //create a new tree
                newTree.add(item);
            }
        }
        numItems--;
        root = newTree.root;
        return true;
    }

    @Override
    public T getObj(Integer index, BinaryTreeInterface tree) {
        if (tree.getNumItems() < index || tree.isEmpty()) {
            return null;
        }
        Iterator it = tree.getInorderIterator();
        int counter = 1;
        T item = null;
        while (it.hasNext()) {
            item = (T) it.next();
            if (counter == index) {
                return item;
            }
            counter++;
        }
        return item;
    }

    @Override
    public Iterator<T> getInorderIterator() {
        return new InorderIterator();
    }

    @Override
    public Iterator<T> getPreorderIterator() {
        return new PreorderIterator();
    }

    private class Node {

        private T data;
        private Node left;
        private Node right;

        public Node() {
            this(null);
        }

        public Node(T data) {
            this(data, null, null);
        }

        public Node(T data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public boolean hasLeft() {
            return left != null;
        }

        public boolean hasRight() {
            return right != null;
        }

        public boolean isLeaf() {
            return (left == null) && (right == null);
        }
    }

    private class InorderIterator implements Iterator<T> {

        private QueueInterface<T> queue = new ArrayQueue<>();

        public InorderIterator() {
            queue.clear();
            inorder(root);
        }

        private void inorder(Node treeNode) {
            if (treeNode != null) {
                inorder(treeNode.left);
                queue.enqueue(treeNode.data);
                inorder(treeNode.right);
            }
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public T next() {
            if (!queue.isEmpty()) {
                return queue.dequeue();
            } else {
                return null;
            }
        }

    }

    private class PreorderIterator implements Iterator<T> {

        private QueueInterface<T> queue = new ArrayQueue<>();

        public PreorderIterator() {
            queue.clear();
            preOrder(root);
        }

        private void preOrder(Node treeNode) {
            if (treeNode != null) {
                queue.enqueue(treeNode.data);
                preOrder(treeNode.left);
                preOrder(treeNode.right);
            }
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public T next() {
            if (!queue.isEmpty()) {
                return queue.dequeue();
            } else {
                return null;
            }
        }

    }
}
