package com.shrello.shrelloapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class signup_email_sequence extends AppCompatActivity
{

	String mfullname;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Bundle bundle = getIntent().getExtras();
		mfullname= bundle.getString("fullname");
		setContentView(R.layout.activity_signup_email_sequence);
	}

	public void load_signup_password_email(View view)
	{

		String mEmail = ((EditText) findViewById(R.id.edit_email)).getText().toString().trim();
		if (mEmail.trim().isEmpty())
		{
			Toast.makeText(signup_email_sequence.this, "Please Enter email", Toast.LENGTH_SHORT).show();
		}
		else
		{
			Intent intent = new Intent(this, signup_password_sequence.class);
			Bundle bundle = new Bundle();
			//Add your data to bundle
			bundle.putString("fullname",mfullname);
			bundle.putString("email",mEmail);
			//Add the bundle to the intent
			intent.putExtras(bundle);
			startActivity(intent);
		}
	}
}
