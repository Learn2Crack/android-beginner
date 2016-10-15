package com.learn2crack.loadimageurl;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, LoadImageTask.Listener {

    private ImageView mImageView;
    private Button mBtLoadImage;

    public static final String IMAGE_URL = "https://api.learn2crack.com/android/images/donut.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = (ImageView) findViewById(R.id.image);
        mBtLoadImage = (Button) findViewById(R.id.btn_load_image);
        mBtLoadImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_load_image:
                new LoadImageTask(this).execute(IMAGE_URL);
                break;
        }
    }

    @Override
    public void onImageLoaded(Bitmap bitmap) {

        mImageView.setImageBitmap(bitmap);
    }

    @Override
    public void onError() {
        Toast.makeText(this, "Error Loading Image !", Toast.LENGTH_SHORT).show();
    }
}
