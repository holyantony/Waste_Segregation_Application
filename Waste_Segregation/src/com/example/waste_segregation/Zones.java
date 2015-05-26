package com.example.waste_segregation;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Zones extends Activity {
	ListView zone;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.zones);
		zone= (ListView) findViewById(R.id.lvzone);
		String[] values = new String[] { "Central Area", "Hill Side", "Lake Side"};
		
		MySimpleArrayAdapter adapter= new MySimpleArrayAdapter(getApplicationContext(), values);
		
		zone.setAdapter(adapter);
		
		Button share = (Button) findViewById(R.id.btnshare);
		share.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//			
				final Dialog dialog = new Dialog(Zones.this);
				// Include dialog.xml file
				dialog.setContentView(R.layout.password);
				dialog.setTitle("Password");
				// set values for custom dialog components - text, image and button
				final EditText text = (EditText) dialog.findViewById(R.id.password);
				
				dialog.show();
	                 
	                Button declineButton = (Button) dialog.findViewById(R.id.buttonOK);
	                // if decline button is clicked, close the custom dialog
	                declineButton.setOnClickListener(new OnClickListener() {
	                    @Override
	                    public void onClick(View v) {
	                    	
	                    	if (text.getText().toString().trim().equals("633423")) {
	                    		Intent intent = new Intent(Zones.this, log.class);
	                    		startActivity(intent);
	                    	}else {
	                    		Toast.makeText(getApplicationContext(),
	                    				"Wrong password!!", Toast.LENGTH_SHORT)
	                    				.show();	
	                    	}
	                    }
	                });
				
			}
		});
		
		zone.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
		        String item = (String) parent.getItemAtPosition(position);
				Intent i= new Intent(getApplicationContext(), bldg_details.class);
				i.putExtra("Area", item);
				startActivity(i);
			} 
		});
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public class MySimpleArrayAdapter extends ArrayAdapter<String> {
		  private final Context context;
		  private final String[] values;

		  public MySimpleArrayAdapter(Context context, String[] values) {
		    super(context, R.layout.row, values);
		    this.context = context;
		    this.values = values;
		  }

		  @Override
		  public View getView(int position, View convertView, ViewGroup parent) {
		    LayoutInflater inflater = (LayoutInflater) context
		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		    View rowView = inflater.inflate(R.layout.row, parent, false);
		    TextView textView = (TextView) rowView.findViewById(R.id.rowTextView);
		    textView.setTextColor(new Color().parseColor("#000000"));
		    textView.setText(values[position]);
		    
		    // change the icon for Windows and iPhone
		    String item_name = values[position];
		    if (item_name.contains("Central Area")) {
		    	textView.setBackgroundResource(R.drawable.central_area);

		    }else if (item_name.contains("Hill Side")) {
		    	textView.setBackgroundResource(R.drawable.hill_side);

			} else {
		    	textView.setBackgroundResource(R.drawable.lake_side);
		    }

		    return rowView;
		  }
		} 
	
}
