package com.chaithu.phasorzandroid;

import android.os.Bundle;
import android.provider.Telephony.Sms;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;

import android.telephony.SmsManager;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends Activity {
	
	private int code ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //public int code = 0;
        Button b = (Button) findViewById(R.id.button1);
        Button c = (Button) findViewById(R.id.button2);
        final EditText number = (EditText) findViewById(R.id.editText1);
        final EditText numberCode = (EditText) findViewById(R.id.editText2);
        final TextView tw1 = (TextView) findViewById(R.id.textView1);
        b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String s = number.getText().toString();
				if(s.length() >= 7 ){
					String g = s.substring(3, 7);
					sendSms(s,g);
					tw1.setText("Enter the code sent to you by SMS");
					code = Integer.parseInt(g);
				}
				else{
					Toast.makeText(getBaseContext(), 
	                        "Please enter a valid mobile number", 
	                        Toast.LENGTH_SHORT).show();
				}
			}
		});
        c.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String t = numberCode.getText().toString();
				if(Integer.parseInt(t) == code){
					Toast.makeText(getBaseContext(), 
		                      "Verification Successful", 
		                       Toast.LENGTH_SHORT).show();
					//jump to another activity
				}
				else{
					Toast.makeText(getBaseContext(), 
		                        "wrong code,Type the correct one", 
		                        Toast.LENGTH_SHORT).show();						
				}
				
			}
		});
    }
    private void sendSms(String s, String g){
    	
        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, Sms.class), 0);                
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(s, null, g, pi, null);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
