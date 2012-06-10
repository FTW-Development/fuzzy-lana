package com.ftwdev.stuffinc.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayLoginCredentials extends Activity {
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		String username = intent.getStringExtra(UserLogin.USERNAME);
		String password = intent.getStringExtra(UserLogin.PASSWORD);
		
		TextView textView = new TextView(this);
		textView.setTextSize(40);
		textView.setText(username);
		textView.append(password);
		
		setContentView(textView);
	}
}
