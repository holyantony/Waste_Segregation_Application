//package com.example.waste_segregation;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//import android.widget.AdapterView.OnItemClickListener;
//
//public class bldg_details extends Activity {
//	TextView building_name;
//	ListView buildings;
//	String item ;
//	String Area;
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.bldgs_details);
//
//		Intent intent = getIntent();
//		Area = intent.getStringExtra("Area");
//
//		building_name= (TextView) findViewById(R.id.tvbuilding_name);
//		building_name.setText("Buildings - "+Area);
//
//		if (Area.equals("Hill Side")) {
//
//			building_name.setBackgroundColor(Color.parseColor("#96B62E"));
//
//		}else if (Area.equals("Lake Side")) {
//
//			building_name.setBackgroundColor(Color.parseColor("#39AECF"));
//
//		}else {
//
//			building_name.setBackgroundColor(Color.parseColor("#FFC640"));
//
//		}
//
//
//		buildings= (ListView) findViewById(R.id.lvbuildings);
//		String[] values = new String[] { "Nilagiri", "Building1", "Building2","Building3","Building4","Building5"};
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//				android.R.layout.simple_list_item_1, values);
//		buildings.setAdapter(adapter);
//
//		buildings.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				item = (String) parent.getItemAtPosition(position);
//				Toast.makeText(getApplicationContext(),
//						"Selected item: " + item, Toast.LENGTH_SHORT)
//						.show();		
//
//				Intent i= new Intent(getApplicationContext(), MainActivity.class);
//				i.putExtra("Area", Area);
//				startActivity(i);
//			}
//		});
//
//
//	}
//}
package com.example.waste_segregation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class bldg_details extends Activity {
	TextView building_name;
	ListView buildings;
	String item ;
	String Area;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bldgs_details);

		Intent intent = getIntent();
		Area = intent.getStringExtra("Area");

		building_name= (TextView) findViewById(R.id.tvbuilding_name);
		building_name.setText("Buildings - "+Area);

		if (Area.equals("Hill Side")) {

			building_name.setBackgroundColor(Color.parseColor("#96B62E"));

		}else if (Area.equals("Lake Side")) {

			building_name.setBackgroundColor(Color.parseColor("#39AECF"));

		}else {

			building_name.setBackgroundColor(Color.parseColor("#FFC640"));

		}


		buildings= (ListView) findViewById(R.id.lvbuildings);
		String[] values = new String[] { "Nilagiri", "Building1", "Building2","Building3","Building4","Building5"};
		
		MySimpleArrayAdapter adapter= new MySimpleArrayAdapter(getApplicationContext(), values);
		
		buildings.setAdapter(adapter);

		buildings.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				item = (String) parent.getItemAtPosition(position);
//				Toast.makeText(getApplicationContext(),
//						"Selected item: " + item, Toast.LENGTH_SHORT)
//						.show();		

				Intent i= new Intent(getApplicationContext(), MainActivity.class);
				i.putExtra("Area", Area);
				startActivity(i);
			}
		}); 


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
		    if (Area.equals("Central Area")) {
		    	textView.setBackgroundResource(R.drawable.central_area);

		    }else if (Area.equals("Hill Side")) {
		    	textView.setBackgroundResource(R.drawable.hill_side);

			} else {
		    	textView.setBackgroundResource(R.drawable.lake_side);
		    }

		    return rowView;
		  }
		} 
}
