package com.nincszsak.chemman.ringbuffer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RingBuffer3Test {

    @Test
    public void testPutSomeElementsAndRetrieveThem() {
        // Given
        RingBuffer3<String> underTest = new RingBuffer3<>(String.class, 5);
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
    public void testFirstGetFromEmpty() {
        // Given
        RingBuffer3<String> underTest = new RingBuffer3<>(String.class, 3);

        // When
        RuntimeException exception = assertThrows(EmptyRingBufferException.class, underTest::get);

        // Then
        assertNotNull(exception);
    }

    @Test
    public void testLongRunningCheckPutAndGet10kTimes() {
        // Given
        RingBuffer3<String> underTest = new RingBuffer3<>(String.class, 5);
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

    @Test
    public void testResizing() {
        // Given
        RingBuffer3<String> underTest = new RingBuffer3<>(String.class, 5);
        String element1 = "abc1";
        String element2 = "abc2";
        String element3 = "abc3";
        String element4 = "abc4";
        String element5 = "abc5";
        underTest.put(element1);
        underTest.put(element2);
        underTest.put(element3);
        underTest.put(element4);
        underTest.put(element5);
        underTest.get();
        underTest.get();
        underTest.get();
        String element6 = "abc6";
        String element7 = "abc7";
        String element8 = "abc8";
        String element9 = "abc9";
        underTest.put(element6);
        underTest.put(element7);
        underTest.put(element8);
        // When
        underTest.put(element9);
        // Then
        String actual4 = underTest.get();
        String actual5 = underTest.get();
        String actual6 = underTest.get();
        String actual7 = underTest.get();
        String actual8 = underTest.get();
        String actual9 = underTest.get();

        assertEquals(element4, actual4);
        assertEquals(element5, actual5);
        assertEquals(element6, actual6);
        assertEquals(element7, actual7);
        assertEquals(element8, actual8);
        assertEquals(element9, actual9);
    }

    @Test
    public void testResizingWillCopyElementsTillTheIndexOfNewCapacity() {
        // Given
        RingBuffer3<String> underTest = new RingBuffer3<>(String.class, 5);
        String element1 = "abc1";
        String element2 = "abc2";
        String element3 = "abc3";
        String element4 = "abc4";
        String element5 = "abc5";
        underTest.put(element1);
        underTest.put(element2);
        underTest.put(element3);
        underTest.put(element4);
        underTest.put(element5);
        underTest.get();
        underTest.get();
        underTest.get();
        underTest.get();
        underTest.get();
        String element6 = "abc6";
        String element7 = "abc7";
        String element8 = "abc8";
        String element9 = "abc9";
        String element10 = "abc10";
        underTest.put(element6);
        underTest.put(element7);
        underTest.put(element8);
        underTest.put(element9);
        // When
        underTest.put(element10);
        // Then
        String actual6 = underTest.get();
        String actual7 = underTest.get();
        String actual8 = underTest.get();
        String actual9 = underTest.get();
        String actual10 = underTest.get();

        assertEquals(element6, actual6);
        assertEquals(element7, actual7);
        assertEquals(element8, actual8);
        assertEquals(element9, actual9);
        assertEquals(element10, actual10);
    }

    @Test
    public void testCheckFullCapacityAfterResizing() {
        // Given
        RingBuffer3<String> underTest = new RingBuffer3<>(String.class, 3);
        String element1 = "abc1";
        String element2 = "abc2";
        String element3 = "abc3";
        String element4 = "abc4";
        String element5 = "abc5";
        String element6 = "abc6";
        underTest.put(element1);
        underTest.put(element2);
        underTest.put(element3);
        // When
        underTest.put(element4);
        underTest.put(element5);
        underTest.put(element6);
        // Then
        String actual1 = underTest.get();
        String actual2 = underTest.get();
        String actual3 = underTest.get();
        String actual4 = underTest.get();
        String actual5 = underTest.get();
        String actual6 = underTest.get();

        assertEquals(element1, actual1);
        assertEquals(element2, actual2);
        assertEquals(element3, actual3);
        assertEquals(element4, actual4);
        assertEquals(element5, actual5);
        assertEquals(element6, actual6);
    }

    @Test
    public void testCheckFullCapacityAfterResizingWithShift() {
        // Given
        RingBuffer3<String> underTest = new RingBuffer3<>(String.class, 3);
        String element1 = "abc1";
        String element2 = "abc2";
        String element3 = "abc3";
        String element4 = "abc4";
        String element5 = "abc5";
        String element6 = "abc6";
        String element7 = "abc7";
        String element8 = "abc8";
        underTest.put(element1);
        underTest.put(element2);
        underTest.put(element3);
        underTest.get();
        underTest.get();

        // When
        underTest.put(element4);
        underTest.put(element5);
        underTest.put(element6);
        underTest.put(element7);
        underTest.put(element8);
        // Then
        assertEquals(6, underTest.getSize());
        String actual3 = underTest.get();
        String actual4 = underTest.get();
        String actual5 = underTest.get();
        String actual6 = underTest.get();
        String actual7 = underTest.get();
        String actual8 = underTest.get();

        assertEquals(element3, actual3);
        assertEquals(element4, actual4);
        assertEquals(element5, actual5);
        assertEquals(element6, actual6);
        assertEquals(element7, actual7);
        assertEquals(element8, actual8);
    }

    @Test
    public void testCheckFullCapacityAfterTwoResizes() {
        // Given
        RingBuffer3<String> underTest = new RingBuffer3<>(String.class, 3);
        String element1 = "abc1";
        String element2 = "abc2";
        String element3 = "abc3";
        String element4 = "abc4";
        String element5 = "abc5";
        String element6 = "abc6";
        String element7 = "abc7";
        String element8 = "abc8";
        String element9 = "abc9";
        String element10 = "abc10";
        String element11 = "abc11";
        String element12 = "abc12";
        String element13 = "abc13";
        String element14 = "abc14";
        underTest.put(element1);
        underTest.put(element2);
        underTest.put(element3);
        underTest.get();
        underTest.get();

        // When
        underTest.put(element4);
        underTest.put(element5);
        underTest.put(element6);
        underTest.put(element7);
        underTest.put(element8);
        underTest.put(element9);
        underTest.put(element10);
        underTest.put(element11);
        underTest.put(element12);
        underTest.put(element13);
        underTest.put(element14);
        // Then
        assertEquals(12, underTest.getSize());
        String actual3 = underTest.get();
        String actual4 = underTest.get();
        String actual5 = underTest.get();
        String actual6 = underTest.get();
        String actual7 = underTest.get();
        String actual8 = underTest.get();
        String actual9 = underTest.get();
        String actual10 = underTest.get();
        String actual11 = underTest.get();
        String actual12 = underTest.get();
        String actual13 = underTest.get();
        String actual14 = underTest.get();

        assertEquals(element3, actual3);
        assertEquals(element4, actual4);
        assertEquals(element5, actual5);
        assertEquals(element6, actual6);
        assertEquals(element7, actual7);
        assertEquals(element8, actual8);
        assertEquals(element9, actual9);
        assertEquals(element10, actual10);
        assertEquals(element11, actual11);
        assertEquals(element12, actual12);
        assertEquals(element13, actual13);
        assertEquals(element14, actual14);
    }
}
