package com.nincszsak.chemman;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;

public class Sandbox {

    @Test
    public void valami() {
        String[] s = null;
        List<String> sortedValues = new ArrayList<>(Arrays.asList(
                "9", "-100", "50",
                "0", "56.6", "90",
                "0.12", ".12", "02.34",
                "000.000"
        ));

        s = sortedValues.stream()
                .sorted(Comparator.comparing(BigDecimal::new, Comparator.reverseOrder())).toArray(String[]::new);
        //Output
        for (int i = 0; i < s.length; i++) {
            System.out.println(s[i]);
        }
    }
}
