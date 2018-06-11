package com.zone.androidtest.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by p_zoneguo on 2018/5/29.
 */
public class UtilsTest {
    @Test
    public void add() throws Exception {
        assertEquals(Utils.add(10, 19), 19);
    }

}