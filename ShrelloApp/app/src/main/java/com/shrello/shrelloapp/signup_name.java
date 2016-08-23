package com.shrello.shrelloapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class signup_name extends AppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup_name);
	}

	public void load_signup_squence_email(View view)
	{
		String mfullname = ((EditText) findViewById(R.id.edit_fullname)).getText().toString().trim();
		if (mfullname.trim().isEmpty())
		{
			Toast.makeText(signup_name.this, "Please Enter name", Toast.LENGTH_SHORT).show();
		}
		else
		{
			Intent intent = new Intent(this, signup_email_sequence.class);

			Bundle bundle = new Bundle();
			//Add your data to bundle
			bundle.putString("fullname",mfullname);
			//Add the bundle to the intent
			intent.putExtras(bundle);
			startActivity(intent);

		}
	}
}
