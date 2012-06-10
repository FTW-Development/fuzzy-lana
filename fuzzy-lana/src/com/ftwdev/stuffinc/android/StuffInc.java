package com.ftwdev.stuffinc.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class StuffInc extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    	Intent login = new Intent(this, UserLogin.class);
    	startActivity(login);
    }

}