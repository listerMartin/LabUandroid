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

	// TABLE
	private static String TABLE_PC = "tblPc";
	// COLUMNS
	private static String ID_PC = "id_pc";
	private static String lABName = "labname";
	private static String COL_PCNAME = "pcname";
	private static String COL_COMMENTS = "comments";
	private static String COL_EQUIPMENTS = "equipments";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String query = "CREATE TABLE " + TABLE_ROOMS + "(" + COL_ID
				+ " INTEGER PRIMARY KEY, " + COL_LAB + " TEXT, " + COL_PC
				+ " TEXT )";

		String query1 = "CREATE TABLE " + TABLE_PC + "(" + ID_PC
				+ " INTEGER PRIMARY KEY, " + lABName + " TEXT, " + COL_PCNAME
				+ " TEXT, " + COL_COMMENTS + " TEXT, " + COL_EQUIPMENTS
				+ " TEXT)";
		db.execSQL(query);
		db.execSQL(query1);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		String query = "DROP TABLE IF EXISTS " + TABLE_ROOMS;
		String query1 = "DROP TABLE IF EXISTS " + TABLE_PC;
		db.execSQL(query);
		db.execSQL(query1);
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
	
	void insertPC(PCtask t) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues c = new ContentValues();

		c.put(lABName, t.getLabname());
		c.put(COL_PCNAME, t.getPCname());
		c.put(COL_COMMENTS, t.getcomment());
		c.put(COL_EQUIPMENTS, t.getEquipment());
		db.insert(TABLE_PC, null, c);
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
	public List<PCtask> getPC() {
		List<PCtask> list1 = new ArrayList<PCtask>();
		String query = "SELECT * FROM " + TABLE_PC;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c = db.rawQuery(query, null);

		if (c.moveToFirst()) {

			do {
				PCtask t = new PCtask();
				t.setId(Integer.parseInt(c.getString(0)));
				t.setLabname(c.getString(1));
				t.setPCname(c.getString(2));
				t.setcomment(c.getString(3));
				t.setEquipment(c.getString(4));
				list1.add(t);
			} while (c.moveToNext());

		}

		return list1;
	}

	public Cursor showAll() {

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor searchAll = db.query(true, TABLE_ROOMS, new String[] { COL_ID,
				COL_LAB, COL_PC }, null, null, null, null, null, null);
		return searchAll;
	}

	public int updateRoom(RoomsTask task) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(COL_LAB, task.getLab());

		// updating row
		return db.update(TABLE_ROOMS, values, COL_ID + " = ?",
				new String[] { String.valueOf(task.getId()) });
	}
	
	public int updatePC(PCtask task) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(COL_COMMENTS, task.getcomment());
		values.put(COL_EQUIPMENTS, task.getEquipment());
		// updating row
		return db.update(TABLE_PC, values, ID_PC + " = ?",
				new String[] { String.valueOf(task.getId()) });
	}

	public void deleteTask(RoomsTask task) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_ROOMS, COL_ID + " = ?",
				new String[] { String.valueOf(task.getId()) });
		db.close();
	}
	public void deletePC(PCtask task) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_PC, ID_PC + " = ?",
				new String[] { String.valueOf(task.getId()) });
		db.close();
	}
}
