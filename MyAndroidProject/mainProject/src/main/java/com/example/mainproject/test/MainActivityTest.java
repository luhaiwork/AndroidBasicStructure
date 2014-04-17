package com.example.mainproject.test;

import android.test.ActivityInstrumentationTestCase2;

import com.example.mainproject.MainActivity_;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity_> {

    public MainActivityTest() {
        super(MainActivity_.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

    }

    public void testForMainActivityInit() {
        assertEquals("a", "a");
    }

    public void testForMainActivityInit2() {
        assertEquals("a", "a");
    }
}
