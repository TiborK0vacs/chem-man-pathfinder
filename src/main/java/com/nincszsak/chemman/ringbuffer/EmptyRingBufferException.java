package com.nincszsak.chemman.ringbuffer;

public class EmptyRingBufferException extends RuntimeException {
    public EmptyRingBufferException(String msg) {
        super(msg);
    }
}
