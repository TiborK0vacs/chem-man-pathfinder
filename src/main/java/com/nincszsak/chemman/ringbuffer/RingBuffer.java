package com.nincszsak.chemman.ringbuffer;

import java.util.LinkedList;
import java.util.List;

public class RingBuffer<T> {

    private final List<T> elements;
    private final int capacity;

    public RingBuffer(int capacity) {
        this.elements = new LinkedList<>();
        this.capacity = capacity;
    }

    public void put(T input) throws RingBufferIsFullException {
        if (elements.size() < capacity) {
            elements.add(input);
        } else {
            throw new RingBufferIsFullException("RingBuffer is full. Retry the write after consuming an element.");
        }
    }

    public T get() {
        T result = null;
        if (!elements.isEmpty()) {
            result = elements.remove(0);
        }
        return result;
    }
}
