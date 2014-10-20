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

	// TABLE
	private static String TABLE_SCHEDULE = "tblsched";
	// COLUMNS
	private static String ID_SHCHED = "id_pc";
	private static String COL_labname = "c_labname";
	private static String COL_code = "c_code";
	private static String COL_description = "c_description";
	private static String COL_subject = "c_subject";
	private static String COL_day = "c_day";
	private static String COL_from = "c_from";
	private static String COL_to = "c_to ";
	private static String COL_instructor = "c_instructor";

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

		String query2 = "CREATE TABLE " + TABLE_SCHEDULE + "(" + ID_SHCHED
				+ " INTEGER PRIMARY KEY, " + COL_labname + " TEXT, " + COL_code
				+ " TEXT, " + COL_description + " TEXT, " + COL_subject
				+ " TEXT, " + COL_day + " TEXT, " + COL_from + " TEXT, "
				+ COL_to + " TEXT, " + COL_instructor + " TEXT)";

		db.execSQL(query);
		db.execSQL(query1);
		db.execSQL(query2);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		String query = "DROP TABLE IF EXISTS " + TABLE_ROOMS;
		String query1 = "DROP TABLE IF EXISTS " + TABLE_PC;
		String query3 = "DROP TABLE IF EXISTS " + TABLE_SCHEDULE;
		db.execSQL(query);
		db.execSQL(query1);
		db.execSQL(query3);
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

	void insertSched(SchedulesTask t) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues c = new ContentValues();

		c.put(COL_labname, t.getlabNameSched());
		c.put(COL_code, t.getcode());
		c.put(COL_description, t.getdescription());
		c.put(COL_subject, t.getsubject());
		c.put(COL_day, t.getday());
		c.put(COL_from, t.getfrom());
		c.put(COL_to, t.getto());
		c.put(COL_instructor, t.getinstructor());
		db.insert(TABLE_SCHEDULE, null, c);
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

	public List<SchedulesTask> getSched() {
		List<SchedulesTask> list2 = new ArrayList<SchedulesTask>();
		String query = "SELECT * FROM " + TABLE_SCHEDULE;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c = db.rawQuery(query, null);

		if (c.moveToFirst()) {

			do {
				SchedulesTask t = new SchedulesTask();
				t.setId(Integer.parseInt(c.getString(0)));
				t.setlabNameSched(c.getString(1));
				t.setcode(c.getString(2));
				t.setdescription(c.getString(3));
				t.setsubject(c.getString(4));
				t.setday(c.getString(5));
				t.setfrom(c.getString(6));
				t.setto(c.getString(7));
				t.setinstructor(c.getString(8));
				list2.add(t);
			} while (c.moveToNext());

		}

		return list2;
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
