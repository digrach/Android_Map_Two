package chis.dip.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
//http://www.vogella.com/tutorials/AndroidSQLite/article.html
public class MySQLLiteHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "mapfeatures.db";
	private static final int DATABASE_VERSION = 1;

	public static final String TABLE_USER_FEATURES = "userfeatures";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_DESCRIPTION = "description";
	public static final String COLUMN_LAT = "lat";
	public static final String COLUMN_LNG = "lng";
	

	// Database creation sql statement
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_USER_FEATURES + "(" + 
			COLUMN_ID + " integer primary key autoincrement, " + 
			COLUMN_NAME + " text not null, " +
			COLUMN_DESCRIPTION + " text not null, " +
			COLUMN_LAT + " real not null, "  + 
			COLUMN_LNG + " real not null);";


	public MySQLLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);		
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(MySQLLiteHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_FEATURES);
		onCreate(db);		
	}

}
