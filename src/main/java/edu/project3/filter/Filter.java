package edu.project3.filter;

import java.util.List;

public abstract class Filter<T> {

    public List<T> filter(List<T> listToFilter) {
        return listToFilter.stream().filter(this::passFiltration).toList();
    }

    protected abstract boolean passFiltration(T element);
}
