package com.nincszsak.chemman.ringbuffer;

import java.lang.reflect.Array;

public class RingBuffer3<T> {
    private final static int DEFAULT_CAPACITY = 64;
    private T[] elements;
    private int capacity;
    private int elementCount;
    private int readIndex;
    private int writeIndex;
    private Class<T> clazz;

    // Default capacity 64 elements
    // Keep this constructor, add new ones.

    public RingBuffer3(Class<T> clazz) {
        this.elements = (T[]) Array.newInstance(clazz, DEFAULT_CAPACITY);
        this.capacity = DEFAULT_CAPACITY;
        this.elementCount = 0;
        this.readIndex = 0;
        this.writeIndex = 0;
        this.clazz = clazz;
    }

    public RingBuffer3(Class<T> clazz, int capacity) {
        this.elements = (T[]) Array.newInstance(clazz, capacity);
        this.capacity = capacity;
        this.elementCount = 0;
        this.readIndex = 0;
        this.writeIndex = 0;
        this.clazz = clazz;
    }

    public void put(T input) {
        if (elementCount < capacity) {
            elements[writeIndex] = input;
            writeIndex = (writeIndex + 1) % capacity;
            elementCount++;
        } else {
            int newCapacity = capacity * 2;
            T[] newArray = (T[]) Array.newInstance(clazz, newCapacity);
            // copy the old values
            for (int i = 0; i < capacity; i++) {
                newArray[i] = elements[i];
            }
            // copying the not read elements from the preset of old values to the right place
            if (writeIndex <= readIndex) {
                for (int i = 0; i < writeIndex; i++) {
                    newArray[capacity + i] = elements[i];
                }
                writeIndex += capacity;
            }
            // saving the new element
            newArray[writeIndex] = input;
            writeIndex = (writeIndex + 1) % newCapacity;
            elementCount++;
            this.elements = newArray;
            this.capacity = newCapacity;
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

    public int getSize() {
        return elements.length;
    }
}