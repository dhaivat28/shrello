package com.shrello.shrelloapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class dp_upload extends AppCompatActivity
{
	private String KEY_IMAGE = "image";
	private String KEY_NAME = "name";
	ImageButton mImg_Btn;
	private Bitmap bitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dp_upload);
		mImg_Btn = (ImageButton) findViewById(R.id.avatar);
	}

	public void img_select(View view)
	{
		Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
		photoPickerIntent.setType("image/*");
		startActivityForResult(photoPickerIntent, 1);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null)
		{
			Uri filePath = data.getData();
			try
			{
				//Getting the Bitmap from Gallery
				bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
				//Setting the Bitmap to ImageView
				mImg_Btn.setImageBitmap(bitmap);
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	public String getStringImage(Bitmap bmp)
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		byte[] imageBytes = baos.toByteArray();
		String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
		return encodedImage;

	}
}