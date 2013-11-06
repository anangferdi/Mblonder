package com.example.mblonder;

import org.masjidguide.SharedPreferences;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.view.Menu;
import android.view.View;
import android.widget.*;

import com.facebook.android.*;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.*;


public class MainActivity extends Activity {

	private static final String APP_ID = "1410177152549488";
	private static final String[] PERMISSIONS = new String[] {"publish_stream"};
	
	private static final String TOKEN = "access_token";
    private static final String EXPIRES = "expires_in";
    private static final String KEY = "f155b5335f1ba5bd006a81c4bec9d7c1";

	private Facebook facebook;
	private String messageToPost;
	
	public boolean restoreCredentials(Facebook facebook) {
    	SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(KEY, Context.MODE_PRIVATE);
    	facebook.setAccessToken(sharedPreferences.getString(TOKEN, null));
    	facebook.setAccessExpires(sharedPreferences.getLong(EXPIRES, 0));
    	return facebook.isSessionValid();
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		facebook = new Facebook(APP_ID);
		restoreCredentials(facebook);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public void loginFB(View button){
		loginToFB();
	}

	public void loginToFB(){
		 facebook.authorize(this, PERMISSIONS, Facebook.FORCE_DIALOG_AUTH, (DialogListener) new LoginDialogListener());
	}
	
	class LoginDialogListener implements DialogListener {
		
	    public void onFacebookError(Facebook error) {
	    	showToast("Authentication with Facebook failed!");
	        finish();
	    }
	    public void onError(Dialog error) {
	    	showToast("Authentication with Facebook failed!");
	        finish();
	    }
	    public void onCancel() {
	    	showToast("Authentication with Facebook cancelled!");
	        finish();
	    }
		@Override
		public void onError(DialogError arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void onFacebookError(FacebookError arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void onComplete(Bundle arg0) {
			// TODO Auto-generated method stub
			
		}
	}
	private void showToast(String message){
		Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
	}
}
