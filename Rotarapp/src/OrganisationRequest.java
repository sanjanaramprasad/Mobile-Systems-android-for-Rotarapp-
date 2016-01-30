package com.example.rotaryapp;

//import java.util.List;

//import com.parse.FindCallback;
//import com.parse.ParseException;
import com.parse.ParseObject;
//import com.parse.ParseQuery;


import android.app.Activity;
//import android.content.ContentValues;
import android.content.Intent;
//import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
//import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
//import android.widget.Toast;
//import android.widget.Toast;



public class Organisationrequest extends Activity {

	EditText mRequirements;
	Button mSUbmit,mView;
	   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.requests);
		
		mRequirements = (EditText) findViewById(R.id.editText1);
		mSUbmit = (Button) findViewById(R.id.button1);
		mView = (Button) findViewById(R.id.button2);
		
		mSUbmit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				SavetoParse();
				     
				   
			}
		});   
		mView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Organisationrequest.this,EventList.class);
				startActivity(intent);
			}
		});
		
		
	}
	
	
	public void SavetoParse(){      
		
		ParseObject Events = new ParseObject("Requirements");
		
		Events.put("requirements", mRequirements.getText().toString());
		Events.saveInBackground();
	//	Toast.makeText(getApplicationContext(), "Request submitted", Toast.LENGTH_LONG).show();
	  
		    
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
