package com.javacodegeeks.android.androidsocketserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class Server extends Activity {
	private ServerSocket serverSocket;
	private TextView text;
	
	Handler updateConversationHandler;
	Thread serverThread = null;
	
	public static final int SERVERPORT = 6000;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		text = (TextView)findViewById(R.id.text2);

		updateConversationHandler = new Handler();

		this.serverThread = new Thread(new ServerThread());
		this.serverThread.start();
	}

	@Override
	protected void onStop() {
		super.onStop();
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class ServerThread implements Runnable {
		public void run() {
			Socket socket = null;
			try {
				serverSocket = new ServerSocket(SERVERPORT);
			} catch (IOException e) {
				e.printStackTrace();
			}
			while (!Thread.currentThread().isInterrupted()) {
				try {
					socket = serverSocket.accept();

					CommunicationThread commThread = new CommunicationThread(socket);
					
					new Thread(commThread).start();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	class CommunicationThread implements Runnable {
		private Socket clientSocket;
		private BufferedReader input;

		public CommunicationThread(Socket clientSocket) {
			this.clientSocket = clientSocket;
			try {
				this.input = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void run() {		
			while (!Thread.currentThread().isInterrupted()) {
				try {
					String read = input.readLine();
					updateConversationHandler.post(new updateUIThread(read));					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	class updateUIThread implements Runnable {
		private String msg;

		public updateUIThread(String str) {
			this.msg = str;
		}

		@Override
		public void run() {
			
			if(Util.isNumeric(msg)){
				msg = "Number Identified, sum itself: " + this.sum(Integer.parseInt(msg), Integer.parseInt(msg));
			}
			
			text.setText(text.getText().toString()+"Client Says: "+ msg + "\n");
		}
		
		public String sum(int a, int b){
			int product = 0;
			
			product = a + b; 
			
			return String.valueOf(product);
		}
	}
	
	static class Util {
		public static boolean isNumeric(String str) {
			  return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
			}
	}
}