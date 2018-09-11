package com.geeklog.dto;

public class Page<T> {

    private int total;

    private T[] entities;

    public Page(int total, T[] entities) {
        this.total = total;
        this.entities = entities;
    }

    public int getTotal() {
        return total;
    }

    public T[] getEntities() {
        return entities;
    }
}
