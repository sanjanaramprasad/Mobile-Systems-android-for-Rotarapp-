package com.example.rotaryapp;


import java.util.ArrayList;
import java.util.List;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class EventList extends Activity{
	   
	ListView mListView;
	ArrayList<String> mListData;
	EditText et;
	TextView mTV;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patientlist);
        
        CalltoFetchparseEvents();         
        mListData = new ArrayList<String>();
        mListView = (ListView) findViewById(R.id.list);
              
       
		
	}
	
	//@SuppressWarnings("unchecked")
	public void CalltoFetchparseEvents(){
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Events");
		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> objects, ParseException e) {
				// TODO Auto-generated method stub
				
				Toast.makeText(getApplicationContext(), "Fetching done", Toast.LENGTH_SHORT).show();
				String Combined="";
				
				for(ParseObject EventsList:objects){
					
						if(MainActivity.mUserType.equalsIgnoreCase(EventsList.getString("Type"))){
							Combined = Combined+"\nName: "+ EventsList.getString("name")+" Date: "+EventsList.getString("date")
						    		+" Type: "+EventsList.getString("Type")+" Description: "+EventsList.getString("description");
						}
					    
				}
				mTV = (TextView) findViewById(R.id.tv);
				mTV.setText(Combined);  
				   
				   
		        if (e == null) {   
		            Log.d("score", "Retrieved " + objects.size() + " scores");
		        } else {
		            Log.d("score", "Error: " + e.getMessage());
		        }   
		    
		        
				
			}
		});
		
		
		
	}
	
}
