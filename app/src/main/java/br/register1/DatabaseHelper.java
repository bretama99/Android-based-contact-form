package br.register1;

import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObservable;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DBNAME="STUDENT";
    private static final String TABLEE="STUDENT_TABLE";
    private static final String COL1="ID";
    private static final String COL2="NAME";
    private static final String COL3="SURNAME";
    private static final String COL4="MARKS";
    public DatabaseHelper(Context context) {
        super(context, DBNAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLEE+ " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT,SURNAME TEXT, MARKS TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLEE);
        onCreate(db);

    }
    public boolean insertData(String name,String surname,String marks){
        long result = -1;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues =new ContentValues();
        contentvalues.put(COL2,name);
        contentvalues.put(COL3,surname);
        contentvalues.put(COL4,marks);
        result = db.insert(TABLEE,null,contentvalues);
        if (result==-1){
            return false;
        }else {
            return true;
        }

    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLEE,null);
        return res;
    }
    public boolean Update(String id, String name, String surname, String marks){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues =new ContentValues();
        contentvalues.put(COL1,id);
        contentvalues.put(COL2,name);
        contentvalues.put(COL3,surname);
        contentvalues.put(COL4,marks);

        db.update(TABLEE,contentvalues,"ID = ?", new String[] { id });
        return true;
    }
    public Integer Delete(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLEE,"ID = ?", new String[] { id });

    }
}
