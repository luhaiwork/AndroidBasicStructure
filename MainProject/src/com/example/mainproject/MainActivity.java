package com.example.mainproject;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import com.example.mainproject.R;
import com.example.testlib.MainActivity1;
@EActivity(R.layout.activity_main)
public class MainActivity extends Activity{
	private String TAG=MainActivity.class.getSimpleName().toString();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Click({R.id.annotation_test,R.id.testid2,R.id.btn_testhttp})
	public void onClick(View v) {
		if(v.getId()==R.id.annotation_test){
			Intent intent = new Intent(this,AnnotationActivity_.class);
			startActivity(intent);
		}else if(v.getId()==R.id.testid2){
			Log.e(TAG, MainActivity1.myteststr);
			Intent intent = new Intent(this,MainActivity1.class);
			startActivity(intent);
		}else if(v.getId()==R.id.btn_testhttp){
			Intent intent = new Intent(MainActivity.this,HttpTestActivity_.class);
			startActivity(intent);
		}
	}
	@Click
	void btn_sprinkles(){
		Intent intent = new Intent(this,SprinklesActivity_.class);
		startActivity(intent);
	}

}
