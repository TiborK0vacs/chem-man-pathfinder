package com.nincszsak.chemman.ringbuffer;

public class RingBufferIsFullException extends RuntimeException {
    public RingBufferIsFullException(String msg) {
        super(msg);
    }
}
