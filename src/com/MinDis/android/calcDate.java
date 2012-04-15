/*
*Copyright (c) 2011 Bryce Campbell

*Permission is hereby granted, free of charge, to any person obtaining a *copy of this software and associated documentation files (the "Software"), *to deal in the Software without restriction, including without limitation *the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

*The above copyright notice and this permission notice shall be included in
*all copies or substantial portions of the Software.

*THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
*IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
*FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL *THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
*LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING *FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER *DEALINGS IN THE SOFTWARE.
*/

/* The prevDate class uses the current time to calculate the previous year and set a Date using that particular year */

package com.MinDis.android;

import java.util.*;

public class calcDate
{
	Date start;
	int year, month, day, prev; // create variables to hold date info
	Calendar cal;
	/** The setYear method accepts a year and assign the year to a variable */

	public void setYear(int y)
	{
		year = y;
	}

	/** The setMonth method accepts an integer and assigns a different integer, corresponding to a switch, to set a month */

	public void setMonth(int m)
	{

		/* the following switch statement specifies month according to how the Calendar class sees the month */
		switch(m)
		{
			case 1: month = 0; break; // Jan.
			case 2: month = 1; break; // Feb.
			case 3: month = 2; break; // Mar.
			case 4: month = 3; break; // Apr.
			case 5: month = 4; break; // May
			case 6: month = 5; break; // Jun.
			case 7: month = 6; break; // Jul.
			case 8: month = 7; break; // Aug.
			case 9: month = 8; break; // Sep.
			case 10: month = 9; break; // Oct.
			case 11: month = 10; break; // Nov.
			case 12: month = 11; break; // Dec.
		}
	}

	/** The setDay method accepts an integer for day and assigns it to a variable */

	public void setDay(int d)
	{
		day = d;
	}

	/** The getMonth method returns the value of the month variable */

	public int getMonth()
	{
		return month;
	}

	/** The getDay method returns the value of the day variable */

	public int getDay()
	{
		return day;
	}

	/** The getYear method returns the value of the year variable */

	public int getYear()
	{
		return year;
	}

	/** The getStart method returns a certain date with the year given */

	public Date getStart()
	{

		cal = Calendar.getInstance();

		cal.clear();

		cal.set(Calendar.YEAR, getYear());
		cal.set(Calendar.MONTH, getMonth());
		cal.set(Calendar.DATE, getDay());

		start = cal.getTime(); // create date object

		return start; // return date object
	}
}