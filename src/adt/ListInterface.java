package adt;

import java.util.Iterator;

public interface ListInterface<T> {

    public int getNumberOfEntries();

    public boolean add(T newEntry);

    public boolean remove(T entry);

    public boolean move(T entry, Integer newPosition);

    public boolean overwrite(T entry, Integer newPosition);

    public boolean isEmpty();

    public boolean search(T entry);

    public void display();

    public void clear();

    public Iterator<T> getListData();
}
