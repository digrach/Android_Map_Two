package chis.dip.data;

import java.util.ArrayList;
import java.util.List;

import chis.dip.model.MapFeature;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class UserFeaturesDataSource {

	// Database fields
	private SQLiteDatabase database;
	private MySQLLiteHelper dbHelper;
	private String[] allColumns = { 
			MySQLLiteHelper.COLUMN_ID,
			MySQLLiteHelper.COLUMN_NAME,
			MySQLLiteHelper.COLUMN_DESCRIPTION,
			MySQLLiteHelper.COLUMN_LAT,
			MySQLLiteHelper.COLUMN_LNG};


	public UserFeaturesDataSource(Context context) {
		dbHelper = new MySQLLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public MapFeature createComment(String name, String description, double lat, double lng) {
		ContentValues values = new ContentValues();
		values.put(MySQLLiteHelper.COLUMN_NAME, name);
		values.put(MySQLLiteHelper.COLUMN_DESCRIPTION, description);
		values.put(MySQLLiteHelper.COLUMN_LAT, lat);
		values.put(MySQLLiteHelper.COLUMN_LNG, lng);


		long insertId = database.insert(MySQLLiteHelper.TABLE_USER_FEATURES, null,
				values);
		Cursor cursor = database.query(MySQLLiteHelper.TABLE_USER_FEATURES,
				allColumns, MySQLLiteHelper.COLUMN_ID + " = " + insertId, null,
				null, null, null);
		cursor.moveToFirst();
		MapFeature newMapFeature = cursorToMapFeature(cursor);
		cursor.close();
		return newMapFeature;
	}

	private MapFeature cursorToMapFeature(Cursor cursor) {
		MapFeature mapFeature = new MapFeature();
		mapFeature.setId(cursor.getLong(0));
		mapFeature.setName(cursor.getString(1));
		mapFeature.setDescription(cursor.getString(2));
		mapFeature.setLat(cursor.getDouble(3));
		mapFeature.setLng(cursor.getDouble(4));
		return mapFeature;
	}

	public List<MapFeature> getAllMapFeatures() {
		List<MapFeature> mapFeatures = new ArrayList<MapFeature>();

		Cursor cursor = database.query(MySQLLiteHelper.TABLE_USER_FEATURES,
				allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			MapFeature mapFeature = cursorToMapFeature(cursor);
			mapFeatures.add(mapFeature);
			cursor.moveToNext();
		}
		// make sure to close the cursor
		cursor.close();
		return mapFeatures;
	}
	
	public void deleteMapFeature(MapFeature mapFeature) {
	    long id = mapFeature.getId();
	    System.out.println("MapFeature deleted with id: " + id);
	    database.delete(MySQLLiteHelper.TABLE_USER_FEATURES, MySQLLiteHelper.COLUMN_ID
	        + " = " + id, null);
	  }

}
