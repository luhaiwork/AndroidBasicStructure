package com.example.mainproject.test;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.test.suitebuilder.annotation.SmallTest;
import android.view.View;
import android.widget.Button;

import com.example.mainproject.AnnotationActivity_;
import com.example.mainproject.MainActivity_;
import com.example.mainproject.R;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity_> {

    public MainActivityTest() {
        super(MainActivity_.class);
    }
    MainActivity_ testAct;
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(true);
        testAct=getActivity();
    }

    /**
//     * method name must start with "test"
//     */
    @SmallTest
    public void testForMainActivityInit() {
        assertEquals("a", "a");
    }

    /**
     * test annotation start btn
     */
    @SmallTest
    public void testAnnotation_test(){
        final View decorView = testAct.getWindow().getDecorView();
        Button testAnnotationBtn= (Button) testAct.findViewById(R.id.annotation_test);
        ViewAsserts.assertOnScreen(decorView,testAnnotationBtn);
        assertTrue(View.VISIBLE==testAnnotationBtn.getVisibility());
//        TouchUtils.clickView(this, testAnnotationBtn);
//        assertEquals("test",testAnnotationBtn.getText());
    }

    /**
     * test open activity
     *
     * drawer must open before excute this method
     */
    public void teststartOpenAnnotation(){
        Button testAnnotationBtn= (Button) testAct.findViewById(R.id.annotation_test);
        //Create and add an ActivityMonitor to monitor interaction between the system and the
        //ReceiverActivity
        Instrumentation.ActivityMonitor receiverActivityMonitor = getInstrumentation()
                .addMonitor(AnnotationActivity_.class.getName(), null, false);
        assertNotNull(testAnnotationBtn);
        TouchUtils.clickView(this, testAnnotationBtn);
        AnnotationActivity_ receiverActivity = (AnnotationActivity_) receiverActivityMonitor
                .waitForActivityWithTimeout(5000);
        assertNotNull(receiverActivity);
        assertEquals("test",testAnnotationBtn.getText());
    }
}
