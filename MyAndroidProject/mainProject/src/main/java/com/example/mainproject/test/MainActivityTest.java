package com.example.mainproject.test;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.test.ActivityInstrumentationTestCase2;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mainproject.MainActivity_;
import com.example.mainproject.R;

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
