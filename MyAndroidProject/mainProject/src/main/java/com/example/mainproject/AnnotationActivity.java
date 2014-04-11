package com.example.mainproject;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.App;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;
import com.example.mainproject.R;

@EActivity(R.layout.activity_annotation)
public class AnnotationActivity extends FragmentActivity {
	private String TAG = AnnotationActivity.class.getSimpleName().toString();
	@App
	MyApplication myApplication;
	@ViewById
	TextView tv_result;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction beginTransaction = fm.beginTransaction();
		beginTransaction.add(R.id.myFragment, new MyFragment_(), "mytag");
		beginTransaction.commit();
        Log.e("test","test");
	}

	@AfterViews
	void afterViewLoad() {
		Log.e(TAG, myApplication.myVal);
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
