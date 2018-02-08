package com.example.whip.listviewsuite2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    ImageView imgdetails;
    TextView title;
    TextView desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        imgdetails = findViewById(R.id.imgviewdetails);
        title = findViewById(R.id.txtViewTitle);
        desc = findViewById(R.id.txtViewDesc);

        Intent intent = getIntent();
        String titre = intent.getStringExtra("TITLE");
        String url = intent.getStringExtra("URL");
        String sdesc = intent.getStringExtra("DESC");

        title.setText(titre);
        desc.setText(sdesc);

        Picasso.with(this).load(url).into(imgdetails);
    }
}
