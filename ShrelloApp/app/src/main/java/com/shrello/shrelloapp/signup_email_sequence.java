package com.shrello.shrelloapp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import org.json.*;
public class signup_email_sequence extends AppCompatActivity
{

	String mfullname,val;
	String mEmail;
	ProgressDialog progress;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Bundle bundle = getIntent().getExtras();
		mfullname = bundle.getString("fullname");
		setContentView(R.layout.activity_signup_email_sequence);
	}

	public void load_signup_password_email(View view)
	{

		mEmail = ((EditText) findViewById(R.id.edit_email)).getText().toString().trim();
		if (mEmail.trim().isEmpty())
		{
			Toast.makeText(signup_email_sequence.this, "Please Enter email", Toast.LENGTH_SHORT).show();
		}
		else
		{

			progress = ProgressDialog.show(this, "Please wait", "Checking", true);
			new Thread(new Runnable()
			{
				@Override
				public void run()
				{
					String url = "http://192.168.1.101:8000/email_check/";
					StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
					{
						@Override
						public void onResponse(String response)
						{
							JSONObject obj = null;
							try
							{
								obj = new JSONObject(response);
							} catch (JSONException e)
							{
								e.printStackTrace();
							}
							try
							{	
								val = obj.getString("val");
							} catch (JSONException e)
							{
								e.printStackTrace();
							}
							if (val.contentEquals("true"))
							{
								progress.dismiss();
								Intent intent = new Intent(signup_email_sequence.this, signup_password_sequence.class);
								Bundle bundle = new Bundle();
								//Add your data to bundle
								bundle.putString("fullname", mfullname);
								bundle.putString("email", mEmail);
								//Add the bundle to the intent
								intent.putExtras(bundle);
								startActivity(intent);
							}
							else
							{
								AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(signup_email_sequence.this);
								alertDialogBuilder.setMessage("Looks like you already have an account, please log in");

								alertDialogBuilder.setPositiveButton("Log In", new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface arg0, int arg1) {
										Toast.makeText(signup_email_sequence.this,"You clicked yes button",Toast.LENGTH_LONG).show();
									}
								});

								/*alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog, int which) {
										finish();
									}
								});*/

								AlertDialog alertDialog = alertDialogBuilder.create();
								alertDialog.show();
								progress.dismiss();
							}

						}
					}, new Response.ErrorListener()
					{
						@Override
						public void onErrorResponse(VolleyError error)
						{
							Toast.makeText(signup_email_sequence.this, error.toString(), Toast.LENGTH_LONG).show();
						}
					})
					{
						@Override
						protected Map<String, String> getParams()
						{
							Map<String, String> params = new HashMap<String, String>();
							params.put("KEY_EMAIL", mEmail);
							return params;
						}

					};
					RequestQueue requestQueue = Volley.newRequestQueue(signup_email_sequence.this);
					requestQueue.add(stringRequest);
				}
			}).start();

		}
	}
}
