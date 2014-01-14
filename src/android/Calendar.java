package org.devgirl.calendar;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Calendar extends CordovaPlugin { 
    
    private Socket socket;
    private static final int SERVERPORT = 8124;
    private static final String SERVER_IP = "10.51.100.192";
    
    @Override    
    public boolean execute(String action, JSONArray arguments, CallbackContext callbackContext) throws JSONException {                      
        if (action.equals("Test")) {
            String responseText = "Hello world";
            try {
                
                InetAddress serverAddr = InetAddress.getByName(SERVER_IP); 
                socket = new Socket(serverAddr, SERVERPORT);                
                
                responseText += ", " + arguments.getString(0);
                callbackContext.success(responseText);
                
                return true;
                
            } catch (JSONException e) {
                callbackContext.error(e.getMessage());
            }
        } else {
            callbackContext.error("Invalid action: " + action);
            return false;
        }
        return false;
    }
}