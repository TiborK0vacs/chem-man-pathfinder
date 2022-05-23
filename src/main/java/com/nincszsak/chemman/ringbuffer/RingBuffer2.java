package com.nincszsak.chemman.ringbuffer;

import java.lang.reflect.Array;

public class RingBuffer2<T> {

    private final T[] elements;
    private final int capacity;
    private int elementCount;
    private int readIndex;
    private int writeIndex;

    // Default capacity 64 elements
    // Keep this constructor, add new ones.

    public RingBuffer2(Class<T> clazz, int capacity) {
        this.elements = (T[]) Array.newInstance(clazz, capacity);
        this.capacity = capacity;
        this.elementCount = 0;
        this.readIndex = 0;
        this.writeIndex = 0;
    }

    public void put(T input) throws RingBufferIsFullException {
        if (elementCount < capacity) {
            elements[writeIndex] = input;
            writeIndex = (writeIndex + 1) % capacity;
            elementCount++;
        } else {
            throw new RingBufferIsFullException("RingBuffer is full. Retry the write after consuming an element.");
        }
    }

    public T get() throws EmptyRingBufferException {
        T result = null;
        if (elementCount > 0) {
            result = elements[readIndex];
            readIndex = (readIndex + 1) % capacity;
            elementCount--;
        } else {
            throw new EmptyRingBufferException("RingBuffer is empty.");
        }
        return result;
    }
}
