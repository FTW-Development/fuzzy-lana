package com.ftwdev.stuffinc.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.SubMenu;

public class Battle extends Activity {
	public String[] attacks = new String[4];
	public String[] team = new String[5];
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.battle);
    	//MenuInflater inflater = getMenuInflater();
    	//generates a Menu from a menu resource file
    	//R.menu.main_menu represents the ID of the XML resource file
    	//inflater.inflate(R.menu.battlemenu, menu);
		setAttacks();
		setTeam();
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
