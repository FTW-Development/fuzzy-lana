package com.ftwdev.stuffinc.server;


import java.net.*;
import java.io.*;

public class clientThread extends Thread {
    private Socket socket = null;

            try {
                clientSocket = stuffServer.accept();
            }
            catch (IOException e){
                System.out.println("Could not accept client connections");
                System.exit(-1);
            }
