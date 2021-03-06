package com.ams.vocabforall;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Ams on 2/26/2017.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    //The Android's default system path of your application database.
    private static String DB_PATH = "/data/data/com.ams.vocabforall/databases/";
    private static String DB_NAME = "vocabulary.sqlite";
    private SQLiteDatabase myDataBase;
    private final Context myContext;

    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     *
     * @param context
     */
    public DataBaseHelper(Context context) {

        super(context, DB_NAME, null, 3);
        this.myContext = context;
    }

    /**
     * Creates a empty database on the system and rewrites it with your own database.
     */
    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();
        if (dbExist) {
            //do nothing - database already exist
        } else {
            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();

            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     *
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase() {

        SQLiteDatabase checkDB = null;

        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        } catch (SQLiteException e) {

            //database does't exist yet.

        }

        if (checkDB != null) {

            checkDB.close();

        }

        return checkDB != null ? true : false;
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     */
    private void copyDataBase() throws IOException {

        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {

        //Open the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {

        if (myDataBase != null)
            myDataBase.close();

        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Add your public helper methods to access and get content from the database.
    // You could return cursors by doing "return myDataBase.query(....)" so it'd be easy
    // to you to create adapters for your views.

    public Cursor getwords() {
        SQLiteDatabase db3 = this.getWritableDatabase();
        Cursor word = db3.rawQuery("SELECT * FROM 'GRE'", null);
        return word;
    }


    public Cursor getmeaning(String x) {
        SQLiteDatabase db3 = this.getWritableDatabase();
        Cursor word = db3.rawQuery("SELECT * FROM 'GRE' WHERE Column1 LIKE '" + x + "%'", null);
        return word;
    }

    public boolean UpdateMastered(String Word, Integer fav) {
        SQLiteDatabase db3 = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("Mastered", fav);
        long result = db3.update("'GRE'", contentValues, "Column1= '" + Word + "'", null);


//        long result =  db3.update( "GRE Vocabulary list",contentValues, "Column1 = '"+Word+"'",null);


        if (result == -1)
            return false;
        else
            return true;
    }


    public Cursor getfav(String fav) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor user = db.rawQuery("select 'Mastered' from 'GRE' where Column1='" + fav + "' ", null);
//       SELECT Mastered FROM 'GRE Vocabulary list' Where Column1= "abate"
        return user;


    }

    public Cursor getfav2(String fav) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor user = db.rawQuery("select * from 'GRE' where Column1='" + fav + "' ", null);
//       SELECT Mastered FROM 'GRE Vocabulary list' Where Column1= "abate"
        return user;


    }

    public Cursor getMastered(int fav) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor user2 = db.rawQuery("select * from 'GRE' where Mastered='" + fav + "' ", null);

        return user2;


    }

    public int getProfilesCount(int fav) {
        String countQuery = "select * from 'GRE' where Mastered='" + fav + "' ";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }

    public int getCount(String x) {
        String countQuery = "SELECT * FROM 'GRE' WHERE Column1 LIKE '" + x + "%'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }

    public Cursor getassorted() {
        SQLiteDatabase db3 = this.getWritableDatabase();
        Cursor word = db3.rawQuery("SELECT * FROM 'Miscellaneous'", null);
        return word;
    }

    public Cursor getrandom() {
        SQLiteDatabase db3 = this.getWritableDatabase();
        Cursor rword = db3.rawQuery("SELECT * FROM 'GRE' ORDER BY RANDOM() LIMIT 4", null);
        return rword;
    }

    public boolean ResetMastered( int a){
        SQLiteDatabase db3 = this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        String s = "GRE";
        contentValues.put("Mastered", a);


        long result =  db3.update(s, contentValues, "Mastered= '" + 1 + "'", null);

        if ( result == -1)
            return false;
        else

            return true;


    }




}




