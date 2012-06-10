package com.ftwdev.stuffinc.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.ftwdev.stuffinc.core.*;
import com.ftwdev.stuffinc.network.StuffedPacket;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class UserLogin extends Activity{
    public final static String USERNAME = "com.ftwdev.stuffinc.USERNAME";
    public final static String PASSWORD = "com.ftwdev.stuffinc.PASSWORD";
    
	public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);
	}
	
    public void loginUser(View view) {
    	Intent display_credentials = new Intent(this, DisplayLoginCredentials.class);
    	EditText userText = (EditText) findViewById(R.id.user_login);
    	EditText passText = (EditText) findViewById(R.id.user_password);
    	String username = userText.getText().toString();
    	String password = passText.getText().toString();
    	display_credentials.putExtra(USERNAME, username);
    	display_credentials.putExtra(PASSWORD, password);
    	startActivity(display_credentials);
    	
    	User login_user = new User(username);
    	StuffedPacket auth = new StuffedPacket("AUTHENTICATING", "User", login_user);
    	
    	try {
			Socket socket = new Socket("10.66.27.113", 12345);
	    	ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
	    	ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
	    	os.writeObject(auth);
	    	try {
				StuffedPacket return_packet = (StuffedPacket) is.readObject();
				System.out.println(return_packet.message);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
    
    public void signUp(View view){
    	Intent signup = new Intent(this, UserSignup.class);
    	startActivity(signup);
    }
    
    public void home(View viw){
    	Intent home = new Intent(this, Home.class);
    	startActivity(home);
    }
}
