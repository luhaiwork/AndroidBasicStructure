package com.example.mainproject;

import java.io.InputStream;
import java.util.List;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mainproject.models.City;
import com.example.mainproject.models.District;
import com.example.mainproject.models.Province;
import com.example.mainproject.parser.ParserByPULL;

@SuppressLint("NewApi")
@EActivity(R.layout.activity_main)
public class MainActivity extends FragmentActivity {
	private String TAG = MainActivity.class.getSimpleName().toString();
	private ActionBarDrawerToggle drawerToggle = null;
	DrawerLayout drawer;
	LinearLayout drawerLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Click({ R.id.annotation_test, R.id.testid2, R.id.btn_testhttp })
	public void onClick(View v) {
		if (v.getId() == R.id.annotation_test) {
			Intent intent = new Intent(this, AnnotationActivity_.class);
			startActivity(intent);
		} else if (v.getId() == R.id.testid2) {
			// Intent intent = new Intent(this,MainActivity1.class);
			Intent intent = new Intent("com.example.testlib.MainActivity1");
			startActivity(intent);
		} else if (v.getId() == R.id.btn_testhttp) {
			Intent intent = new Intent(MainActivity.this,
					HttpTestActivity_.class);
			startActivity(intent);
		}
	}

	@Click
	void btn_sprinkles() {
		Intent intent = new Intent(this, SprinklesActivity_.class);
		startActivity(intent);
	}
	@Click
	void btn_locationSelect(){
		android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction beginTransaction = fm.beginTransaction();
		beginTransaction.add(R.id.main, new LocationSelectFragment_(), "locationSelectFragment");
		beginTransaction.commit();
		drawer.closeDrawer(drawerLayout);
	}

	@AfterViews
	void afterView() {
		// Tag tag = new Tag("myTag");
		// tag.save();
		// tag.setTagName("modify tag Name ");
		// tag.save();
		// OneQuery<Tag> one = Query.one(Tag.class,
		// "select * from Tag where tagId = ?", tag.getTagId());
		// Toast.makeText(this, tag.getTagName(), Toast.LENGTH_SHORT).show();
		// getActionBar().setDisplayHomeAsUpEnabled(true);
		// 设置显示drawer的提示button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		drawer = (DrawerLayout) findViewById(R.id.drawer);
		drawerToggle = new ActionBarDrawerToggle(this, drawer,
				R.drawable.ic_drawer, R.string.open, R.string.close) {
			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle("app name");
				super.onDrawerOpened(drawerView);
			}
		};
		drawer.setDrawerListener(drawerToggle);
			try {
				testxml();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		drawerLayout=(LinearLayout)findViewById(R.id.drawer_layout);
	}
	/**
	 * test android xml parser
	 * 
	 *
	 * @author luh </br>
	 * @time 2014年3月12日 下午3:54:16 </br>
	 *
	 * @throws Throwable
	 */
	void testxml() throws Throwable {
		AssetManager asset = getAssets();
		InputStream input = asset.open("basic_loaction_info.xml");
		List<Province> provinces = ParserByPULL.getProvince(input);
		for (Province province : provinces) {
			Log.e("test","province-name:"+province.getProvinceName()+"-code:"+province.getProvinceCode());
		}
		input = asset.open("basic_loaction_info.xml");
		List<City> cities = ParserByPULL.getCities(input, "1");
		for (City city : cities) {
			Log.e("test","city -name:"+city.getName()+"-code:"+city.getCode());
		}
		input = asset.open("basic_loaction_info.xml");
		List<District> districts = ParserByPULL.getDistricts(input, "0", "02");
		for (District district : districts) {
			Log.e("test", "district -name:"+district.getName()+" -code:"+district.getName());
		}
	}

	// 需要加入以下代码，以保证drawer的提示图标正常显示
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		drawerToggle.syncState();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// 用于控制用户点击菜单左侧按钮时drawer的开关
		if (drawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		Toast.makeText(this, "parent menu click", Toast.LENGTH_SHORT).show();
		Log.e("test", "parent menu item click");
		return super.onOptionsItemSelected(item);
	}

}
