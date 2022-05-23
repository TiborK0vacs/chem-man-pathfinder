package com.nincszsak.chemman.ringbuffer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RingBuffer2Test {

    @Test
    public void testPutSomeElementsAndRetrieveThem() {
        // Given
        RingBuffer2<String> underTest = new RingBuffer2<>(String.class, 5);
        String element1 = "abc1";
        String element2 = "abc2";
        String element3 = "abc3";
        String element4 = "abc4";

        // When
        underTest.put(element1);
        underTest.put(element2);
        underTest.put(element3);
        underTest.put(element4);

        // Then
        String actual1 = underTest.get();
        String actual2 = underTest.get();
        String actual3 = underTest.get();
        String actual4 = underTest.get();

        assertEquals(element1, actual1);
        assertEquals(element2, actual2);
        assertEquals(element3, actual3);
        assertEquals(element4, actual4);
    }

    @Test
    public void testPutMoreElementsThanTheLimit() {
        // Given
        RingBuffer2<String> underTest = new RingBuffer2<>(String.class, 3);
        String element1 = "abc1";
        String element2 = "abc2";
        String element3 = "abc3";
        String element4 = "abc4";
        underTest.put(element1);
        underTest.put(element2);
        underTest.put(element3);

        // When
        RuntimeException exception = assertThrows(RingBufferIsFullException.class, () -> {
            underTest.put(element4);
        });

        // Then
        assertNotNull(exception);
    }

    @Test
    public void testPutMoreElementsThanTheLimitThenGetOneAndRetryTheLastPut() {
        // Given
        RingBuffer2<String> underTest = new RingBuffer2<>(String.class, 3);
        String element1 = "abc1";
        String element2 = "abc2";
        String element3 = "abc3";
        String element4 = "abc4";

        underTest.put(element1);
        underTest.put(element2);
        underTest.put(element3);

        RuntimeException exception = assertThrows(RingBufferIsFullException.class, () -> {
            underTest.put(element4);
        });
        assertNotNull(exception);

        String actual1 = underTest.get();
        assertEquals(element1, actual1);

        // When
        underTest.put(element4);

        // Then
        String actual2 = underTest.get();
        String actual3 = underTest.get();
        String actual4 = underTest.get();
        assertEquals(element2, actual2);
        assertEquals(element3, actual3);
        assertEquals(element4, actual4);
    }

    @Test
    public void testFirstGetFromEmpty() {
        // Given
        RingBuffer2<String> underTest = new RingBuffer2<>(String.class, 3);

        // When
        RuntimeException exception = assertThrows(EmptyRingBufferException.class, underTest::get);

        // Then
        assertNotNull(exception);
    }

    @Test
    public void testLongRunningCheckPutAndGet10kTimes() {
        // Given
        RingBuffer2<String> underTest = new RingBuffer2<>(String.class, 5);
        String element1 = null;
        String element2 = null;
        String actual1 = null;
        String actual2 = null;
        // When
        for (int i = 0; i < 10000; i++) {
            element1 = "abc" + i;
            element2 = "abc" + (i + 1);

            underTest.put(element1);
            underTest.put(element2);
            // Then
            actual1 = underTest.get();
            actual2 = underTest.get();

            assertEquals(element1, actual1);
            assertEquals(element2, actual2);
        }
    }

}