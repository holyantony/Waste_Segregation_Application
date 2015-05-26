//
//
//package com.example.waste_segregation;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.poifs.filesystem.POIFSFileSystem;
//
//import android.R.integer;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.support.v7.app.ActionBarActivity;
//import android.util.Log;
//import android.view.View;
//
//import android.widget.ExpandableListView;
//import android.widget.ExpandableListView.OnChildClickListener;
//import android.widget.TextView;
//import android.widget.Toast;
//
//public class MainActivity extends ActionBarActivity {
//
//	ExpandableListAdapter listAdapter;
//	ExpandableListView expListView;
//	List<String> listDataHeader;
//	HashMap<String, List<String>> listDataChild;
//	String Area;
//	ArrayList<ArrayList<String>> details = new ArrayList<ArrayList<String>>();
//	ArrayList<ArrayList<String>> flats = new ArrayList<ArrayList<String>>();
//	ArrayList<String> tempElements;
//	static String TAG = "ExelLog";
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
//
//		// get the listview
//
//		Intent intent = getIntent();
//		Area = intent.getStringExtra("Area");
//		TextView floor = (TextView) findViewById(R.id.tvfloor);
//
//
//		if (Area.equals("Hill Side")) {
//
//			floor.setBackgroundColor(Color.parseColor("#96B62E"));
//
//		}else if (Area.equals("Lake Side")) {
//			floor.setBackgroundColor(Color.parseColor("#39AECF"));
//
//
//		}else {
//			floor.setBackgroundColor(Color.parseColor("#FFC640"));
//
//		}
//
//		expListView = (ExpandableListView) findViewById(R.id.lvExp);
//
//		// preparing list data
//		prepareListData();
//
//		listAdapter = new ExpandableListAdapter(this, Area, listDataHeader, listDataChild);
//
//		// setting list adapter
//		expListView.setAdapter(listAdapter);
//
//		// Listview on child click listener
//		
//		
//		
//		
//		
//		expListView.setOnChildClickListener(new OnChildClickListener() {
//
//
//			@Override
//			public boolean onChildClick(ExpandableListView parent, View v,
//					int groupPosition, int childPosition, long id) {
//				// TODO Auto-generated method stub
//				String value = listDataHeader.get(groupPosition);
//				System.out.println("Value:"+value+" "+" "+ listDataChild);
//
//				String name= listDataChild.get( value).get(childPosition);
//				System.out.println("name:"+name);
//
//				Toast.makeText( getApplicationContext(), listDataHeader.get(groupPosition) + " : "+ name, Toast.LENGTH_SHORT)
//				.show();
//
//				details.indexOf(name);
//				
//
//				for (int i = 0; i < details.size(); i++) {
//					if (details.get(i).get(0).contains(name+".0")) {
//						System.out.println("yes"+details.get(i));	
//					}
//				}
//
//
//				TextView exptv = (TextView)v.findViewById(R.id.name); //  Get the textview holding the text
//				String yourText = exptv.getText().toString();
//				Toast.makeText( getApplicationContext(), "yourText:"+yourText, Toast.LENGTH_SHORT)
//				.show();
//				return false;
//			}
//		});
//
//		// DatabaseHandler dh = new DatabaseHandler(MainActivity.this);
//	}
//
//	private void loadDataFromAsset() {
//		try {
//			// get input stream for text
//			InputStream is = getAssets().open("Nilagiri.xls");
//			// check size
//			int size = is.available();
//			// create buffer for IO
//			byte[] buffer = new byte[size];
//			// get data to buffer
//			is.read(buffer);
//			try {
//				FileOutputStream output = openFileOutput("Nilagiri.xls", Context.MODE_PRIVATE);
//				output.write(buffer);
//				output.flush();
//				output.close();
//				is.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		catch (IOException ex) {
//			return;
//		}
//	}
//
//
//
//	private void prepareListData() {
//		listDataHeader = new ArrayList<String>();
//		listDataChild = new HashMap<String, List<String>>();
//
//		loadDataFromAsset();
//
//		//xls parsing
//
//		try{
//
//			// Creating Input Stream 
//			//            File file = new File(context.getExternalFilesDir(null), filename); 
//			File path = new File("/data/data/com.example.waste_segregation/files/Nilagiri.xls");
//			FileInputStream myInput = new FileInputStream(path);
//
//			// Create a POIFSFileSystem object 
//			POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);
//
//			// Create a workbook using the File System 
//			HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);
//
//			// Get the first sheet from workbook 
//			HSSFSheet mySheet = myWorkBook.getSheetAt(0);
//
//			/** We now need something to iterate through the cells.**/
//			Iterator rowIter = mySheet.rowIterator();
//
//			while(rowIter.hasNext()){
//				HSSFRow myRow = (HSSFRow) rowIter.next();
//				Iterator cellIter = myRow.cellIterator();
//				ArrayList<String> rows_details = new ArrayList<String>();
//				if (cellIter.hasNext()) { 
//					int count=0;
//					while(cellIter.hasNext()){
//						HSSFCell myCell = (HSSFCell) cellIter.next();
//						String value= myCell.toString();
//						if (value.length()>0) {
//							
////							if (count==0) {
////								System.out.println(myCell.toString());
////							}
////							System.out.println("count:"+count++);
//
////							Log.d(TAG, "Cell Value: " +  myCell.toString());
//							
//							rows_details.add(value);
//						}
//					}
//					details.add(rows_details);
//					System.out.println("out");
//				}
//
//			}
//			System.out.println(details);
//		}catch (Exception e){
//			e.printStackTrace();
//		}
//
//
//		for (int i = 0; i <details.size(); i++) {
//			//			System.out.println("inn");
//			String value= details.get(i).get(3).replace(".0", "");
//			//			System.out.println("Flat numbers:"+details.get(i).get(0));
//			String prefix="";
//			if (Integer.parseInt(value) >0 && Integer.parseInt(value) <3) {
//				prefix="st";
//			}else if (Integer.parseInt(value) ==3) {
//				prefix="rd";
//			}else {
//				prefix="th";
//			}
//			
//			if (!listDataHeader.contains(value+prefix+" "+"Floor")) {
//
//				listDataHeader.add(value+prefix+" "+"Floor");
//
//			}
//
//		}
//		
////		int k=0;
////		int count=0;
////
////		ArrayList<String> rows_details=null;
////		rows_details = new ArrayList<String>();
////
////		for (int j = 0; j < details.size(); j++) {
////
////			if (details.get(j).get(3).replace(".0", "").toString().contains(listDataHeaderq.get(k))) {
////				System.out.println("details.get(j):"+details.get(j));
////				rows_details.add(details.get(j).get(0).toString());
////				
////				System.out.println("k:"+k);
////				System.out.println("details.size()-1:"+details.size()-1);
////
////				count++;
////				System.out.println("count:"+count);
////
////				if (count==details.size()-1) {
////					System.out.println("innn");
////				}
//////				System.out.println(listDataHeaderq.size()-1);
////				
//////				if (k==listDataHeaderq.size()-1) {
//////					flats.add(rows_details);
//////
//////				}
////			}else {
////				flats.add(rows_details);
////				count++;
////				System.out.println("count:"+count);
////
////				System.out.println("flts:"+flats);
////				rows_details = new ArrayList<String>();
////				System.out.println("inelse");
////				k++;
////				j--;
////				System.out.println("j:"+j);
//////								break;
////			}
////		} 
////		
////		System.out.println("out from loop");
////
//////		System.out.println("flats:"+flats);
//
//		List<String> top250 = new ArrayList<String>();
//
//
//		top250.add("268");
//		top250.add("269");
//		top250.add("270");
//		top250.add("271");
//
//
//		for (int i = 0; i < listDataHeader.size(); i++) {
//
//			listDataChild.put(listDataHeader.get(i), top250); // Header, Child data
//
//		}
//
//	}
//}


package com.example.waste_segregation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

 
public class MainActivity extends Activity {

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		SharedPreferences prefs = getSharedPreferences("my_pref", MODE_PRIVATE); 
		int size = prefs.getInt("array_size", 0);
		for(int i=0; i<size; i++){
			
		    todo_name.add(prefs.getString("p_name" + i, null));
		    todo_email.add(prefs.getString("p_email" + i, null));

		}
		
	}

	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;
	String Area,email_id,p_name,p_flat;
	ArrayList<ArrayList<String>> details = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> flats = new ArrayList<ArrayList<String>>();
//	ArrayList<String> tempElements;
	static String TAG = "ExelLog";
	List<String> list_floors ;
	List<String> list_flats,list_names ;
	List<ArrayList<String>> names_lol ;
	ArrayList<ArrayList<String>> final_details;
	EditText dry, wet;
	Boolean wet_flag;
	ArrayList<String> todo_email = new ArrayList<String>();
	ArrayList<String> todo_name = new ArrayList<String>();
	SharedPreferences.Editor edit ;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// get the listview

		Intent intent = getIntent();
		Area = intent.getStringExtra("Area");
		TextView floor = (TextView) findViewById(R.id.tvfloor);
		
		Button save = (Button) findViewById(R.id.btnsave);
		 dry = (EditText) findViewById(R.id.etdry);
		 wet = (EditText) findViewById(R.id.etwet);
		 wet_flag= true;

		save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if (wet.isEnabled() || dry.isEnabled()) {
					if (wet_flag == true) {
						
						if (!isEmpty(wet.getText())) {
							write_xls("Wet waste", wet.getText().toString(), true,1);
							wet.setEnabled(false);
							wet_flag= false;
						}else {
							Toast.makeText(getApplicationContext(), "Field is blank", Toast.LENGTH_SHORT).show();
						}
						
					}else {
						
						if (!isEmpty(wet.getText())) {
							write_xls("Dry waste", dry.getText().toString(), true,0);
							dry.setEnabled(false);
							wet_flag= true;
						}else {
							Toast.makeText(getApplicationContext(), "Field is blank", Toast.LENGTH_SHORT).show();
						}
					}
					
				}
				
		
			}
		});



		if (Area.equals("Hill Side")) {

			floor.setBackgroundColor(Color.parseColor("#96B62E"));

		}else if (Area.equals("Lake Side")) {
			floor.setBackgroundColor(Color.parseColor("#39AECF"));

		}else {
			floor.setBackgroundColor(Color.parseColor("#FFC640"));

		}

		expListView = (ExpandableListView) findViewById(R.id.lvExp);

		// preparing list data
		prepareListData();

		listAdapter = new ExpandableListAdapter(this, Area, listDataHeader, listDataChild,names_lol);

		// setting list adapter
		expListView.setAdapter(listAdapter);
		expListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v, int groupPosition,
					int childPosition, long id) {
				String value = listDataHeader.get(groupPosition);
				//				System.out.println("Value:"+value+" "+" "+ listDataChild);

				String name= listDataChild.get( value).get(childPosition);
				//				System.out.println("name:"+name);


				for (int i = 0; i < final_details.size(); i++) {
					if (final_details.get(i).get(0).contains(name+".0")) {
						System.out.println("yes::"+final_details.get(i).get(2));	
						email_id=final_details.get(i).get(2);
						p_name=final_details.get(i).get(1);
						p_flat=final_details.get(i).get(0);

					}
				}

				//				System.out.println("email_id:"+email_id);
				//
				//				TextView exptv = (TextView)v.findViewById(R.id.name); //  Get the textview holding the text
				//				String yourText = exptv.getText().toString();
				//				Toast.makeText( getApplicationContext(), "yourText:"+yourText, Toast.LENGTH_SHORT)
				//				.show();


				CustomDialogClass c_dialog=new CustomDialogClass(MainActivity.this, email_id,p_name,p_flat);
				c_dialog.show();  

				return false;
			}
		});

	}
	public boolean isEmpty(CharSequence str) {
	    if (str == null || str.length() == 0)
	        return true;
	    else
	        return false;
	}
	private void loadDataFromAsset() {
		try {
			// get input stream for text
			InputStream is = getAssets().open("Nilagiri.xls");
			// check size
			int size = is.available();
			// create buffer for IO
			byte[] buffer = new byte[size];
			// get data to buffer
			is.read(buffer);
			try {
				FileOutputStream output = openFileOutput("Nilagiri.xls", Context.MODE_PRIVATE);
				output.write(buffer);
				output.flush();
				output.close();
				is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		catch (IOException ex) {
			return;
		}
	}



	@SuppressWarnings("null")
	private void prepareListData() {
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<String>>();
		loadDataFromAsset();

		//xls parsing

		try{

			// Creating Input Stream
			//            File file = new File(context.getExternalFilesDir(null), filename);
			File path = new File("/data/data/com.example.waste_segregation/files/Nilagiri.xls");
			FileInputStream myInput = new FileInputStream(path);

			// Create a POIFSFileSystem object
			POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);

			// Create a workbook using the File System
			HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);

			// Get the first sheet from workbook
			HSSFSheet mySheet = myWorkBook.getSheetAt(0);

			/** We now need something to iterate through the cells.**/
			Iterator rowIter = mySheet.rowIterator();

			while(rowIter.hasNext()){
				HSSFRow myRow = (HSSFRow) rowIter.next();
				Iterator cellIter = myRow.cellIterator();
				ArrayList<String> rows_details = new ArrayList<String>();
				if (cellIter.hasNext()) {
					while(cellIter.hasNext()){
						HSSFCell myCell = (HSSFCell) cellIter.next();
						String value= myCell.toString();
						if (value.length()>0) {
//							Log.d(TAG, "Cell Value: " +  myCell.toString());
							rows_details.add(value);
						}
					}
					details.add(rows_details);
				}

			}
//						System.out.println(details);
		}catch (Exception e){
			e.printStackTrace();
		}

		
		final_details = new ArrayList<ArrayList<String>> (details);
		Collections.reverse(final_details);
//		System.out.println("temp:"+final_details);

		/////////////////////////// flats ////////////////////

		String floors;
		for(int j = 0; j < final_details.size();j++)
		{
			floors= final_details.get(j).get(3).replace(".0", "");


			// System.out.println("Flat numbers:"+details.get(i).get(0));
			String prefix="";
			if (Integer.parseInt(floors) >0 && Integer.parseInt(floors) <3) {
				prefix="st";
			}else if (Integer.parseInt(floors) ==3) {
				prefix="rd";
			}else {
				prefix="th";
			}
			//System.out.println("list of floors"+floor_list);
			//			System.out.println("header list :"+listDataHeader);
			if (!listDataHeader.contains(floors+prefix+" "+"Floor")) {

				listDataHeader.add(floors+prefix+" "+"Floor");
			}
		}

		list_floors= new ArrayList<String>();

		for(int j = 0; j < final_details.size();j++)
		{

			floors= final_details.get(j).get(3).replace(".0", "");
			if(!list_floors.contains(floors))
			{
				list_floors.add(floors);
			}

		}
		//		System.out.println("floors list "+list_floors);
		List<ArrayList<String>> flats_lol = new ArrayList<ArrayList<String>>();
		names_lol = new ArrayList<ArrayList<String>>();

		for(int k = 0; k < list_floors.size(); k++)
		{

			floors= list_floors.get(k);
			//			System.out.println("floors "+floors);
			list_flats= new ArrayList<String>();
			list_names= new ArrayList<String>();

			for(int j = 0; j < final_details.size();j++)
			{
				//				System.out.println("print details list "+details.get(j));
				if(final_details.get(j).contains(floors.concat(".0")))
				{
					list_flats.add(final_details.get(j).get(0).toString().replace(".0", ""));
					list_names.add(final_details.get(j).get(1).toString());

				}

			}
			flats_lol.add((ArrayList<String>) list_flats);
			names_lol.add((ArrayList<String>) list_names);


		}

		//		System.out.println("flats list "+flats_lol);
		//		System.out.println("names list "+names_lol);

		/////////////////////////////////////////////////////////////

		for (int h = 0; h < listDataHeader.size(); h++) {
			listDataChild.put(listDataHeader.get(h), flats_lol.get(h)); // Header, Child data
		}


	}

	protected void sendEmail(String[] TO, String p_name, String p_flat) {
		Log.i("Send email", "");

		Intent emailIntent = new Intent(Intent.ACTION_VIEW);
		emailIntent.setData(Uri.parse("mailto:"));
		emailIntent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
		emailIntent.setType("plain/text");

		emailIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
		//emailIntent.putExtra(Intent.EXTRA_CC, CC);
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Waste Segregation");
		emailIntent.putExtra(Intent.EXTRA_TEXT, "Respected "+p_name+","+"\n"+ "You have not segregated waste today !!!\n ");

		try {
			startActivity(emailIntent);
			finish();
			Log.i("Finished sending email...", "");
		} catch (android.content.ActivityNotFoundException ex) {
			Toast.makeText(MainActivity.this,
					"There is no email client installed.", Toast.LENGTH_SHORT).show();
		}
	}


	public class CustomDialogClass extends Dialog implements
	android.view.View.OnClickListener {

		public Activity c;
		public Dialog d;
		public Button yes, no;
		String email_id,p_name,p_flat;

		public CustomDialogClass(Activity a, String email_id, String p_name, String p_flat) {
			super(a);
			// TODO Auto-generated constructor stub
			this.c = a;
			this.email_id= email_id;
			this.p_name= p_name;
			this.p_flat= p_flat;

		}

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.custom_dialog);
			yes = (Button) findViewById(R.id.btn_yes);
			no = (Button) findViewById(R.id.btn_no);
			TextView text = (TextView) findViewById(R.id.txt_dia);
			text.setText("Send Email to Flat no. "+p_flat.replace(".0", "")+" ?");

			yes.setOnClickListener(this);
			no.setOnClickListener(this);

		}

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_yes:
				write_xls(p_flat,p_name, false,0);
                new EmailSender(email_id.toString(),p_name,p_flat).execute();
				break;
			case R.id.btn_no:
				dismiss();
				break;
			default:
				break;
			}
			dismiss();
		}
	}
	
	public class EmailSender extends AsyncTask<String, String, String> {   
		ProgressDialog pd;
		String email;
		String p_name,p_flat;

		public EmailSender(String email, String p_name, String p_flat) { 
			
			this.email=email;
			this.p_name=p_name;
			this.p_flat=p_flat;

		}


		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pd.dismiss();

		}


		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pd = new ProgressDialog(MainActivity.this);
			pd.setMessage("Sending mail");
			pd.show();
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			
			if (isOnline()) {
//				System.out.println("p_name_loop:"+todo_name);
//				System.out.println("email_id_loop:"+todo_email);

				System.out.println("Name:"+p_name);
				todo_name.add(p_name);
				todo_email.add(email);

				
//				System.out.println("p_name_loop:"+todo_name);
//				System.out.println("email_id_loop:"+todo_email);



					for (int i = 0; i < todo_name.size();) {
						
//						System.out.println("Inn");
//						System.out.println("email_id_in_loop:"+todo_name.get(i));

						try{

							final String fromEmail = "healthoffice.iitb@gmail.com"; //requires valid gmail id
							final String password = "healthoffice@iitb"; // correct password for gmail id

							System.out.println("TLSEmail Start");
							Properties props = new Properties();
							props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
							props.put("mail.smtp.port", "587"); //TLS Port
							props.put("mail.smtp.auth", "true"); //enable authentication
							props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

							//create Authenticator object to pass in Session.getInstance argument
							Authenticator auth = new Authenticator() {
								//override the getPasswordAuthentication method
								protected PasswordAuthentication getPasswordAuthentication() {
									return new PasswordAuthentication(fromEmail, password);
								}
							};
							Session session = Session.getInstance(props, auth);

							MimeMessage message = new MimeMessage(session);
							message.setFrom(new InternetAddress(fromEmail));
							message.addRecipient(Message.RecipientType.TO, new InternetAddress(todo_email.get(i)));

							System.out.println("Mail Check 2");

							message.setSubject("Waste segregation");
							
							message.setText("Dear "+todo_name.get(i)+","+"\n"+ "You have not" +
									" segregated the waste from your home today. We hope it is unintentional.\nPlease co-operate.\n\n" +
									"Best regards,"+"\n"+"PHO, IITB");

							System.out.println("Mail Check 3");
							todo_email.remove(todo_email.get(i));
							
							todo_name.remove(todo_name.get(i));


							Transport.send(message);
							System.out.println("Mail Sent");
							i=0;
							pd.dismiss();

						}catch(Exception ex){
							System.out.println("Mail fail");
							System.out.println(ex);
						}
						
					} 
				
			}else {
				
				todo_email.add(email);
				todo_name.add(p_name);
				edit = getSharedPreferences("my_pref", MODE_PRIVATE).edit();
				edit.putInt("array_size", todo_email.size());
				for(int i=0;i<todo_email.size(); i++){

					edit.putString("p_name" + i, todo_name.get(i));
					edit.putString("p_email" + i, todo_email.get(i));
				}
				edit.commit();
//				System.out.println("todo_email:"+todo_email);
//				System.out.println("todo_name:"+todo_name);

			}
			
			return null;
		}
	}
	
	public boolean isOnline() {
	    ConnectivityManager cm =
	        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    return netInfo != null && netInfo.isConnectedOrConnecting();
	}
	
	
	public boolean write_xls(String p_flat2, String p_name2, Boolean wet_dry_flag, int count){
		
		// check if available and not read only 
		if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) { 
			Log.e(TAG, "Storage not available or read only"); 
			return false; 
		} 

		boolean success = false; 
		// location of file to be stored
		String fileName = "/sdcard/log.xls";       
		int last=0;
		File file = new File(fileName);
		FileInputStream fin = null;  
		HSSFWorkbook workbook = null;  
		HSSFSheet worksheet = null;  
		FileOutputStream fout = null;
		POIFSFileSystem lPOIfs = null;

		//if file already exist get last row index
		if (file.exists()) {
			System.out.println("in");
			try{

				fout = new FileOutputStream(fileName, true);
				fin = new FileInputStream(fileName);
				lPOIfs = new POIFSFileSystem(fin);
				workbook = new HSSFWorkbook(lPOIfs);
				// log: sheet name
				worksheet = workbook.getSheet("log");
				for (int i=0; i<workbook.getNumberOfSheets(); i++) {
					System.out.println( workbook.getSheetName(i) );                                    
				}
				HSSFSheet sheet = workbook.getSheetAt(0);
				// getting last row index
				last = sheet.getLastRowNum();
				System.out.println("last:"+last);

			}catch (IOException e) {  
				e.printStackTrace();  
			}catch (NullPointerException e){
				e.printStackTrace(); 
			}
		}else {
			System.out.println("out");

			//if file is not present, default index is 0
			try{
				fout = new FileOutputStream(file);                            
			}catch (IOException e) {  
				e.printStackTrace();  
			}
			workbook = new HSSFWorkbook();                       
			worksheet = workbook.createSheet("log"); 

		} 
           // if last index is 0 ie. no data exist in the file, but if last index is not equal to zero then last index + 1      
           if(last != 0){
        	   if (wet_dry_flag) {
        		   if (count==1) {
        			   last = worksheet.getLastRowNum()+2;

        		   }else {
        			   last = worksheet.getLastRowNum()+1;

        		   }

			}else {
	        	   last = worksheet.getLastRowNum()+1;

			}
           }else{
        	   last = worksheet.getLastRowNum();
           }
        
        if (last==0) {
        	// setting column width
        	worksheet.setColumnWidth(0, (15 * 200));
        	worksheet.setColumnWidth(1, (15 * 300));
        	worksheet.setColumnWidth(2, (15 * 200));

        	HSSFRow row = worksheet.createRow(last);  
        	// setting separate background for header
        	CellStyle cs = workbook.createCellStyle();
        	cs.setFillForegroundColor(HSSFColor.LIME.index);
        	cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            HSSFCell cellA1 = row.createCell(0);
            cellA1.setCellValue("Flat Number");   
            cellA1.setCellStyle(cs);
            
            HSSFCell cellB1 = row.createCell(1);
            cellB1.setCellValue("Name");
            cellB1.setCellStyle(cs);
        
            HSSFCell cellC1 = row.createCell(2);
            cellC1.setCellValue("Date");
            cellC1.setCellStyle(cs);

             
            HSSFRow row1 = worksheet.createRow(1);                        
            HSSFCell cellA2 = row1.createCell(0);
            cellA2.setCellValue(p_flat2.replace(".0", ""));   
		    
            HSSFCell cellB2 = row1.createCell(1);
            cellB2.setCellValue(p_name2);   
            
            HSSFCell cellC2 = row1.createCell(2);
            Time today = new Time(Time.getCurrentTimezone());
            today.setToNow();
            cellC2.setCellValue(today.monthDay + "/"+ today.month+"/"+today.year );
		}else {
			if (wet_dry_flag) {
	        	
				CellStyle cs = workbook.createCellStyle();
	        	cs.setFillForegroundColor(HSSFColor.LIME.index);
	        	cs.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				HSSFRow row = worksheet.createRow(last);                         
				HSSFCell cellA1 = row.createCell(0);
				cellA1.setCellValue(p_flat2);   
				cellA1.setCellStyle(cs);
				HSSFCell cellB1 = row.createCell(1);
				cellB1.setCellValue(p_name2+" Kg");   

				HSSFCell cellC1 = row.createCell(2);
				Time today = new Time(Time.getCurrentTimezone());
				today.setToNow();
				cellC1.setCellValue(today.monthDay + "/"+ today.month+"/"+today.year );	
			}else {
				HSSFRow row = worksheet.createRow(last);                         
				HSSFCell cellA1 = row.createCell(0);
				cellA1.setCellValue(p_flat2.replace(".0", ""));   

				HSSFCell cellB1 = row.createCell(1);
				cellB1.setCellValue(p_name2);   

				HSSFCell cellC1 = row.createCell(2);
				Time today = new Time(Time.getCurrentTimezone());
				today.setToNow();
				cellC1.setCellValue(today.monthDay + "/"+ today.month+"/"+today.year );
			}
			

		}
       
       try {
		workbook.write(fout);
		success = true; 

	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
       try {
		fout.flush();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}

       try {
		fout.close();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
		try {
			 fout = new FileOutputStream(file);
			workbook.write(fout);
	        fout.flush();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return success; 

	}
	
	public static boolean isExternalStorageReadOnly() { 
		String extStorageState = Environment.getExternalStorageState(); 
		if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) { 
			return true; 
		} 
		return false; 
	} 

	public static boolean isExternalStorageAvailable() { 
		String extStorageState = Environment.getExternalStorageState(); 
		if (Environment.MEDIA_MOUNTED.equals(extStorageState)) { 
			return true; 
		} 
		return false; 
	} 
	
	
}

