package com.example.rotaryapp;

import com.parse.Parse;
import com.parse.ParseObject;

public class Application extends android.app.Application {
  // Debugging switch
  public static final boolean APPDEBUG = false;

  // Debugging tag for the application
  public static final String APPTAG = "AnyWall";

 

  public Application() {
  }

  @Override   
  public void onCreate() {
    super.onCreate();

    ParseObject.registerSubclass(AnywallPost.class);
    Parse.initialize(this, "Kf469e55DPHb8tiKCBJKg6c2YqIn6rMKCQDi3IUd",
        "wvJMRMVF8bDumWCFHBloG9Ihc9JYXctfWv27A85r");
   
  
  }   


}
