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

/** The RMD class specifies methods for RMD calculation and uses an *external class for setting a date, in order to make calculations accurate
*/

package com.MinDis.android;

import java.util.*; // required to use Date class
import java.text.*; // use to format and parse date

public class RMD
{
	double balance; // variable to hold account balance
	double rmd; // variable to hold result
	long age; // variable to hold age
	String bdate; // variable to hold birthday
	SimpleDateFormat sd = new SimpleDateFormat("MM/dd/yyyy"); /* variable used to convert input to date */
	calcDate initial = new calcDate(); /* create new object from external class */
	int year; // variable to hold year

	/** the setBalance method is used to set an account balance for RMD calculations */
	public void setBalance(double i)
	{
		balance = i;
	}

	/** the getBalance method returns the value of the balance variable */
	public double getBalance()
	{
		return balance;
	}

	/** the setBDate method assigns a date in string format to the bdate variable */
	public void setBDate(String h)
	{
		bdate = h;
	}

	/** the setYear method is used to assign a value to the year variable */
	public void setYear(int c)
	{
		year = c;
	}

	/** the getYear method returns the value of the year variable */
	public int getYear()
	{
		return year;
	}

	/** the getAge method returns a person's age from a certain date by converting their birthdate into a Date object and calculating the difference*/
	public long getAge() throws Exception
	{
		
		Date birthd = sd.parse(bdate); /* convert bdate value to Date object */
		long cur; // variable to hold set date in ms
		long birth; // variable to hold birth date in ms
		long diff; // variable to hold difference

		initial.setYear(getYear()); // set year
		initial.setMonth(12); // set month
		initial.setDay(31); // set day

		cur = initial.getStart().getTime(); /* assign start date in milliseconds to cur variable */
		birth = birthd.getTime(); /* assign birth date in milliseconds to birth */
		diff = cur - birth; // calculate
		age = (diff/(24*60*60*1000))/365; /* convert difference in readable gap in years */
		return age; // return a person's age
	}

	/** the getRMD method calculates the Required Minimum Distribution for a person ages 70 and up, based on IRS data that is current as of 4/05/2011 */
	public double getRMD() throws Exception
	{

		switch ((int)getAge())
		{
			case 70: rmd = balance/27.4; break;

			case 71: rmd = balance/26.5; break;

			case 72: rmd = balance/25.6; break;

			case 73: rmd = balance/24.7; break;

			case 74: rmd = balance/23.8; break;
			case 75: rmd = balance/22.9; break;
			case 76: rmd = balance/22.0; break;
			case 77: rmd = balance/21.2; break;
			case 78: rmd = balance/20.3; break;
			case 79: rmd = balance/19.5; break;
			case 80: rmd = balance/18.7; break;
			case 81: rmd = balance/17.9; break;
			case 82: rmd = balance/17.1; break;
			case 83: rmd = balance/16.3; break;
			case 84: rmd = balance/15.5; break;
			case 85: rmd = balance/14.8; break;
			case 86: rmd = balance/14.1; break;
			case 87: rmd = balance/13.4; break;
			case 88: rmd = balance/12.7; break;
			case 89: rmd = balance/12.0; break;
			case 90: rmd = balance/11.4; break;
			case 91: rmd = balance/10.8; break;
			case 92: rmd = balance/10.2; break;
			case 93: rmd = balance/9.6; break;
			case 94: rmd = balance/9.1; break;
			case 95: rmd = balance/8.6; break;
			case 96: rmd = balance/8.1; break;
			case 97: rmd = balance/7.6; break;
			case 98: rmd = balance/7.1; break;
			case 99: rmd = balance/6.7; break;
			case 100: rmd = balance/6.3; break;
			case 101: rmd = balance/5.9; break;
			case 102: rmd = balance/5.5; break;
			case 103: rmd = balance/5.2; break;
			case 104: rmd = balance/4.9; break;
			case 105: rmd = balance/4.5; break;
			case 106: rmd = balance/4.2; break;
			case 107: rmd = balance/3.9; break;
			case 108: rmd = balance/3.7; break;
			case 109: rmd = balance/3.4; break;
			case 110: rmd = balance/3.1; break;
			case 111: rmd = balance/2.9; break;
			case 112: rmd = balance/2.6; break;
			case 113: rmd = balance/2.4; break;
			case 114: rmd = balance/2.1; break;
		
			default:
			if((int)getAge() >= 115)
			{ 
				rmd = balance/1.9;
			}
			else
			{
				rmd = 0.0; 
			}
			break;
		}
		return rmd;
	}
}