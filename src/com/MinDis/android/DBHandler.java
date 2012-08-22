
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

	public DBHandler(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// method to create table
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + key + " INTEGER PRIMARY KEY AUTOINCREMENT, " + bd + " TEXT, " + ira + " REAL, " + distrib + " INTEGER);";

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

}