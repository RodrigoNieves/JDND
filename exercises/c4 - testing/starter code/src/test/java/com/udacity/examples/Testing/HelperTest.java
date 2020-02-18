package com.udacity.examples.Testing;

import org.junit.Test;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;

import static org.junit.Assert.*;

public class HelperTest {
    @Test
    public void test() {
        assertEquals(3,3);
    }

    @Test
    public void verify_getCount() {
        List<String> empNames = Arrays.asList("Rodrigo", "Santiago");
        long actual = Helper.getCount(empNames);
        assertEquals(2, actual);
    }

    @Test
    public void verify_getStats() {
        List<Integer> yrsOfExperience = Arrays.asList(13,4,15,6,17,8,19,1,2,3);
        List<Integer> expectEquals = Arrays.asList(13,4,15,6,17,8,19,1,2,3);
        IntSummaryStatistics stats = Helper.getStats(yrsOfExperience);
        assertEquals(19,stats.getMax());
        assertEquals(expectEquals, yrsOfExperience);
    }

    @Test
    public void compare_arrays(){
        int[] yrs = {10,14,2};
        int[] expected = {10,14,2};
        assertArrayEquals(expected,yrs);
    }
}
