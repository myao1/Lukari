package com.example.lukariremote;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.*;
import android.widget.EditText;

public class RoomSelectActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_select);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.room, menu);
        return true;
    }
    
    public void addRoom(View view){
    	Intent makeNewRoom = new Intent(this, RoomActivity.class);
    	
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setTitle("Room Name");
    	builder.setMessage("Enter the room name");
    	
    	final EditText roomName = new EditText(this);
    	builder.setView(roomName);
    	
    	builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
    		public void onClick(DialogInterface dialog, int btn) {
    			String value = roomName.getText().toString();
    			
    		}
    	});
    	
    	builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
    		public void onClick(DialogInterface dialog, int btn) {
    			
    		}
    	});
    	builder.show();
    	
    }
    
}
