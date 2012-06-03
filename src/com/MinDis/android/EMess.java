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

package com.MinDis.android;
import android.content.*; // import for DialogInterface and Context
import android.app.AlertDialog; // import AlertDialog

/* the EMess class creates AlertDialogs to be displayed in App */
public class EMess
{
	Context a; /* variable to hold context, as AlertDialog.Builder needs it */
	AlertDialog alert; // AlertDialog variable
	AlertDialog.Builder m; // variable used in creating Alert Dialog

	public EMess(Context context)
	{
		a = context;
	}

	public void setBuilder(int title, int mess, int ok)
	{
		m = new AlertDialog.Builder(a);
		m.setTitle(title);
		m.setMessage(mess).setCancelable(true).setPositiveButton(ok, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id)
			{
				dialog.cancel();
			}
		});
	}

	public void getAlert()
	{
		alert = m.create();
		m.show();
	}
}