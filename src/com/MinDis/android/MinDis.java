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

package com.MinDis.android; // make source part of Android package

import android.app.Activity; // import Android Activity classes
import android.net.*;
import android.content.*;
import android.os.Bundle; // required package for Android Activity source
import android.view.*; // handles screen layout and user interaction
import android.widget.*; // required for UI elements
import java.text.*; // required to parse and format data
import java.util.*; // import for calendar
public class MinDis extends Activity
{

	/* The following are variable to hold data for application */
	Spinner month, day, byear;
	EditText rmd;
	EditText balance;
	Button calc;
	Spinner selection;
	ArrayAdapter adapter, madapter, dadapter, yadapter;
    EMess mess = new EMess(this);
	RMD comp = new RMD();
    DBManager db = new DBManager(this);
    DBOP par = new DBOP();
    BCal bcal = new BCal();
	NumberFormat dp = NumberFormat.getInstance(); /* variable to initiate way of parsing input */
	DecimalFormat cf = new DecimalFormat("#,###.##"); /* this object is used to format output */
    SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat human = new SimpleDateFormat("MM/dd/yyyy");

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main); // get Application layout

	month = (Spinner)findViewById(R.id.month);
	madapter = ArrayAdapter.createFromResource(this, R.array.months, android.R.layout.simple_spinner_dropdown_item);

	madapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	month.setAdapter(madapter);

	day = (Spinner)findViewById(R.id.day);
	dadapter = ArrayAdapter.createFromResource(this, R.array.days, android.R.layout.simple_spinner_dropdown_item);
	
	dadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	day.setAdapter(dadapter);

	byear = (Spinner)findViewById(R.id.byear);
	yadapter = ArrayAdapter.createFromResource(this, R.array.years, android.R.layout.simple_spinner_dropdown_item);

	yadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

	byear.setAdapter(yadapter);

	balance = (EditText)findViewById(R.id.amount); // get Textfield
	/* the following two statements creates a combobox */
	selection = (Spinner)findViewById(R.id.spinner);
	adapter = ArrayAdapter.createFromResource(this, R.array.choices_array, android.R.layout.simple_spinner_dropdown_item);
	rmd = (EditText)findViewById(R.id.rmd); // get TextField
	calc = (Button)findViewById(R.id.calculate); // get TextField
	rmd.setEnabled(false); /* make textfield that holds results read-only */

	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // set drop down resource

	selection.setAdapter(adapter); // assign adapter to combobox
        
    }

	public void rmdAction(View v) throws Exception
	{
		try
		{
			// the following holds data inputted by user
			String bday;
			String choice;
			String amount;
			int year;

			/* the following converts input into proper data types */
			amount = balance.getText().toString();

			Number principle = dp.parse(amount);

			// the following converts spinner input into string
			bday = month.getSelectedItem().toString();
			bday += "/";
			bday += day.getSelectedItem().toString();
			bday += "/";
			bday += byear.getSelectedItem().toString();

			// get selection from combobox
			choice = selection.getSelectedItem().toString();
			// convert selection to integer
			year = Integer.parseInt(choice);

			comp.setYear(year); /* pass choice to external class */
			comp.setBDate(bday); /* pass birthday to external class */
			comp.setBalance(principle.doubleValue()); /* pass account balance to external class */

			// get results
			rmd.setText(cf.format(comp.getRMD()));
		}

		catch (java.text.ParseException e)
		{
			/* the following code creates a dialog box that talks about error */
			/* AlertDialog.Builder m = new AlertDialog.Builder(this); // create instance of AlertDialog builder
			m.setTitle(R.string.error_title); // set dialog title
			m.setMessage(R.string.error).setCancelable(true).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() // set message and create button to close dialog box
{
	public void onClick(DialogInterface dialog, int id)
	{
		dialog.cancel();
	}
});
			AlertDialog alert = m.create();
			m.show(); */
            
            // the following creates an AlertDialog via a custom class
            mess.setBuilder(R.string.error_title, R.string.error, R.string.ok);
            mess.getAlert();

		}		

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.app_menu, menu);
		return true;
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
            String bday;
            
            bday = month.getSelectedItem().toString();
            bday += "/";
            bday += day.getSelectedItem().toString();
            bday += "/";
            bday += byear.getSelectedItem().toString();
            
            par.setBirth(bday);
            par.setDistrib(Integer.parseInt(selection.getSelectedItem().toString()));
            par.setBal(balance.getText().toString());
        
            par.setMonthAdapter(month);
            par.setDayAdapter(day);
            par.setYearAdapter(byear);
            par.setDistAdapter(selection);
        
        // AlertDialog.Builder m = new AlertDialog.Builder(this); // create instance of AlertDialog builder
        
		// handle item selection
		 switch (item.getItemId())
		{
            case R.id.save:
                db.open();
                if (db.records() > 0)
                {
                    if (db.updateData(par.getBirth(), par.getBal(), par.getDistrib()) != 0)
                    {
                        mess.setBuilder(R.string.update_success, R.string.update_success_text, R.string.ok);
                        mess.getAlert();
                    }
                    else
                    {
                        mess.setBuilder(R.string.update_failure, R.string.update_failure_text, R.string.ok);
                        mess.getAlert();
                    }
                }
                else
                {
                    if (db.saveData(par.getBirth(), par.getBal(), par.getDistrib()) != 0)
                    {
                        mess.setBuilder(R.string.save_success, R.string.save_success_text, R.string.ok);
                        mess.getAlert();
                    }
                    else
                    {
                        mess.setBuilder(R.string.save_failure, R.string.save_failure_text, R.string.ok);
                        mess.getAlert();
                    }
                }
                db.close();
                return true;
            case R.id.load:
                db.open();
		if(db.records() > 0)
		{
                	bcal.setBirth(db.getBirth());
                	bcal.setCal(bcal.getBirth());
                    
                	par.setSMonth(bcal.getCal().get(Calendar.MONTH) +1);
                	par.setSDay(bcal.getCal().get(Calendar.DATE));
                	par.setSYear(bcal.getCal().get(Calendar.YEAR));
                	par.setSDistrib(db.getYear());
                	month.setSelection(par.getSMonth());
                	day.setSelection(par.getSDay());
                	byear.setSelection(par.getSYear());
                	selection.setSelection(par.getSDistrib());
                	balance.setText(cf.format(db.getBal()));

            mess.setBuilder(R.string.load_success, R.string.load_success_text, R.string.ok);
            mess.getAlert();
		}
		else
        {
            mess.setBuilder(R.string.load_failure, R.string.load_failure_text, R.string.ok);
            mess.getAlert();
        }
                db.close();
                return true;
			case R.id.license:
				Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://dl.dropbox.com/u/332246/LICENSE.txt"));
				startActivity(i);
				return true;
			case R.id.report:
				Intent b = new Intent(Intent.ACTION_VIEW, Uri.parse("http://brycecampbell.me/~bryce/mantis"));
				startActivity(b);
				return true;
			case R.id.contact:
				Intent c = new Intent(Intent.ACTION_SEND);
				c.setType("plain/text");
				c.putExtra(android.content.Intent.EXTRA_EMAIL, "tonyhawk2100@gmail.com");
				startActivity(Intent.createChooser(c, "Contact Developer..."));
				return true;
				
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}
