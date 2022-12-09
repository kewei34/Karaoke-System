package adt;

// Author : Chong Ke Wei

import java.util.Iterator;

public interface BinaryTreeInterface<T extends Comparable<T>> {

    public int getNumItems();

    public boolean isEmpty();

    public boolean isRepeat(T entry);

    public void makeEmpty();

    public boolean add(T entry);

    public boolean remove(T entry);

    public Iterator<T> getInorderIterator();

    public Iterator<T> getPreorderIterator();

    public T getObj(Integer index, BinaryTreeInterface tree);

}
