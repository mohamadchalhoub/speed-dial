package com.example.speeddial;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Button call, email;
    ImageView image;
    Uri universal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        call = (Button) findViewById(R.id.button);
        email = (Button) findViewById(R.id.button2);
        image = (ImageView) findViewById(R.id.imageView);
    }

    public void call(View view) {
        //String phoneNumber = "78827104";
        Intent call = new Intent(Intent.ACTION_DIAL);
        call.setData(Uri.parse("tel:" + 78827104));
        startActivity(call);
    }

    public void email(View view) {
        Intent email = new Intent(Intent.ACTION_SEND);
        email.setType("message/rfc88");
        email.putExtra(Intent.EXTRA_EMAIL, "mohamadchalhoub24@gmail.com");
        email.putExtra(Intent.EXTRA_SUBJECT, "test");
        email.putExtra(Intent.EXTRA_TEXT, "hello dear");
        startActivity(email);
    }

    public void select(View view) {
        Intent selectImage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(selectImage, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Uri universal = data.getData();
            image.setImageURI(universal);
        }
    }

    public void share(View view) {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/*");
        share.putExtra(Intent.EXTRA_STREAM, universal);
        share.putExtra(Intent.EXTRA_TEXT, "sending universal");
        Intent chooser = Intent.createChooser(share, "selected image");
        startActivity(chooser);
    }

    public void shareLocation(View view) {
        Intent shareLocation = new Intent(Intent.ACTION_VIEW);
        shareLocation.setData(Uri.parse("geo:33.874161,35.516342,16z"));
        startActivity(shareLocation);
    }
}