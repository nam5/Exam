package com.example.shaein.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DbAdapter {

    public static final String KEY_NAME = "name";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_ROWID = "_id";

    public static final int FIND_BY_NAME = 0;
    public static final int FIND_BY_PHONE = 1;

    private static final String TAG = "DbAdapter";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;
// 데이터베이스를 저장

    private static final String DATABASE_CREATE =
            "create table data (_id integer primary key autoincrement,"+
                    "name text not null, phone text not null);";

    private static final String DATABASE_NAME = "datum.db";
    private static final String DATABASE_TABLE = "data";
    private static final int DATABASE_VERSION = 1;

    private final Context mCtx;

    private class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);

// TODO Auto-generated constructor stub
        }

        public void onCreate(SQLiteDatabase db){
            db.execSQL(DATABASE_CREATE);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            Log.w(TAG, "Upgrading db from version" + oldVersion + " to" +
                    newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS data");
            onCreate(db);
        }

    }

    public DbAdapter(Context ctx){
        this.mCtx = ctx;
    }

    public DbAdapter open() throws SQLException{
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        mDbHelper.close();
    }

    public long createBook(String name, String phone){
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_PHONE, phone);

        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }

    public boolean deleteBook(long rowID){
        return mDb.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowID, null) > 0;
    }

    public Cursor fetchAllBooks(){
        return mDb.query(DATABASE_TABLE, new String[]{KEY_ROWID, KEY_NAME, KEY_PHONE}, null, null, null, null, null);

    }

    public Cursor fetchBook(long rowID) throws SQLException{
        Cursor mCursor =
                mDb.query(true, DATABASE_TABLE, new String[]{KEY_ROWID, KEY_NAME, KEY_PHONE}, KEY_ROWID + "=" + rowID, null, null, null, null, null);
        if(mCursor != null)
            mCursor.moveToFirst();
        return mCursor;
    }

    public boolean updateBook(long rowID, String name, String phone){
        ContentValues args = new ContentValues();
        args.put(KEY_NAME, name);
        args.put(KEY_PHONE, phone);

        return mDb.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowID, null) > 0;
    }


}
