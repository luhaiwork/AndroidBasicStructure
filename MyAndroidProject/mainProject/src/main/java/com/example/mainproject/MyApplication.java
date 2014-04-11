/**
 * 
 */
package com.example.mainproject;

import org.androidannotations.annotations.EApplication;

import com.example.mainproject.models.Note;
import com.example.mainproject.models.NoteTagLink;
import com.example.mainproject.models.Tag;

import se.emilsjolander.sprinkles.Migration;
import se.emilsjolander.sprinkles.Sprinkles;
import android.app.Application;

/**
 * </br>
 * 
 * @author luh </br>
 * @time 2014年2月27日 下午3:08:07 </br>
 * 
 */
@EApplication
public class MyApplication extends Application {
	public String myVal="test";
	/* (non-Javadoc)
	 * @see android.app.Application#onCreate()
	 */
	@Override
	public void onCreate() {
		//sprinkles 根据当前项目的数据库版本，自动进行升级操作。
		Sprinkles sprinkles = Sprinkles.init(getApplicationContext());
		//database version 1 
		Migration initialMigration = new Migration();
		initialMigration.dropTable(Note.class);
		sprinkles.addMigration(initialMigration);
		//database version2
		Migration initialMigration2 = new Migration();
		initialMigration2.dropTable(Note.class);
		initialMigration2.createTable(Note.class);
		sprinkles.addMigration(initialMigration2);
		//database version3 
		Migration initialMigration3 = new Migration();
		initialMigration3.dropTable(Note.class);
		initialMigration3.createTable(Note.class);
		sprinkles.addMigration(initialMigration3);
		//database version4
		Migration initialMigration4=new Migration();
		initialMigration4.dropTable(Note.class);
		initialMigration4.createTable(Note.class);
		initialMigration4.createTable(Tag.class);
		initialMigration4.createTable(NoteTagLink.class);
		sprinkles.addMigration(initialMigration4);
		super.onCreate();
	}
}
