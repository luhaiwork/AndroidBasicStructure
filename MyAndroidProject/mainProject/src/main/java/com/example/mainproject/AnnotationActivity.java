package com.example.mainproject;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_annotation)
public class AnnotationActivity extends FragmentActivity {
    @App
    MyApplication myApplication;
    @ViewById
    TextView tv_result;
    private String TAG = AnnotationActivity.class.getSimpleName().toString();

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction beginTransaction = fm.beginTransaction();
        beginTransaction.add(R.id.myFragment, new MyFragment_(), "mytag");
        beginTransaction.commit();
        Log.e("test", "test");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }

    @AfterViews
    void afterViewLoad() {
        Log.e(TAG, myApplication.myVal);
        tv_result.setText(Html.fromHtml("<a href='http://www.baidu.com'><u>测试点击链接</u></a>"));
        tv_result.setMovementMethod(LinkMovementMethod.getInstance());
    }

    /*
     * (non-Javadoc)
     *
     * @see android.app.Activity#onRestart()
     */
    @Override
    protected void onRestart() {
        Log.e(TAG, "onRestart");
        super.onRestart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.annotation, menu);
        return true;
    }

    @Click
    void testThread() {
        doSomethingInThead();
    }

    @Background
    void doSomethingInThead() {
        for (int i = 0; i < 10; i++) {
            testUIThead(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @UiThread
    void testUIThead(int i) {
        tv_result.setText("i====" + i);
    }

}
