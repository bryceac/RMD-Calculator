package com.MinDis.android.xml;
import org.simpleframework.xml.*;

// the data class holds specs for XML file that should be read or written
@Root(name="rmd")
public class Data 
{
	// specify variables and annotate with element marker
	@Element
	String birth, balance, year;
	
	// create setter and getter methods
	public void setBirth(String b)
	{
		birth = b;
	}
	
	public String getBirth()
	{
		return birth;
	}
	
	public void setBalance(String b)
	{
		balance = b;
	}
	
	public String getBalance()
	{
		return balance;
	}
	
	public void setYear(String y)
	{
		year = y;
	}
	
	public String getYear()
	{
		return year;
	}
	
	// constructor to quickly create Data object
	public Data(String b, String bal, String y)
	{
		setBirth(b);
		setBalance(bal);
		setYear(y);
	}
	
	// default constructor
	public Data()
	{
		
	}
}
