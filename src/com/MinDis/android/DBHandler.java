
/*
*Copyright (c) 2012 Bryce Campbell

*Permission is hereby granted, free of charge, to any person obtaining a *copy of this software and associated documentation files (the "Software"), *to deal in the Software without restriction, including without limitation *the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

*The above copyright notice and this permission notice shall be included in
*all copies or substantial portions of the Software.

*THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
*IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
*FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL *THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
*LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING *FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER *DEALINGS IN THE SOFTWARE.
*/

/** The DBHandler class specifies methods for working with SQLite. */

package com.MinDis.android;
import java.util.*;
import android.content.*;
import android.database.*;
import android.database.sqlite.*;
import android.database.sqlite.*;

public class DBHandler extends SQLiteOpenHelper
{
	// static variables
	private static final int DATABASE_VERSION = 1; // version
	private static final String DATABASE_NAME = "mrd.db"; /* database name */
	private static final String TABLE_NAME = "rmd"; // db name

	// field names
	private static final String key = "id";
	private static final String bd = "birth";
	private static final String ira = "bal";
	private static final String distrib = "year";
    
    // variables to hold data
    String birth;
    double bal;
    int year, records;

	public DBHandler(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// method to create table
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + key + "INTEGER PRIMARY KEY AUTOINCREMENT," + bd + "TEXT," + ira + "REAL," + distrib + "INTEGER)";

		db.execSQL(CREATE_TABLE);
	}

	// method to upgrade database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		// drop table if exists
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

		// recreate table
		onCreate(db);
	}

	// create insert method
	public void saveData(String a, double b, int c)
	{
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(bd, a);
		values.put(ira, b);
		values.put(distrib, c);

		// insert statement
		db.insert(TABLE_NAME, null, values);
        db.close();
	}

	// create update method
	public int updateData(String a, double b, int c)
	{
        int test;
		SQLiteDatabase db = this.getWritableDatabase();

		String bUpdate, iUpdate, yUpdate, bIUpdate, bYUpdate, iYUpdate, aUpdate;
        bUpdate = "UPDATE rmd SET ";
        bUpdate += bd;
        bUpdate += " = \"";
        bUpdate += a;
        bUpdate += "\" WHERE id = 1";
        iUpdate = "UPDATE rmd SET ";
        iUpdate += ira;
        iUpdate += " = "; 
        iUpdate += b;
        iUpdate += " WHERE id = 1";
        yUpdate = "UPDATE rmd SET "; 
        yUpdate += distrib;
        yUpdate += " = ";
        yUpdate += c;
        yUpdate += " WHERE id = 1";
        bIUpdate = "UPDATE rmd SET "; 
        bIUpdate += bd;
        bIUpdate += " = \""; 
        bIUpdate += a;
        bIUpdate += "\", "; 
        bIUpdate += ira;
        bIUpdate += " = "; 
        bIUpdate += b;
        bIUpdate += " WHERE id = 1";
        bYUpdate = "UPDATE rmd SET ";
        bYUpdate += bd;
        bYUpdate += " = \"";
        bYUpdate += a;
        bYUpdate += "\", ";
        bYUpdate += distrib;
        bYUpdate += " = ";
        bYUpdate += c;
        bYUpdate += " WHERE id = 1";
        iYUpdate = "UPDATE rmd SET ";
        iYUpdate += ira + " = ";
        iYUpdate += b;
        iYUpdate += ", ";
        iYUpdate += distrib;
        iYUpdate += " = ";
        iYUpdate += c;
        iYUpdate += " WHERE id = 1";
        aUpdate = "UPDATE rmd SET "; 
        aUpdate += bd;
        aUpdate += " = \"";
        aUpdate += a;
        aUpdate += "\", ";
        aUpdate += ira;
        aUpdate += " = ";
        aUpdate += b;
        aUpdate += ", ";
        aUpdate += distrib;
        aUpdate += " = ";
        aUpdate += c;
        aUpdate += " WHERE id = 1";
		if (getBirth().equals(a) != true && getBal() != b && getYear() != c)
		{
			if (db.rawQuery(aUpdate, null) != null)
            {
                test = 1;
            }
            else
            {
                test = 0;
            }
		}
		else if (getBirth().equals(a) != true && getBal() != b)
		{
			if (db.rawQuery(bIUpdate, null) != null)
            {
                test = 1;
            }
            else
            {
                test = 0;
            }
		}
		else if (getBirth().equals(a) != true && getYear() != c)
		{
			if (db.rawQuery(bYUpdate, null) != null)
            {
                test = 1;
            }
            else
            {
                test = 0;
            }
		}
		else if (getBal() != b && getYear() != c)
		{
			if (db.rawQuery(iYUpdate, null) != null)
            {
                test = 1;
            }
            else
            {
                test = 0;
            }
		}
		else if (getYear() != c)
		{
			
			if (db.rawQuery(yUpdate, null) != null)
            {
                test = 1;
            }
            else
            {
                test = 0;
            }
		}
		else if (getBal() != b)
		{
			if (db.rawQuery(iUpdate, null) != null)
            {
                test = 1;
            }
            else
            {
                test = 0;
            }
		}
		else if (getBirth().equals(a) != true)
		{
			if (db.rawQuery(bUpdate, null) != null)
            {
                test = 1;
            }
            else
            {
                test = 0;
            }
		}
		else
        {
            test = 0;
        }
		return test;
	}

	// the following methods retrieve data
	public String getBirth()
	{
        String birthQuery = "SELECT date(" + bd + ") FROM rmd LIMIT 1";
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.rawQuery(birthQuery, null);
        if (cursor != null)
            cursor.moveToFirst();
        birth = cursor.getString(0);
        return birth;
        
	}

	public double getBal() throws SQLException
	{
        String balQuery = "SELECT " + ira + " FROM rmd LIMIT 1";
		SQLiteDatabase db = this.getReadableDatabase();
        
		Cursor cursor = db.rawQuery(balQuery, null);
        if (cursor != null)
            cursor.moveToFirst();
        bal = cursor.getDouble(0);
        return bal;
	}

	public int getYear() throws SQLException
	{
        String distQuery = "SELECT " + distrib + " FROM rmd LIMIT 1";
		SQLiteDatabase db = this.getReadableDatabase();
        
		Cursor cursor = db.rawQuery(distQuery, null);
        if (cursor != null)
            cursor.moveToFirst();
            year = cursor.getInt(0);
            return year;
	}

	// the following method gets number of records
	public int records() throws SQLException
	{
        String rowCount = "SELECT * FROM " + TABLE_NAME;
		SQLiteDatabase db = this.getReadableDatabase();
        
		Cursor cursor = db.rawQuery(rowCount, null);
        cursor.close();
        return records = cursor.getCount();
	}
}