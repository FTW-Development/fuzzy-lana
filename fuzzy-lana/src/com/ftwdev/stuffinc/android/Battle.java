package com.ftwdev.stuffinc.android;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ftwdev.stuffinc.core.Collector;
import com.ftwdev.stuffinc.core.Move;
import com.ftwdev.stuffinc.core.Stuff;
import com.ftwdev.stuffinc.core.StuffFactory;
import com.ftwdev.stuffinc.core.Thing;

public class Battle extends Activity {
	private Thing leftGuy;
	private Thing rightGuy;
	private Collector leftCollector;
	private Collector rightCollector;
	private Animation leftGuyAnim;
	private com.ftwdev.stuffinc.core.Battle battle;
	private Map<Thing, Map<String, Integer>> resourceMap;
		
	public void onCreate(Bundle savedInstanceState){
		//TEMPORARY
		fixThingYAML();
		
		leftGuy = StuffFactory.getWild(1, 20);
		rightGuy = StuffFactory.getWild(1, 20);
		
		//TEMPORARY - Collector should exist before battle
		leftCollector = new Collector(leftGuy);
		for (int i = 0; i < 3; ++i){
			leftCollector.addThingToPocket(StuffFactory.getWild(1, 30));
		}
		rightCollector = new Collector(rightGuy);

		//TEMPORARY
		fixCurrentHealth();
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.battle);
		
		battle = new com.ftwdev.stuffinc.core.Battle(leftGuy, rightGuy);
		
		findViewById(R.id.leftGuyStats).getBackground().setAlpha(200);
		findViewById(R.id.rightGuyStats).getBackground().setAlpha(200);

		createResourceMap();
		createMoveSection();
		createAlternatesSection();
		createOptionsSection();
		initializeStatDisplay();
	}

	private void createAlternatesSection() {
		List<Thing> pocket = leftCollector.getPocket();
		LinearLayout alternatesSection = (LinearLayout) findViewById(R.id.battleAlternatesSection);
		alternatesSection.removeAllViews();
		for (Thing thing : pocket) {
			if (thing != leftGuy){
				alternatesSection.addView(createAlternateButton(thing));
			}
		}
		alternatesSection.setVisibility(LinearLayout.GONE);
	}

	private Button createAlternateButton(Thing thing) {
		Button button = createGenericButton();
		button.setText(thing.getName());
		button.setTag(thing);
		button.setOnClickListener(createAlternateButtonListener());
		return button;
	}

	//TEMPORARY METHODS
	private void fixThingYAML() {
		AssetManager am = getBaseContext().getAssets();
		try {
			Move.initMoveList(am.open("Moves.yml"));
			Stuff.initStuffList(am.open("Stuff.yml"));
		} catch (IOException e) {
			System.out.println("Failed to open yml files");
			e.printStackTrace();
		}
	}

	private void fixCurrentHealth() {
		leftGuy.setCurrentHealth(leftGuy.getHealth());
		rightGuy.setCurrentHealth(rightGuy.getHealth()); 
	}

	//ACTUAL METHODS
	private void createOptionsSection() {
		ViewGroup optionsSection = (ViewGroup) findViewById(R.id.battleOptionsSection);
		optionsSection.addView(createMovesButton());
		optionsSection.addView(createSwapButton());
	}

	private Button createMovesButton() {
		Button movesButton = createGenericButton();
		movesButton.setText("Moves");
		movesButton.setOnClickListener(createMovesButtonListener());
		return movesButton;
	}
	
	private Button createSwapButton() {
		Button swapButton = createGenericButton();
		swapButton.setText("Swap");
		swapButton.setOnClickListener(createSwapButtonListener());
		return swapButton;
		
	}


	private void initializeStatDisplay(){
		initializeStatDisplay(leftGuy);
		initializeStatDisplay(rightGuy);
		updateStatDisplay();
	}

	private void initializeStatDisplay(Thing thing){
		TextView name = (TextView) findViewByMap(thing, "name");
		name.setText("Name: " + thing.getName());
		
		TextView level = (TextView) findViewByMap(thing, "level");
		level.setText("Level " + thing.getLevel());
		
		ImageView image = (ImageView) findViewByMap(thing, "image");
		image.setImageResource(getImageId(thing));
		
		leftGuyAnim = AnimationUtils.loadAnimation(this, R.anim.left_thing_battle);
	}

	private void updateStatDisplay(){
		updateStatDisplay(leftGuy);
		updateStatDisplay(rightGuy);
	}
	
	private void updateStatDisplay(Thing thing){
		TextView health = (TextView) findViewByMap(thing, "health");
		health.setText("Health: " + thing.getCurrentHealth() + " / " + thing.getHealth());
		
		ProgressBar healthBar = (ProgressBar) findViewByMap(thing, "healthbar");
		healthBar.setMax(thing.getHealth());
		healthBar.setProgress(thing.getCurrentHealth());
	}
	
	private View findViewByMap(Thing thing, String field){
		int id = resourceMap.get(thing).get(field);
		View view = findViewById(id);
		return view;
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
	
	private void createMoveSection() {
		ArrayList<Move> moves = leftGuy.getMoveSet();
		ViewGroup moveSection = (ViewGroup) findViewById(R.id.battleMoveSection);
		for (int i=0; i < moves.size(); ++i ){
			Button button = createMoveButton(moves, i);
			moveSection.addView(button);
		}
		moveSection.setVisibility(ViewGroup.GONE);
	}
	
	private Button createMoveButton(ArrayList<Move> moves, int i) {
		Button button = createGenericButton();
		button.setText(moves.get(i).getName());
		button.setTag(i);
		button.setOnClickListener(createAttackButtonListener());
		return button;
	}

	private Button createGenericButton() {
		LinearLayout.LayoutParams lay = createButtonLayout();
		Button button = new Button(this);
		button.setLayoutParams(lay);
		button.getBackground().setAlpha(200);
		return button;
	}

	private LinearLayout.LayoutParams createButtonLayout() {
		LinearLayout.LayoutParams lay = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
		lay.weight = 5;
		return lay;
	}	

	private OnClickListener createAttackButtonListener() {
		return new View.OnClickListener() {
			public void onClick(View view) {
				Button button = (Button) view;
				int buttonNum = (Integer) button.getTag();
				Move leftAttack = leftGuy.getMoveSet().get(buttonNum);
				
				battle.round(leftAttack);
				updateStatDisplay();

				ImageView image = (ImageView) findViewById(R.id.leftGuyImage);
				image.startAnimation(leftGuyAnim);
				
				hideButtonParent(button);
				
				if (battle.isComplete()){
					end();
				} else {
					showOptionsSection();
				}
			}
        };
	}
	
	private OnClickListener createAlternateButtonListener(){
		return new View.OnClickListener() {
			public void onClick(View view) {
				Button button = (Button) view;
				Thing thing = (Thing) button.getTag();

				leftGuy = thing;
				
				//to adjust the alternates to contain the character swapped out.
				createResourceMap();
				hideButtonParent(button);
				createAlternatesSection();
				initializeStatDisplay();
				updateStatDisplay();
				showOptionsSection();
			}
        };
	}
	
	protected void showOptionsSection() {
		LinearLayout optionsSection = (LinearLayout) findViewById(R.id.battleOptionsSection);
		optionsSection.setVisibility(LinearLayout.VISIBLE);
	}
	
	protected void showMoveSection() {
		LinearLayout moveSection = (LinearLayout) findViewById(R.id.battleMoveSection);
		moveSection.setVisibility(LinearLayout.VISIBLE);
	}
	
	protected void showAlternatesSection(){
		LinearLayout alternatesSection = (LinearLayout) findViewById(R.id.battleAlternatesSection);
		alternatesSection.setVisibility(LinearLayout.VISIBLE);
	}
	
	private void hideButtonParent(Button button) {
		LinearLayout section = (LinearLayout) button.getParent();
		section.setVisibility(TextView.GONE);
	}

	private OnClickListener createMovesButtonListener() {
		return new View.OnClickListener() {
			public void onClick(View view) {
				Button button = (Button) view;
				hideButtonParent(button);
				showMoveSection();
			}
        };
	}
	
	private OnClickListener createSwapButtonListener() {
		return new View.OnClickListener() {
			public void onClick(View view) {
				Button button = (Button) view;
				hideButtonParent(button);
				showAlternatesSection();
			}
        };
	}
	
	private void createResourceMap() {
		resourceMap = new HashMap<Thing, Map<String, Integer>>();
		resourceMap.put(leftGuy, new HashMap<String, Integer>());
		resourceMap.put(rightGuy, new HashMap<String, Integer>());
		
		Map<String, Integer> leftGuyMap = resourceMap.get(leftGuy);
		leftGuyMap.put("stats", R.id.leftGuyStats);
		leftGuyMap.put("image", R.id.leftGuyImage);
		leftGuyMap.put("level", R.id.leftGuyLevel);
		leftGuyMap.put("name", R.id.leftGuyName);
		leftGuyMap.put("health", R.id.leftGuyHealth);
		leftGuyMap.put("healthbar", R.id.leftGuyHealthBar);
		
		Map<String, Integer> rightGuyMap = resourceMap.get(rightGuy);
		rightGuyMap.put("stats", R.id.rightGuyStats);
		rightGuyMap.put("image", R.id.rightGuyImage);
		rightGuyMap.put("level", R.id.rightGuyLevel);
		rightGuyMap.put("name", R.id.rightGuyName);
		rightGuyMap.put("health", R.id.rightGuyHealth);
		rightGuyMap.put("healthbar", R.id.rightGuyHealthBar);	
	}


	private void end() {
		TextView battleTextPopup = (TextView) findViewById(R.id.battleTextPopup);
		battleTextPopup.setText("Battle Over!");
		battleTextPopup.append(battle.getWinner().getName() + "WINS");
		battleTextPopup.setVisibility(TextView.VISIBLE);
	}


}
