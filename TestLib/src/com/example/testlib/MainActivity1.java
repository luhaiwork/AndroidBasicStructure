package com.example.testlib;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity1 extends Activity {
	public static String myteststr="sss";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
