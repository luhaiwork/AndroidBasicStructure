package se.emilsjolander.sprinkles;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class DbOpenHelper extends SQLiteOpenHelper {
	
	private DbOpenHelper(Context context) {
		super(context, "sprinkles.db", null,
				Sprinkles.sInstance.mMigrations.size());
		Log.e("aa","version"+Sprinkles.sInstance.mMigrations.size());
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.e("aa","dbhelper onCreate excute");
		executeMigrations(db, 0, Sprinkles.sInstance.mMigrations.size());
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.e("aa","dbhelper onUpgrade excute");
		executeMigrations(db, oldVersion, newVersion);
	}
	
	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);
		db.execSQL("PRAGMA foreign_keys=ON;");
	}

	private void executeMigrations(SQLiteDatabase db, int oldVersion, int newVersion) {
		for (int i = oldVersion; i < newVersion; i++) {
			Sprinkles.sInstance.mMigrations.get(i).execute(db);
		}
	}
	
	static SQLiteDatabase sInstance;
	
	static synchronized SQLiteDatabase getInstance() {
		if (sInstance == null) {
			sInstance = new DbOpenHelper(Sprinkles.sInstance.mContext).getWritableDatabase();
		}
		return sInstance;
	}

}
