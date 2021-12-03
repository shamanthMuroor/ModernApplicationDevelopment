package www.example.lab5;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHandler extends SQLiteOpenHelper {
    private static final int Db_Version = 1;
    private static final String Db_Name = "users";
    private static final String Table_Name = "user";
    private static final String User_id = "id";
    private static final String User_name = "name";
    private static final String User_password = "password";

    public DbHandler(MainActivity context) {
        super(context, Db_Name, null, Db_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Create_Table="CREATE TABLE " + Table_Name + "(" + User_id + " INTEGER PRIMARY KEY," + User_name + " TEXT," + User_password + " TEXT" + ")";
        db.execSQL(Create_Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ Table_Name);
        onCreate(db);
    }

    public void addUser(User usr) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(User_name, usr.getName());
        cv.put(User_password, usr.getPassword());
        db.insert(Table_Name, null, cv);
        db.close();
    }
    public int checkUser(User us) {
        int id=-1;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT id FROM user WHERE name=? AND password=?",new String[]{us.getName(),us.getPassword()});
        if(cursor.getCount()>0) {
            cursor.moveToFirst();
            id=cursor.getInt(0);
            cursor.close();
        }
        return id;
    }

}
