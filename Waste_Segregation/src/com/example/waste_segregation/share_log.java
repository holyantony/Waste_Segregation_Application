package com.example.waste_segregation;
/**
 * Copyright 2013 Google Inc. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */


import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveApi.DriveContentsResult;
import com.google.android.gms.drive.MetadataChangeSet;

/**
 * Android Drive Quickstart activity. This activity takes a photo and saves it
 * in Google Drive. The user is prompted with a pre-made dialog which allows
 * them to choose the file location.
 */
public class share_log extends Activity implements ConnectionCallbacks,
        OnConnectionFailedListener {

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	private static final String TAG = "android-drive-quickstart";
    private static final int REQUEST_CODE_CREATOR = 2;
    private static final int REQUEST_CODE_RESOLUTION = 3;

    private GoogleApiClient mGoogleApiClient;

    /**
     * Create a new file and save it to Drive.
     */
    private void saveFileToDrive() {
        // Start by creating a new contents, and setting a callback.
        Log.i(TAG, "Creating new contents.");
        Drive.DriveApi.newDriveContents(mGoogleApiClient)
                .setResultCallback(new ResultCallback<DriveContentsResult>() {

            @Override
            public void onResult(DriveContentsResult result) {
                // If the operation was not successful, we cannot do anything
                // and must
                // fail.
                if (!result.getStatus().isSuccess()) {
                    Log.i(TAG, "Failed to create new contents.");
                    return;
                }
               
                // Otherwise, we can write our data to the new contents.
                Log.i(TAG, "New contents created.");
                // Get an output stream for the contents.
                OutputStream outputStream = result.getDriveContents().getOutputStream();
//                OutputStream outputStream=null;
                
                ByteArrayOutputStream bos = null;
                try {
                	java.io.File file = new java.io.File("/sdcard/log.xls");
                    FileInputStream fis = new FileInputStream(file);
                    bos = new ByteArrayOutputStream();
                    if (fis != null) {
                        byte[] buf = new byte[(int) file.length()];
                        for (int num; (num = fis.read(buf)) != -1;) {
                            bos.write(buf, 0, num);
                        }
                    }
                    byte[] bytes = bos.toByteArray();
                    outputStream = result.getDriveContents().getOutputStream();
                    outputStream.write(bytes);
                   
                } catch (Exception e) {
                }
              
                
                
                MetadataChangeSet metadataChangeSet = new MetadataChangeSet.Builder()
                        .setMimeType("appliaction/xls").setTitle("log.xls").build();
                // Create an intent for the file chooser, and start it.
                IntentSender intentSender = Drive.DriveApi
                        .newCreateFileActivityBuilder()
                        .setInitialMetadata(metadataChangeSet)
                        .setInitialDriveContents(result.getDriveContents())
                        .build(mGoogleApiClient);
                try {
                    startIntentSenderForResult(
                            intentSender, REQUEST_CODE_CREATOR, null, 0, 0, 0);
                } catch (SendIntentException e) {
                    Log.i(TAG, "Failed to launch file chooser.");
                }
            }
        });
    }
    
    
    @Override
    protected void onActivityResult(final int requestCode,  int resultCode, final Intent data) {
        switch (requestCode) {
         
            case REQUEST_CODE_CREATOR:
                // Called after a file is saved to Drive.
                if (resultCode == RESULT_OK) {
                  Toast.makeText(getApplicationContext(), "Uploaded", Toast.LENGTH_SHORT).show();
                  Intent intent = new Intent(getApplicationContext(), Zones.class);
                  startActivity(intent);
                }
                break;
        }
    }
 
    @Override
    protected void onResume() {
    	super.onResume();
    	if (mGoogleApiClient == null) {
    		// Create the API client and bind it to an instance variable.
            // We use this instance as the callback for connection and connection
            // failures.
            // Since no account name is passed, the user is prompted to choose.
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addApi(Drive.API)
                    .addScope(Drive.SCOPE_FILE)
                    .useDefaultAccount()
//                    .setAccountName("holyantony1492@gmail.com")
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();  
    	}
    	System.out.println(mGoogleApiClient.toString());
        // Connect the client. Once connected, the camera is launched.
        mGoogleApiClient.connect();
    }


    @Override
    public void onConnectionFailed(ConnectionResult result) {
        // Called whenever the API client fails to connect.
        Log.i(TAG, "GoogleApiClient connection failed: " + result.toString());
        if (!result.hasResolution()) {
            // show the localized error dialog.
            GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(), this, 0).show();
            return;
        }
        // The failure has a resolution. Resolve it.
        // Called typically when the app is not yet authorized, and an
        // authorization
        // dialog is displayed to the user.
        try {
            result.startResolutionForResult(this, REQUEST_CODE_RESOLUTION);
        } catch (SendIntentException e) {
            Log.e(TAG, "Exception while starting resolution activity", e);
        }
    } 

    @Override
    public void onConnected(Bundle connectionHint) {
      
        saveFileToDrive();
    }

    @Override
    public void onConnectionSuspended(int cause) {
        Log.i(TAG, "GoogleApiClient connection suspended");
    }
}
