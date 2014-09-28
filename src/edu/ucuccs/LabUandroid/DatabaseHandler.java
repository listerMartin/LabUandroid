package edu.ucuccs.LabUandroid;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
	// DATABASE VERSION
	private static int DATABASE_VERSION = 1;
	// DATABASE
	private static String DATABASE_NAME = "dbLabUandroid";
	// TABLE
	private static String TABLE_ROOMS = "tblRooms";
	// COLUMNS
	private static String COL_ID = "col_id";
	private static String COL_LAB = "col_lab";
	private static String COL_PC = "col_pc";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String query = "CREATE TABLE " + TABLE_ROOMS + "(" + COL_ID
				+ " INTEGER PRIMARY KEY, " + COL_LAB + " TEXT, " + COL_PC
				+ " TEXT )";
		db.execSQL(query);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		String query = "DROP TABLE IF EXISTS " + TABLE_ROOMS;
		db.execSQL(query);
		onCreate(db);

	}

	void insertTask(RoomsTask t) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues c = new ContentValues();

		c.put(COL_LAB, t.getLab());
		c.put(COL_PC, t.getPC());
		db.insert(TABLE_ROOMS, null, c);
		db.close();
	}

	public List<RoomsTask> getAllTask() {
		List<RoomsTask> list = new ArrayList<RoomsTask>();
		String query = "SELECT * FROM " + TABLE_ROOMS;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c = db.rawQuery(query, null);

		if (c.moveToFirst()) {

			do {
				RoomsTask t = new RoomsTask();
				t.setId(Integer.parseInt(c.getString(0)));
				t.setLab(c.getString(1));
				t.setPC(c.getString(2));
				list.add(t);
			} while (c.moveToNext());

		}

		return list;
	}

	public Cursor showAll() {

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor searchAll = db.query(true, TABLE_ROOMS, new String[] { COL_ID,
				COL_LAB, COL_PC }, null, null, null, null, null, null);
		return searchAll;
	}
}
