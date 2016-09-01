package com.shrello.shrelloapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;


public class signup_password_sequence extends AppCompatActivity
{
	String mfullname, memail, password = null;
	String encoded_password;
	ProgressDialog progress;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Bundle bundle = getIntent().getExtras();
		mfullname = bundle.getString("fullname");
		memail = bundle.getString("email");
		setContentView(R.layout.activity_signup_password_sequence);
	}

	private static String encryptPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException
	{

		MessageDigest crypt = MessageDigest.getInstance("SHA-1");
		crypt.reset();
		crypt.update(password.getBytes("UTF-8"));

		return new BigInteger(1, crypt.digest()).toString(16);
	}

	public void register_user(View view) throws UnsupportedEncodingException, NoSuchAlgorithmException
	{
		password = ((EditText) findViewById(R.id.edit_password)).getText().toString().trim();
		if (password.length() >= 6)
		{
			encoded_password = encryptPassword(password);
			String url = "http://192.168.1.101:8000/signup/";
			progress = ProgressDialog.show(this, "Please wait", "Checking", true);
			StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
			{
				@Override
				public void onResponse(String response)
				{

					JSONObject obj = null;
					String val=null;
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
					if(val.contentEquals("succesful"))
					{
						progress.dismiss();
						Toast.makeText(signup_password_sequence.this, val, Toast.LENGTH_LONG).show();
						Intent intent = new Intent(signup_password_sequence.this, dp_upload.class);
						Bundle bundle = new Bundle();
						//Add your data to bundle
						bundle.putString("email", memail);
						//Add the bundle to the intent
						intent.putExtras(bundle);
						startActivity(intent);
					}
					else
					{
						progress.dismiss();
						Toast.makeText(signup_password_sequence.this, val, Toast.LENGTH_LONG).show();
					}
				}
			}, new Response.ErrorListener()
			{
				@Override
				public void onErrorResponse(VolleyError error)
				{
					progress.dismiss();
					Toast.makeText(signup_password_sequence.this, error.toString(), Toast.LENGTH_LONG).show();
				}
			})
			{
				@Override
				protected Map<String, String> getParams()
				{
					Map<String, String> params = new HashMap<String, String>();
					params.put("KEY_FULLNAME", mfullname);
					params.put("KEY_EMAIL", memail);
					params.put("KEY_PASS", encoded_password);
					return params;
				}

			};
			RequestQueue requestQueue = Volley.newRequestQueue(this);
			requestQueue.add(stringRequest);
		}
		else
		{
			Toast.makeText(signup_password_sequence.this, "password is too short!", Toast.LENGTH_SHORT).show();
		}
	}
}
