package com.ftwdev.stuffinc.android;

import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.SubMenu;
import android.widget.ImageView;
import android.widget.TextView;

import com.ftwdev.stuffinc.core.Move;
import com.ftwdev.stuffinc.core.Stuff;
import com.ftwdev.stuffinc.core.StuffFactory;
import com.ftwdev.stuffinc.core.Thing;

public class Battle extends Activity {
	public String[] attacks = new String[4];
	public String[] team = new String[5];
	
	public Thing leftguy;
	public Thing rightguy;
		
	public void onCreate(Bundle savedInstanceState){
		AssetManager am = getBaseContext().getAssets();
		try {
			Move.initMoveList(am.open("Moves.yml"));
			Stuff.initStuffList(am.open("Stuff.yml"));
		} catch (IOException e) {
			System.out.println("poopy");
			e.printStackTrace();
		}
		
		leftguy = StuffFactory.getWild(1, 1);
		//rightguy = StuffFactory.getWild(1, 1);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.battle);
    	//MenuInflater inflater = getMenuInflater();
    	//generates a Menu from a menu resource file
    	//R.menu.main_menu represents the ID of the XML resource file
    	//inflater.inflate(R.menu.battlemenu, menu);
		setAttacks();
		setTeam();
		
		//Dynamic page setup left guy
		ImageView left_image = (ImageView) findViewById(R.id.leftGuyImage);
		TextView left_name = (TextView) findViewById(R.id.leftGuyName);
		TextView left_level = (TextView) findViewById(R.id.leftGuyLevel);
		TextView left_health = (TextView) findViewById(R.id.leftGuyHealth);
		
		left_image.setImageResource(R.drawable.dual_daisies1);
		
		//Dynamic page setup right guy
		ImageView right_image = (ImageView) findViewById(R.id.rightGuyImage);
		TextView right_name = (TextView) findViewById(R.id.rightGuyName);
		TextView right_level = (TextView) findViewById(R.id.rightGuyLevel);
		TextView right_health = (TextView) findViewById(R.id.rightGuyHealth);
		
		right_image.setImageResource(R.drawable.fiery_barbecue_chips2);		
	}
	
	public boolean onPrepareOptionsMenu(Menu menu){
		SubMenu fight_sub_menu = menu.addSubMenu("Fight");
		for (int i=0; i < attacks.length; ++i ){
			fight_sub_menu.add(attacks[i]);
		}
		SubMenu mod_sub_menu = menu.addSubMenu("Mods");
		SubMenu swap_sub_menu = menu.addSubMenu("Swap");
		for (int i=0; i < team.length; ++i){
			swap_sub_menu.add(team[i]);
		}
		SubMenu fail_sub_menu = menu.addSubMenu("Fail");
		return true;
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {

	    //MenuInflater inflater = getMenuInflater();
	    //inflater.inflate(R.menu.battlemenu, menu);
	    return true;
	}
	
	@Override
	public void onAttachedToWindow() {
	    super.onAttachedToWindow();
	    openOptionsMenu();
	}
	
	public void setTeam(){
		team[0] = "Guy1";
		team[1] = "Guy2";
		team[2] = "Guy3";
		team[3] = "Guy4";
		team[4] = "Guy5";
	}
	
	public void setAttacks(){
		attacks[0] = "Attack1";
		attacks[1] = "Attack2";
		attacks[2] = "Attack3";
		attacks[3] = "Attack4";
	}

/*	public void showPopup(View v) {
	    PopupMenu popup = new PopupMenu(this, v);
	    MenuInflater inflater = popup.getMenuInflater();
	    inflater.inflate(R.menu.battlemenu, popup.getMenu());
	    popup.show();
	} */
}
