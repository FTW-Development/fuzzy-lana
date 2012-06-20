package com.ftwdev.stuffinc.android;

import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ftwdev.stuffinc.core.Move;
import com.ftwdev.stuffinc.core.Stuff;
import com.ftwdev.stuffinc.core.StuffFactory;
import com.ftwdev.stuffinc.core.Thing;

public class Battle extends Activity {
	public String[] attacks = new String[4];
	public String[] team = new String[5];
	
	private Thing leftGuy;
	private Thing rightGuy;
	private com.ftwdev.stuffinc.core.Battle battle;

		
	public void onCreate(Bundle savedInstanceState){
		AssetManager am = getBaseContext().getAssets();
		try {
			Move.initMoveList(am.open("Moves.yml"));
			Stuff.initStuffList(am.open("Stuff.yml"));
		} catch (IOException e) {
			System.out.println("Failed to open yml files");
			e.printStackTrace();
		}
		
		leftGuy = StuffFactory.getWild(1, 20);
		rightGuy = StuffFactory.getWild(1, 20);
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.battle);
		
		battle = new com.ftwdev.stuffinc.core.Battle(leftGuy, rightGuy);
		
		findViewById(R.id.leftGuyStats).getBackground().setAlpha(150);
		findViewById(R.id.rightGuyStats).getBackground().setAlpha(150);

		
		//TEMPORARY
		fixCurrentHealth();
		
		displayStats();
		displayAttacks();
	}

	private void fixCurrentHealth() {
		leftGuy.setCurrentHealth(leftGuy.getHealth());
		rightGuy.setCurrentHealth(rightGuy.getHealth()); 
		
	}

	private void displayStats(){
		displayLeftGuyStats(leftGuy);
		displayRightGuyStats(rightGuy);
	}
	
	private void displayAttacks() {
		ArrayList<Move> moves = leftGuy.getMoveSet();
		ViewGroup battle_view = (ViewGroup) findViewById(R.id.battleMoveSection);
		LinearLayout.LayoutParams lay = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
		lay.weight = 5;
		for (int i=0; i < moves.size(); ++i ){
			Button button = new Button(this);
			button.setLayoutParams(lay);
			button.setText(moves.get(i).getName());
			button.setTag(i);
			button.getBackground().setAlpha(200);
			button.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					Button b = (Button) v;
					int buttonNum = (Integer) b.getTag();
					Move leftAttack = leftGuy.getMoveSet().get(buttonNum);
					Move rightAttack = rightGuy.getMoveSet().get(0);
					battle.round(leftAttack, rightAttack);
					if (battle.isComplete()){
						TextView battleTextPopup = (TextView) findViewById(R.id.battleTextPopup);
						battleTextPopup.setText("Battle Over!");
						battleTextPopup.setVisibility(TextView.VISIBLE);
					}
					displayStats();
				}
            });
			battle_view.addView(button);
		}
	}
	
	private void displayLeftGuyStats(Thing leftGuy){
		//Dynamic page setup left guy
		ImageView left_image = (ImageView) findViewById(R.id.leftGuyImage);
		TextView left_name = (TextView) findViewById(R.id.leftGuyName);
		TextView left_level = (TextView) findViewById(R.id.leftGuyLevel);
		TextView left_health = (TextView) findViewById(R.id.leftGuyHealth);
			
		left_image.setImageResource(getImageId(leftGuy));
		left_name.setText("Name: " + leftGuy.getName());
		left_level.setText("Level: " + leftGuy.getLevel());
		left_health.setText("Health: " + leftGuy.getCurrentHealth() + " / " + leftGuy.getHealth());
	}
	
	private void displayRightGuyStats(Thing rightGuy){
		//Dynamic page setup right guy
		ImageView right_image = (ImageView) findViewById(R.id.rightGuyImage);
		TextView right_name = (TextView) findViewById(R.id.rightGuyName);
		TextView right_level = (TextView) findViewById(R.id.rightGuyLevel);
		TextView right_health = (TextView) findViewById(R.id.rightGuyHealth);
		
		right_image.setImageResource(getImageId(rightGuy));	
		right_name.setText("Name: " + rightGuy.getName());
		right_level.setText("Level: " + rightGuy.getLevel());
		right_health.setText("Health: " + rightGuy.getCurrentHealth() + " / " + rightGuy.getHealth());
	}
	
	private int getImageId(Thing thing){
		String formattedName = thing.getName().toLowerCase().replaceAll(" ", "_").replaceAll("'", "");
		if (thing == leftGuy){
			formattedName += "1";
		} else {
			formattedName += "2";
		}
		return getResources().getIdentifier(formattedName, "drawable", this.getPackageName());
	}
	
	/*
	public boolean onPrepareOptionsMenu(Menu menu){
		ArrayList<Move> moves = leftGuy.getMoveSet();

		SubMenu fight_sub_menu = menu.addSubMenu("Fight");
		for (int i=0; i < moves.size(); ++i ){
			fight_sub_menu.add(moves.get(i).getName());
		}
		SubMenu mod_sub_menu = menu.addSubMenu("Mods");
		SubMenu swap_sub_menu = menu.addSubMenu("Swap");
		SubMenu fail_sub_menu = menu.addSubMenu("Fail");
		return true;
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {

	    //MenuInflater inflater = getMenuInflater();
	    //inflater.inflate(R.menu.battlemenu, menu);
	    return true;
	}
	*/
	
	@Override
	public void onAttachedToWindow() {
	    super.onAttachedToWindow();
	    openOptionsMenu();
	}
	
	public void battleLoop(View view){
		TextView left_name = (TextView) findViewById(R.id.leftGuyName);
		left_name.setText("WINWIN");
	}
	
	public void setTeam(){
		team[0] = "Guy1";
		team[1] = "Guy2";
		team[2] = "Guy3";
		team[3] = "Guy4";
		team[4] = "Guy5";
	}


/*	public void showPopup(View v) {
	    PopupMenu popup = new PopupMenu(this, v);
	    MenuInflater inflater = popup.getMenuInflater();
	    inflater.inflate(R.menu.battlemenu, popup.getMenu());
	    popup.show();
	} */
}
