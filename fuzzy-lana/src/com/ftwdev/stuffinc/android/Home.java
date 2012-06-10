package com.ftwdev.stuffinc.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home extends Activity {
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		//setBackgroundDrawable(R.drawable.title_background);
	}
	
	public void battle(View view){
		Intent battle = new Intent(this, Battle.class);
		startActivity(battle);
	}
	
	public void gallery(View view){
		Intent gallery = new Intent(this, Gallery.class);
		startActivity(gallery);
	}
}
