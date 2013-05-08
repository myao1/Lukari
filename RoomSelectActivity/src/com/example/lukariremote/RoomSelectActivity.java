package com.example.lukariremote;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Context;
import android.database.DataSetObserver;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class RoomSelectActivity extends Activity {

	static RoomListAdapter roomListAdapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_select);
        
        final ListView roomListView = (ListView) findViewById(R.id.roomList);
        String[] rooms = new String[] {"Master Bedroom","Kitchen","Bathroom", "Living Room" };
        final ArrayList<String> list = new ArrayList<String>();
        list.add(rooms[0]);
        list.add(rooms[1]);
        list.add(rooms[2]);
        list.add(rooms[3]);
        
        roomListAdapter = new RoomListAdapter(this, R.layout.room_item, list);
        
        roomListView.setAdapter(roomListAdapter);
        
        roomListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        	
        	public void onItemClick(AdapterView<?> parent, final View view, int position, long id){
        		final String item = (String) parent.getItemAtPosition(position);
        		// On item click, it should start the corresponding room activity
        		Intent roomIntent = new Intent(RoomSelectActivity.this, RoomActivity.class);
        		roomIntent.putExtra("roomName", item);
        		roomIntent.putExtra("rooms", roomListAdapter.getRooms());
        		RoomSelectActivity.this.startActivity(roomIntent);
        	}
		});
        
        roomListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
        	
        	public boolean onItemLongClick(AdapterView<?> parent, final View view, int position, long id){
        		return false;
        	}
		});
    }

    private class RoomListAdapter extends ArrayAdapter<String>{
    	
    	private List<String> rooms;
    	
    	public RoomListAdapter(Context context, int textViewResourceId, List<String> objs){
    		super(context, textViewResourceId, objs);
    		rooms.addAll(objs);
    	}
    	
    	public boolean addRoom(String newRoom){
    		return rooms.add(newRoom);
    	}
    	
    	public String[] getRooms(){
    		String[] roomArray = new String[rooms.size()];
    		for(int i=0; i<roomArray.length; i++){
    			roomArray[i] = rooms.get(i);
    		}
    		return roomArray;
    	}
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.room, menu);
        
        return true;
    }
    
    public void addRoom(View view){
    	
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setTitle("Room Name");
    	builder.setMessage("Enter the room name");
    	
    	final EditText roomName = new EditText(this);
    	builder.setView(roomName);
    	
    	builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
    		public void onClick(DialogInterface dialog, int btn) {
    			String value = roomName.getText().toString();
    			roomListAdapter.addRoom(value);
    			// should add the string name for new room to list
    			
    			Intent newRoomIntent = new Intent(RoomSelectActivity.this, RoomActivity.class);
    			newRoomIntent.putExtra("roomName", value);
        		RoomSelectActivity.this.startActivity(newRoomIntent);
    		}
    	});
    	
    	builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
    		public void onClick(DialogInterface dialog, int btn) {
    			
    		}
    	});
    	builder.show();
    	
    	
    }
    
}
