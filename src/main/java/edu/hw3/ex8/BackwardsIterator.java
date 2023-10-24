package edu.hw3.ex8;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class BackwardsIterator<T> implements Iterator<T> {

    List<T> list;

    int index;

    public BackwardsIterator(Collection<T> list) {
        this.list = new ArrayList<>(list);
        this.index = list.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return index >= 0;
    }

    @Override
    public T next() {
        return list.get(index--);
    }
}
