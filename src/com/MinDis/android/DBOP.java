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

/** The DBOP class specifies method to pass input into and out of SQLite. */

package com.MinDis.android;
import java.util.*;
import java.text.*;
import android.widget.*;

public class DBOP
{
	ArrayAdapter month, day, year, dist;
	Number principle;
	double bal;
	String birth;
	int distrib, smonth, sday, syear, sdistrib;
	SimpleDateFormat hf = new SimpleDateFormat("MM/dd/yyyy");
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM--dd");
	// DecimalFormat cf = new DecimalFormat("#,###.##");
	NumberFormat dp = NumberFormat.getInstance();
	Date human, sql;

	/* the following methods take spinners and retrieve ArrayAdapters */
	public void setMonthAdapter(Spinner a)
	{
		month = (ArrayAdapter)a.getAdapter();
	}

	public void setDayAdapter(Spinner a)
	{
		day = (ArrayAdapter)a.getAdapter();
	}

	public void setYearAdapter(Spinner a)
	{
		year = (ArrayAdapter)a.getAdapter();
	}

	public void setDistAdapter(Spinner a)
	{
		dist = (ArrayAdapter)a.getAdapter();
	}

	/* the following method set variables in the class */
	public void setBirth(String a)
	{
		try
		{
			human = hf.parse(a);
			birth = df.format(human);
		}
		catch (java.text.ParseException e)
		{
		}
	}

	public void setDistrib(int a)
	{
			distrib = a;
	}

	public void setBal(String a)
	{
		try
		{
			principle = dp.parse(a);
			bal = principle.doubleValue();
		}
		catch (java.text.ParseException e)
		{
		}
	}

	/* the following methods set variables to be used with Spinners */
	public void setSMonth(int m)
	{
		smonth = getMonthAdapter().getPosition(m);
	}

	public void setSDay(int d)
	{
		sday = getDayAdapter().getPosition(d);
	}

	public void setSYear(int y)
	{
		syear = getYearAdapter().getPosition(y);
	}

	public void setSDistrib(int e)
	{
		sdistrib = getDistAdapter().getPosition(e);
	}

	/* the following returns variable values previously set */
	public String getBirth()
	{
		return birth;
	}

	public double getBal()
	{
		return bal;
	}

	public int getDistrib()
	{
		return distrib;
	}

	public ArrayAdapter getMonthAdapter()
	{
		return month;
	}

	public ArrayAdapter getDayAdapter()
	{
		return day;
	}

	public ArrayAdapter getYearAdapter()
	{
		return year;
	}

	public ArrayAdapter getDistAdapter()
	{
		return dist;
	}

	public int getSMonth()
	{
		return smonth;
	}

	public int getSDay()
	{
		return sday;
	}

	public int getSYear()
	{
		return syear;
	}

	public int getSDistrib()
	{
		return sdistrib;
	}
}