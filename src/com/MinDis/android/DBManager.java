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

/** The DBManager class specifies methods to send data to and retrieve data from SQLite. */

package com.MinDis.android;
import java.util.*;
import android.content.*;
import android.database.*;
import android.database.sqlite.*;

public class DBManager
{
    Context dbContext;
    SQLiteDatabase db;
    DBHandler handler;
    // static variables
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
    
    public DBManager(Context context)
    {
        dbContext = context;
    }
    
    public DBManager open()
    {
        handler = new DBHandler(dbContext);
        db = handler.getWritableDatabase();
        return this;
    }
    
    public void close()
    {
        handler.close();
    }
    
	// create insert method
	public int saveData(String a, double b, int c)
	{
        int test = 0;
		ContentValues values = new ContentValues();
		values.put(bd, a);
		values.put(ira, b);
		values.put(distrib, c);
        
        try
        {
            // insert statement
            db.insert(TABLE_NAME, null, values);
            test = 1;
        }
        
        catch (Exception ex)
        {
            test = 0;
        }
        db.close();
        return test;
	}

	// create update method
	public int updateData(String a, double b, int c)
	{
        int test = 0;
		ContentValues values = new ContentValues();
        
        try
        {
            if (getBirth().equals(a) != true && getBal() != b && getYear() != c)
            {
                values.put(bd, a);
                values.put(ira, b);
                values.put(distrib, c);
            
                if (db.update(TABLE_NAME, values, key + " = ?", new String[] { Integer.toString(1) }) != 0)
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
                values.put(bd, a);
                values.put(ira, b);
            
                if (db.update(TABLE_NAME, values, key + " = ?", new String[] { Integer.toString(1) }) != 0)
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
                values.put(bd, a);
                values.put(distrib, c);
            
                if (db.update(TABLE_NAME, values, key + " = ?", new String[] { Integer.toString(1) }) != 0)
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
                values.put(ira, b);
                values.put(distrib, c);
            
                if (db.update(TABLE_NAME, values, key + " = ?", new String[] { Integer.toString(1) }) != 0)
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
                values.put(distrib, c);
            
                if (db.update(TABLE_NAME, values, key + " = ?", new String[] { Integer.toString(1) }) != 0)
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
                values.put(ira, b);
            
                if (db.update(TABLE_NAME, values, key + " = ?", new String[] { Integer.toString(1) }) != 0)
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
                values.put(bd, a);
            
                if (db.update(TABLE_NAME, values, key + " = ?", new String[] { Integer.toString(1) }) != 0)
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
        }
        
        catch (Exception ex)
        {
            
        }
		return test;
	}

	// the following methods retrieve data
	public String getBirth()
	{
        String[] columns = new String[] { bd };
        Cursor cursor;
        
        cursor = db.query(TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null)
        cursor.moveToFirst();
        birth = cursor.getString(0);
        return birth;
        
	}

	public double getBal()
	{
        String[] columns = new String[] { ira };
        Cursor cursor;
        
        cursor = db.query(TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        bal = cursor.getDouble(0);
        
        return bal;
	}

	public int getYear()
	{
        String[] columns = new String[] { distrib };
        Cursor cursor;
        
        cursor = db.query(TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        year = cursor.getInt(0);
        
        return year;
	}

	// the following method gets number of records
	public int records()
	{
        String rowCount = "SELECT COUNT(*) FROM " + TABLE_NAME;
        
        Cursor cursor;
        
        try
        {
            cursor = db.rawQuery(rowCount, null);
            if (cursor != null)
                cursor.moveToFirst();
            records = cursor.getInt(0);
        }
        
        catch (Exception ex)
        {
            
        }
        
        return records;
	}

}