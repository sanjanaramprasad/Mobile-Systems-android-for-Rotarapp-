package com.example.rotaryapp;

import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class AddEvent extends Activity{
	
	String mTypeVal;
	EditText mDate,mEventdesctips,mEventname;
	Spinner mType;
	Button mAddEvent;
	String[] mStringData = {"Blood","School","Orphanage","Old Age"};    
	String mDateStr,mDesciption;
	TextView mTV;
	private int year;
	private int month;
	private int day;
	static final int DATE_DIALOG_ID = 100;
	Button mSelectdate;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addevent);
		
		CalltoFetchparseEvents();
		mDate = (EditText) findViewById(R.id.editText1);
		mEventdesctips = (EditText) findViewById(R.id.editText2);
		mEventname = (EditText) findViewById(R.id.editText4);
		
		mType = (Spinner) findViewById(R.id.spinner1);
		mAddEvent = (Button) findViewById(R.id.button1);
		
		addButtonListener();
		
		//StartServer();
		ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,mStringData);
		aa.setDropDownViewResource(android.R.layout.simple_spinner_item);
		
		mType.setAdapter(aa);
		
		mType.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				mTypeVal = mStringData[arg2];
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		mAddEvent.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				mDateStr = mDate.getText().toString();
				mDesciption = mEventdesctips.getText().toString();
				
				SavetoParse();
				
				
			}
		});
		
	}
	   
	//@SuppressWarnings("unchecked")   
	public void CalltoFetchparseEvents(){
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Requirements");
		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> objects, ParseException e) {
				// TODO Auto-generated method stub
				
				//Toast.makeText(getApplicationContext(), "fetching done", 600000).show();
				String Combined="";
				for(ParseObject EventsList:objects){
					    Combined = Combined+"\n"+ EventsList.getString("requirements");
				}
				mTV = (TextView) findViewById(R.id.textview);
				mTV.setText(Combined);  
				      
				     
		        if (e == null) {   
		            Log.d("score", "Retrieved " + objects.size() + " scores");
		        } else {
		            Log.d("score", "Error: " + e.getMessage());
		        }
		    
		        
				
			}
		});
	}
		

	public void addButtonListener() {

		mSelectdate = (Button) findViewById(R.id.date);

		mSelectdate.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {

				showDialog(DATE_DIALOG_ID);

			}

		});

	}  
	@Override
	protected Dialog onCreateDialog(int id) {

		switch (id) {
		case DATE_DIALOG_ID:
		   // set date picker as current date
			final Calendar calendar = Calendar.getInstance();

			year = calendar.get(Calendar.YEAR);
			month = calendar.get(Calendar.MONTH);
			day = calendar.get(Calendar.DAY_OF_MONTH);

			
		   return new DatePickerDialog(this, datePickerListener, year, month,day);
		}
		return null;
	}

	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,int selectedMonth, int selectedDay) {
			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;
			
			
			// set selected date into Text View
			mSelectdate.setText(new StringBuilder().append(month + 1)
			   .append("-").append(day).append("-").append(year).append(" "));

		
		}   
	};      
	
	public void SavetoParse(){   
		
		ParseObject Events = new ParseObject("Events");
		Events.put("date", ""+year+"-"+month+"-"+day);
		Events.put("name", mEventname.getText().toString());   
		Events.put("description", mDesciption);
		Events.put("Type", mTypeVal);
		Events.saveInBackground();
	//	Toast.makeText(getApplicationContext(), "Event added!", Toast.LENGTH_SHORT).show();
	  }
		    
	}
	
	
		    	
			
	

