package com.example.rotaryapp;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

    
import android.app.Activity;
import android.content.Intent;   
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
//import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {

	static String mUserType;
	
	EditText mUsername,mPassword;
	Button mSubmit;   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	
		mUsername = (EditText) findViewById(R.id.editText1);
		mPassword = (EditText) findViewById(R.id.editText2);
		
		mSubmit = (Button) findViewById(R.id.button1);
		
		mSubmit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
			
				String username = mUsername.getText().toString();
				String password = mPassword.getText().toString();
				
				CallLoginParse(username, password);
				}           
		});
	}
	
	//Call to the server for validation
	public void CallLoginParse(String username,String password){
		ParseUser.logInInBackground(username, password, new LogInCallback() {
		      @Override
		      public void done(ParseUser user, ParseException e) {
		       
		        if (e != null) {
		          // Show the error message
		          Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
		        } else {    
		        	
		        	  String mRole = user.getString("role");
		          // Start an intent for the dispatch activity
		 	      if(mRole.equalsIgnoreCase("org"))
		        	  { Toast.makeText(getApplicationContext(), "Welcome",Toast.LENGTH_SHORT).show();
			        	  
		        		  mUserType = user.getString("type");   
		        		  Intent intent = new Intent(MainActivity.this,Organisationrequest.class);
						  startActivity(intent);
		        	  }
		                else if(mRole.equalsIgnoreCase("vul")){
		        		  Toast.makeText(getApplicationContext(), "Hello Volunteer!", Toast.LENGTH_SHORT).show();
			        	  
		        		  Intent intent = new Intent(MainActivity.this,AddEvent.class);
						  startActivity(intent);
		        	  }else{
		        		  Toast.makeText(getApplicationContext(), "User role not defined", Toast.LENGTH_LONG).show();
		        	  }
			        
			          
		        }      
		      }     
		    });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
