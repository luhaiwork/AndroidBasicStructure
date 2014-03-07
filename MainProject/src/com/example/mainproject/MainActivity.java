package com.example.mainproject;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
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
//			Intent intent = new Intent(this,MainActivity1.class);
			Intent intent = new Intent("com.example.testlib.MainActivity1");
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
	@AfterViews
	void afterView(){
//		Tag tag = new Tag("myTag");
//		tag.save();
//		tag.setTagName("modify tag Name ");
//		tag.save();
//		OneQuery<Tag> one = Query.one(Tag.class, "select * from Tag where tagId = ?", tag.getTagId());
//		Toast.makeText(this, tag.getTagName(), Toast.LENGTH_SHORT).show();
			}
}
