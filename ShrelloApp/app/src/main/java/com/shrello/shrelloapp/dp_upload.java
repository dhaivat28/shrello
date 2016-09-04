package com.shrello.shrelloapp;

import android.app.ProgressDialog;
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
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

public class dp_upload extends AppCompatActivity
{
	private String KEY_IMAGE = "image";
	private String KEY_NAME = "name";
	ImageButton mImg_Btn;
	private Bitmap bitmap;
	String URL="http://192.168.1.101:8000/img_test/";
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
			/*try
			{*/
				//Getting the Bitmap from Gallery
				//bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
				//Setting the Bitmap to ImageView
				//mImg_Btn.setImageBitmap(bitmap);
				CropImage.activity(filePath)
						.setGuidelines(CropImageView.Guidelines.ON)
						.setAspectRatio(1,1)
						.setFixAspectRatio(true)
						.start(this);
			/*} catch (IOException e)
			{
				e.printStackTrace();
			}*/
		}
		if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
			CropImage.ActivityResult result = CropImage.getActivityResult(data);
			if (resultCode == RESULT_OK) {
				Uri resultUri = result.getUri();
				try
				{
					bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), resultUri);
					bitmap = Bitmap.createScaledBitmap(bitmap, 640, 640, false);
					mImg_Btn.setImageBitmap(bitmap);
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			} else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
				Exception error = result.getError();
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

	public void upload(View view)
	{
		if(bitmap != null)
		{

			final ProgressDialog loading = ProgressDialog.show(this,"Uploading...","Please wait...",false,false);
			StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
					new Response.Listener<String>() {
						@Override
						public void onResponse(String s) {
							//Disimissing the progress dialog
							loading.dismiss();
							//Showing toast message of the response
							Toast.makeText(dp_upload.this, s , Toast.LENGTH_LONG).show();
						}
					},
					new Response.ErrorListener() {
						@Override
						public void onErrorResponse(VolleyError volleyError) {
							//Dismissing the progress dialog
							loading.dismiss();
							//Showing toast
							Toast.makeText(dp_upload.this, "e:"+volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
						}
					}){
				@Override
				protected Map<String, String> getParams() throws AuthFailureError
				{
					//Converting Bitmap to String
					String image = getStringImage(bitmap);

					//Getting Image Name
					String name = "test";

					//Creating parameters
					Map<String,String> params = new Hashtable<String, String>();

					//Adding parameters
					params.put(KEY_IMAGE, image);
					params.put(KEY_NAME, name);

					//returning parameters
					return params;
				}
			};

			//Creating a Request Queue
			RequestQueue requestQueue = Volley.newRequestQueue(this);

			//Adding request to the queue
			requestQueue.add(stringRequest);
		}
		else
		{
			Toast.makeText(dp_upload.this, "please upload image or skip" , Toast.LENGTH_SHORT).show();
		}
	}
}