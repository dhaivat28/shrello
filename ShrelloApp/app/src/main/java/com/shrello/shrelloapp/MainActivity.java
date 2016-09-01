package com.shrello.shrelloapp;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity
{
	EditText linkedin,stackoverflow,error_text,rlinkedin,rstackoverflow;
	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
/*
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

			TypedValue typedValue = new TypedValue();
			Resources.Theme theme = getTheme();
			theme.resolveAttribute(R.attr.colorPrimary, typedValue, true);
			int color = typedValue.data;

			Bitmap bm = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
			ActivityManager.TaskDescription td = new ActivityManager.TaskDescription("Shrello", bm, color);

			setTaskDescription(td);
			bm.recycle();

		}*/
	}

	public void load_sign_up_activity(View view)
	{
		Intent intent = new Intent(this, signup_name.class);
		startActivity(intent);
	}
}
